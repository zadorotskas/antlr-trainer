package ru.spbstu.icc.kspt.dao.impl

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
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

    override suspend fun theory(id: Int): Theory? = dbQuery {
        Theories
            .select(Theories.id eq id)
            .map(::resultRowToTheory)
            .singleOrNull()
    }

    override suspend fun addTheory(name: String, number: Int, path: String) = dbQuery {
        Theories.insert {
            it[Theories.name] = name
            it[Theories.number] = number
            it[Theories.path] = path
        }.resultedValues?.singleOrNull()?.let(::resultRowToTheory)
    }

    override suspend fun deleteTheory(id: Int): Boolean = dbQuery {
        Theories.deleteWhere { Theories.id eq id } > 0
    }

    private fun resultRowToTheory(row: ResultRow): Theory {
        return Theory(
            id = row[Theories.id],
            name = row[Theories.name],
            number = row[Theories.number],
            path = row[Theories.path]
        )
    }
}