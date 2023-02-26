package kspt.icc.spbstu.ru.dao.impl

import kspt.icc.spbstu.ru.dao.DAOFacade
import kspt.icc.spbstu.ru.dao.DatabaseFactory.dbQuery
import kspt.icc.spbstu.ru.model.User
import kspt.icc.spbstu.ru.model.UserRole
import kspt.icc.spbstu.ru.model.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

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
        hashedPassword = row[Users.hashedPassword, ],
        role = row[Users.role]
    )
}