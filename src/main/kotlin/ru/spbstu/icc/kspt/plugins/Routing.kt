package ru.spbstu.icc.kspt.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import ru.spbstu.icc.kspt.AuthName.OAUTH
import ru.spbstu.icc.kspt.routing.*

fun Application.configureRouting() {

    routing {
        homepageRoute()
//        loginRoute()
        logoutRoute()
        profileRoute()
        registerRoute()
        lessonRoute()

        static("/") {
            staticBasePackage = "static"
            resources(".")
            static("theory") {
                resource("newLesson.js")
                resource("lesson.js")
            }
        }
    }
}





