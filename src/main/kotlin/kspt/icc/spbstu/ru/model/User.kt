package kspt.icc.spbstu.ru.model

import org.jetbrains.exposed.sql.Table

enum class UserRole {
    STUDENT,
    ADMIN
}

data class User(val id: Int, val login: String, val hashedPassword: String, val role: UserRole)

object Users: Table() {
    val id = integer("id").autoIncrement()
    val login = varchar("login", 128).uniqueIndex()
    val hashedPassword = varchar("password", 1024)
    val role = enumeration<UserRole>("role")

    override val primaryKey = PrimaryKey(id)
}