package ru.spbstu.icc.kspt

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json
import ru.spbstu.icc.kspt.dao.DAOFacade
import ru.spbstu.icc.kspt.dao.DatabaseFactory
import ru.spbstu.icc.kspt.dao.impl.DAOFacadeImpl
import ru.spbstu.icc.kspt.plugins.configureRouting
import ru.spbstu.icc.kspt.plugins.configureSecurity
import ru.spbstu.icc.kspt.plugins.configureTemplating

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init(environment.config)

    configureTemplating()
    configureSecurity()
    configureRouting()
}

val dao: DAOFacade = DAOFacadeImpl()

val applicationHttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}