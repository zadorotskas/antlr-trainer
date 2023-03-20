package ru.spbstu.icc.kspt.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import ru.spbstu.icc.kspt.routing.*

fun Application.configureRouting() {

    routing {
        homepageRoute()
        loginRoute()
        logoutRoute()
        profileRoute()
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





