package ru.spbstu.icc.kspt.model

import org.jetbrains.exposed.sql.Table

data class Lesson(val id: Int, val name: String, val number: Int)

object Lessons : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val number = integer("number")

    override val primaryKey = PrimaryKey(id)
}
