package ru.spbstu.icc.kspt.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object TaskSolutions : Table() {
    val id = integer("id").autoIncrement()
    val userToken = varchar("userToken", 256)
    val lessonId = reference("lesson_id", Lessons.id)
    val datetime = datetime("datetime")
    val state = enumeration<SolutionState>("state")

    override val primaryKey = PrimaryKey(id)
}

data class TaskSolution(
    val id: Int,
    val userToken: String,
    val lesson: Lesson,
    val datetime: LocalDateTime,
    val state: SolutionState
)

enum class SolutionState {
    FAILED, FINISHED
}
