package ru.spbstu.icc.kspt.routing

import io.ktor.client.request.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.plugins.applicationHttpClient

internal fun Routing.logoutRoute() {
    get(CommonRoutes.LOGOUT) {
        call.sessions.get<UserPrincipal>()?.token?.let { token ->
            applicationHttpClient.post("https://oauth2.googleapis.com/revoke") {
                headers {
                    append(io.ktor.http.HttpHeaders.ContentType, "application/x-www-form-urlencoded")
                }
                parameter("token", token)
            }
        }
        call.sessions.clear<UserPrincipal>()
        call.respondRedirect(CommonRoutes.LOGIN)

    }
}
