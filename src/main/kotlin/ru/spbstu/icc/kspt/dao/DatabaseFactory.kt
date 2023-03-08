package ru.spbstu.icc.kspt.dao

import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import ru.spbstu.icc.kspt.model.Users

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val driverClassName = config.propertyOrNull("ktor.deployment.jdbcDriver")?.getString() ?: "org.h2.Driver"
        val jdbcURL = config.propertyOrNull("ktor.deployment.jdbcURL")?.getString() ?: "jdbc:h2:file:./build/db"
        val database = Database.connect(
            url = jdbcURL,
            driver = driverClassName,
            user = "postgres"
        )

        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) {
            block()
        }
}