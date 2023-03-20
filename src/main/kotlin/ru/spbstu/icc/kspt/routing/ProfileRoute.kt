package ru.spbstu.icc.kspt.routing

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import ru.spbstu.icc.kspt.AuthName
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.forms.profileForm
import ru.spbstu.icc.kspt.model.UserPrincipal

internal fun Route.profileRoute() {
    authenticate(AuthName.SESSION) {
        get(CommonRoutes.PROFILE) {
            val principal = call.principal<UserPrincipal>()!!
            call.respondHtml {
                profileForm(principal)
            }
        }
    }
}
