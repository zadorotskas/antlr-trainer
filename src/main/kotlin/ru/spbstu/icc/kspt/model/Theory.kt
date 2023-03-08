package ru.spbstu.icc.kspt.model

import org.jetbrains.exposed.sql.Table

data class Theory(val id: Int, val name: String, val number: Int, val path: String)

object Theories : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val number = integer("number")
    val path = varchar("path", 512)

    override val primaryKey = PrimaryKey(id)
}
