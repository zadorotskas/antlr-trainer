package ru.spbstu.icc.kspt.routing

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.*
import ru.spbstu.icc.kspt.AuthName
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.FormFields
import ru.spbstu.icc.kspt.model.UserPrincipal


internal fun Routing.loginRoute() {
    route(CommonRoutes.LOGIN) {
        get {
            call.respondHtml {
                head {
                    link {
                        rel = "stylesheet"
                        href = "./style.css"
                    }
                }
                body {
                    div(classes = "login-form") {
                        form(encType = FormEncType.applicationXWwwFormUrlEncoded) {
                            h1 {
                                +"Login"
                            }
                            val queryParams = call.request.queryParameters
                            val errorMsg = when {
                                "invalid" in queryParams -> "Sorry, incorrect username or password."
                                "no" in queryParams -> "Sorry, you need to be logged in to do that."
                                else -> null
                            }
                            if (errorMsg != null) {
                                div {
                                    style = "color:red;"
                                    +errorMsg
                                }
                            } else if ("registered" in queryParams) {
                                div {
                                    style = "color:green;"
                                    +"Successfully registered"
                                }
                            }
                            div(classes = "content") {
                                div(classes = "input-field") {
                                    textInput(name = FormFields.USERNAME) {
                                        placeholder = "user"
                                    }
                                }
                                div(classes = "input-field") {
                                    passwordInput(name = FormFields.PASSWORD) {
                                        placeholder = "password"
                                    }
                                }
                            }
                            div(classes = "action") {
                                button(formMethod = ButtonFormMethod.post, formEncType = ButtonFormEncType.applicationXWwwFormUrlEncoded) {
                                    +"Log in"
                                }
                                button {
                                    id = "register-btn"
                                    type = ButtonType.button
                                    +"Register"
                                }
                            }
                        }
                    }
                    script {
                        src = "login.js"
                    }
                }
            }
        }

        authenticate(AuthName.FORM) {
            post {
                val principal = call.principal<UserPrincipal>()!!
                call.sessions.set(principal)
                call.respondRedirect(CommonRoutes.PROFILE)
            }
        }
    }
}
