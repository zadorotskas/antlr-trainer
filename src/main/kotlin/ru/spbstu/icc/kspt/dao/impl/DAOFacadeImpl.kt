package ru.spbstu.icc.kspt.dao.impl

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.spbstu.icc.kspt.dao.DAOFacade
import ru.spbstu.icc.kspt.dao.DatabaseFactory.dbQuery
import ru.spbstu.icc.kspt.model.*

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
}