package ru.spbstu.icc.kspt.routing

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.forms.loginForm


internal fun Routing.loginRoute() {
    route(CommonRoutes.LOGIN) {
        get {
            call.respondHtml {
                loginForm()
            }
        }
    }
}
