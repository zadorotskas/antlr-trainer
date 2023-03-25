package ru.spbstu.icc.kspt.model

import org.jetbrains.exposed.sql.Table

data class Admin(val id: Int, val userName: String)

object Admins : Table() {
    val id = integer("id").autoIncrement()
    val userName = varchar("name", 256).uniqueIndex()

    override val primaryKey = PrimaryKey(Users.id)
}