package ru.spbstu.icc.kspt.routing

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.spbstu.icc.kspt.AuthName
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.model.UserPrincipal

internal fun Routing.homepageRoute() {
    authenticate(AuthName.SESSION, optional = true) {
        get("/") {
            if (call.principal<UserPrincipal>() == null) {
                call.respondRedirect(CommonRoutes.LOGIN)
            } else {
                call.respondRedirect(CommonRoutes.ALL_LESSONS)
            }
        }
    }
}
