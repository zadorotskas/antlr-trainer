package ru.spbstu.icc.kspt.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import ru.spbstu.icc.kspt.*
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole
import kotlin.collections.set

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<UserPrincipal>(
            Cookies.AUTH_COOKIE
        ) {
            cookie.path = "/"
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(Authentication) {
        configureSessionAuth()
//        configureAdminAuth()
        configureFormAuth()
    }

    routing {
//        get("/session/increment") {
//                val session = call.sessions.get<MySession>() ?: MySession()
//                call.sessions.set(session.copy(count = session.count + 1))
//                call.respondText("Counter is ${session.count}. Refresh to increment.")
//            }
    }
}

private fun AuthenticationConfig.configureSessionAuth() {
    session(AuthName.SESSION) {
        challenge {
            call.respondRedirect("${CommonRoutes.LOGIN}?no")
        }
        validate { session: UserPrincipal ->
            session
        }
    }
    session(AuthName.SESSION_ADMIN) {
        challenge {
            call.respondRedirect("${CommonRoutes.LOGIN}?no")
        }
        validate { session: UserPrincipal ->
            if (session.role == UserRole.ADMIN) {
                session
            } else null
        }
    }
}

private fun AuthenticationConfig.configureFormAuth() {
    form(AuthName.FORM) {
        userParamName = FormFields.USERNAME
        passwordParamName = FormFields.PASSWORD
        challenge {
            val errors = call.authentication.allFailures
            when (errors.singleOrNull()) {
                AuthenticationFailedCause.InvalidCredentials ->
                    call.respondRedirect("${CommonRoutes.LOGIN}?invalid")

                AuthenticationFailedCause.NoCredentials ->
                    call.respondRedirect("${CommonRoutes.LOGIN}?no")

                else ->
                    call.respondRedirect(CommonRoutes.LOGIN)
            }
        }
        validate { cred: UserPasswordCredential ->
            val user = dao.user(cred.name)
            if (user == null || !BCrypt.verifyer().verify(cred.password.toCharArray(), user.hashedPassword).verified) {
                null
            } else {
                UserPrincipal(cred.name, user.role)
            }
        }
    }
}
//
//private fun AuthenticationConfig.configureAdminAuth() {
//    form(AuthName.ADMIN) {
//        userParamName = FormFields.USERNAME
//        passwordParamName = FormFields.PASSWORD
//        challenge {
//            val errors = call.authentication.allFailures
//            when (errors.singleOrNull()) {
//                AuthenticationFailedCause.InvalidCredentials ->
//                    call.respondRedirect("${CommonRoutes.LOGIN}?invalid")
//
//                AuthenticationFailedCause.NoCredentials ->
//                    call.respondRedirect("${CommonRoutes.LOGIN}?no")
//
//                else ->
//                    call.respondRedirect(CommonRoutes.LOGIN)
//            }
//        }
//        validate { cred: UserPasswordCredential ->
//            val user = dao.user(cred.name)
//            if (user == null
//                || !BCrypt.verifyer().verify(cred.password.toCharArray(), user.hashedPassword).verified
//                || user.role != UserRole.ADMIN
//            ) {
//                null
//            } else {
//                UserPrincipal(cred.name, user.role)
//            }
//        }
//    }
//}
