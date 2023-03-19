package ru.spbstu.icc.kspt.routing

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*
import ru.spbstu.icc.kspt.AuthName
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole

internal fun Route.profileRoute() {
    authenticate(AuthName.SESSION) {
        get(CommonRoutes.PROFILE) {
            val principal = call.principal<UserPrincipal>()!!
            call.respondHtml {
                body {
                    div {
                        +"Hello, ${principal.name}!"
                    }
                    if (principal.role == UserRole.ADMIN) {
                        div {
                            +"You are admin!"
                        }
                        div {
                            button {
                                id = "add-theory-btn"
                                type = ButtonType.button
                                +"Add theory"
                            }
                        }
                    }
                    div {
                        a(href = CommonRoutes.LOGOUT) {
                            +"Log out"
                        }
                    }
                    script {
                        src = "profile.js"
                    }
                }
            }
        }
    }
}
