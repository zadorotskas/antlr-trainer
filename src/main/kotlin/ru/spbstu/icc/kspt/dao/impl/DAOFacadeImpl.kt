package ru.spbstu.icc.kspt.dao.impl

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.spbstu.icc.kspt.dao.DAOFacade
import ru.spbstu.icc.kspt.dao.DatabaseFactory.dbQuery
import ru.spbstu.icc.kspt.model.*
import java.time.LocalDateTime

class DAOFacadeImpl : DAOFacade {
    override suspend fun user(login: String): User? = dbQuery {
        Users
            .select(Users.login eq login)
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun user(id: Int): User? = dbQuery {
        Users
            .select( Users.id eq id)
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun addUser(login: String, hashedPassword: String, role: UserRole): User? = dbQuery {
        Users.insert {
            it[Users.login] = login
            it[Users.hashedPassword] = hashedPassword
            it[Users.role] = role
        }.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun deleteUser(id: Int): Boolean = dbQuery {
        Users.deleteWhere { Users.id eq id } > 0
    }

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        login = row[Users.login],
        hashedPassword = row[Users.hashedPassword],
        role = row[Users.role]
    )

    override suspend fun lesson(id: Int): Lesson? = dbQuery {
        Lessons
            .select(Lessons.id eq id)
            .map(::resultRowToLesson)
            .singleOrNull()
    }

    override suspend fun allLessons(): List<Lesson> = dbQuery {
        Lessons.selectAll().map(::resultRowToLesson)
    }

    override suspend fun addLesson(name: String, number: Int) = dbQuery {
        Lessons.insert {
            it[Lessons.name] = name
            it[Lessons.number] = number
        }.resultedValues?.singleOrNull()?.let(::resultRowToLesson)
    }

    override suspend fun deleteLesson(id: Int): Boolean = dbQuery {
        Lessons.deleteWhere { Lessons.id eq id } > 0
    }

    private fun resultRowToLesson(row: ResultRow): Lesson {
        return Lesson(
            id = row[Lessons.id],
            name = row[Lessons.name],
            number = row[Lessons.number]
        )
    }

    override suspend fun admin(name: String): Admin? = dbQuery {
        Admins
            .select { Admins.userName eq name }
            .map(::resultRowToAdmin)
            .singleOrNull()
    }

    override suspend fun addAdmin(name: String): Admin? = dbQuery {
        Admins.insert {
            it[userName] = name
        }.resultedValues?.singleOrNull()?.let(::resultRowToAdmin)
    }

    private fun resultRowToAdmin(row: ResultRow): Admin {
        return Admin(
            id = row[Admins.id],
            userName = row[Admins.userName]
        )
    }

    override suspend fun addTaskSolution(
        userName: String,
        lessonId: Int,
        datetime: LocalDateTime,
        state: SolutionState,
        attempt: Long
    ): TaskSolution? = dbQuery {
        TaskSolutions.insert {
            it[TaskSolutions.userName] = userName
            it[TaskSolutions.lessonId] = lessonId
            it[TaskSolutions.datetime] = datetime
            it[TaskSolutions.state] = state
            it[TaskSolutions.attempt] = attempt
        }.resultedValues?.singleOrNull()?.let(::resultRowToTaskSolution)
    }

    override suspend fun getAttemptsCount(userName: String, lessonId: Int): Long = dbQuery {
        TaskSolutions.select { (TaskSolutions.lessonId eq lessonId) and (TaskSolutions.userName eq userName) }.count()
    }

    override suspend fun getLastAttempt(userName: String, lessonId: Int): TaskSolution? = dbQuery {
        TaskSolutions
            .select(
                (TaskSolutions.lessonId eq lessonId) and (TaskSolutions.userName eq userName)
                        and (
                        TaskSolutions.attempt eq wrapAsExpression(
                            TaskSolutions
                                .slice(TaskSolutions.attempt.max())
                                .select((TaskSolutions.lessonId eq lessonId) and (TaskSolutions.userName eq userName))
                        ))
            )
            .singleOrNull()
            ?.let(::resultRowToTaskSolution)
    }

    override suspend fun updateTaskSolutionState(userName: String, lessonId: Int, newState: SolutionState): Boolean = dbQuery {
        getLastAttempt(userName, lessonId)?.attempt?.let { lastAttempt ->
            TaskSolutions
                .update({ (TaskSolutions.lessonId eq lessonId) and (TaskSolutions.userName eq userName) and (TaskSolutions.attempt eq lastAttempt)}) {
                    it[state] = newState
                } == 1
        } ?: false
    }

    override suspend fun getProgress(lessonId: Int): List<TaskSolution> = dbQuery {
        val subQuery = TaskSolutions
            .slice(TaskSolutions.userName, TaskSolutions.attempt.max().alias(TaskSolutions.attempt.name))
            .select((TaskSolutions.lessonId eq lessonId))// and (TaskSolutions.state eq SolutionState.FINISHED))
            .groupBy(TaskSolutions.userName)
            .alias("subQuery")

        val result = TaskSolutions
            .join(subQuery, JoinType.LEFT, additionalConstraint = {
                (TaskSolutions.userName eq subQuery[TaskSolutions.userName]) and (TaskSolutions.attempt eq subQuery[TaskSolutions.attempt])
            })
            .slice(TaskSolutions.id, TaskSolutions.userName, TaskSolutions.lessonId, TaskSolutions.datetime, TaskSolutions.state, TaskSolutions.attempt)
            .select(
                (TaskSolutions.lessonId eq lessonId)
                        and (
                            (TaskSolutions.state eq SolutionState.FINISHED) or (subQuery[TaskSolutions.attempt] eq TaskSolutions.attempt)
                        )
            )
            .map(::resultRowToTaskSolution)

        val userNamesToSolution = mutableMapOf<String, TaskSolution>()

        result.forEach {
            val previous = userNamesToSolution[it.userName]
            if (previous == null || (previous.state != SolutionState.FINISHED && previous.attempt < it.attempt)) {
                userNamesToSolution[it.userName] = it
            }
        }

        return@dbQuery userNamesToSolution.values.toList().sortedWith(compareBy ({ it.state }, { it.datetime })).reversed()
    }

    private fun resultRowToTaskSolution(row: ResultRow): TaskSolution {
        return TaskSolution(
            id = row[TaskSolutions.id],
            userName = row[TaskSolutions.userName],
            lessonId = row[TaskSolutions.lessonId],
            datetime = row[TaskSolutions.datetime],
            state = row[TaskSolutions.state],
            attempt = row[TaskSolutions.attempt]
        )
    }
}