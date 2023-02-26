package kspt.icc.spbstu.ru

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.sessions.*
import kspt.icc.spbstu.ru.dao.DAOFacade
import kspt.icc.spbstu.ru.dao.DatabaseFactory
import kspt.icc.spbstu.ru.dao.impl.DAOFacadeImpl
import kspt.icc.spbstu.ru.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    configureTemplating()
    configureSecurity()
    configureRouting()
}

val dao: DAOFacade = DAOFacadeImpl()