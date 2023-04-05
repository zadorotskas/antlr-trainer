package ru.spbstu.icc.kspt.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object TaskSolutions : Table() {
    val id = integer("id").autoIncrement()
    val userName = varchar("userName", 256)
    val lessonId = reference("lesson_id", Lessons.id)
    val datetime = datetime("datetime")
    val state = enumeration<SolutionState>("state")
    val attempt = long("attempt")

    override val primaryKey = PrimaryKey(id)

    init {
        uniqueIndex("task_solution__attempts", userName, lessonId, attempt)
    }
}

data class TaskSolution(
    val id: Int,
    val userName: String,
    val lessonId: Int,
    val datetime: LocalDateTime,
    val state: SolutionState,
    val attempt: Long
)

enum class SolutionState(
    val resultMessage: String
) {
    LOADED("Solution was loaded, but didn't started"),
    FAILED("Run failed"),
    FINISHED("Successful")
}
