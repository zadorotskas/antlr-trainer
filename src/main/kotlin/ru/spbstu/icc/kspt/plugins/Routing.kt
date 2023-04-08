package ru.spbstu.icc.kspt.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import ru.spbstu.icc.kspt.routing.homepageRoute
import ru.spbstu.icc.kspt.routing.lessonRoute
import ru.spbstu.icc.kspt.routing.loginRoute
import ru.spbstu.icc.kspt.routing.logoutRoute

fun Application.configureRouting() {

    routing {
        homepageRoute()
        loginRoute()
        logoutRoute()
        lessonRoute()

        static("/") {
            staticBasePackage = "static"
            resources(".")
            static("lesson") {
                resource("newLesson.js")
                resource("studentLesson.js")
                resource("adminLesson.js")
            }
        }
    }
}
