package ru.spbstu.icc.kspt.routing

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.FormFields
import ru.spbstu.icc.kspt.dao
import ru.spbstu.icc.kspt.model.UserRole

internal fun Route.registerRoute() {
    route(CommonRoutes.REGISTER) {
        get {
            call.respondHtml {
                body {
                    form(method = FormMethod.post, encType = FormEncType.applicationXWwwFormUrlEncoded) {
                        val queryParams = call.request.queryParameters
                        val errorMsg = when {
                            "exist" in queryParams -> "Sorry, username is already in used."
                            else -> null
                        }
                        if (errorMsg != null) {
                            div {
                                style = "color:red;"
                                +errorMsg
                            }
                        }
                        textInput(name = FormFields.USERNAME) {
                            placeholder = "login"
                        }
                        br
                        passwordInput(name = FormFields.PASSWORD) {
                            placeholder = "password"
                        }
                        br
                        submitInput {
                            value = "Register"
                        }
                    }
                }
            }
        }
        post {
            val formParameters = call.receiveParameters()
            val login = formParameters[FormFields.USERNAME] ?: error("Empty username field")

            if (dao.user(login) != null) {
                call.respondRedirect("${CommonRoutes.REGISTER}?exist")
                return@post
            }

            val password = formParameters[FormFields.USERNAME] ?: error("Empty password field")

            dao.addUser(
                login = login,
                hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray()),
                role = UserRole.STUDENT
            )

            call.respondRedirect("${CommonRoutes.LOGIN}?registered")
        }
    }
}
