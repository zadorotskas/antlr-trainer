package kspt.icc.spbstu.ru.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.sessions.*
import kotlinx.html.*
import kotlinx.serialization.Serializable
import kspt.icc.spbstu.ru.AuthName
import kspt.icc.spbstu.ru.CommonRoutes
import kspt.icc.spbstu.ru.FormFields
import kspt.icc.spbstu.ru.dao
import kspt.icc.spbstu.ru.model.UserPrincipal
import kspt.icc.spbstu.ru.model.UserRole

fun Application.configureRouting() {

    routing {
        homepageRoute()
        loginRoute()
        logoutRoute()
        profileRoute()
        registerRoute()

        get("/") {
            call.respondText("Hello World!")
        }

        static("/") {
            staticBasePackage = "static"
            resources(".")
        }
    }
}

internal fun Routing.homepageRoute() {
    authenticate(AuthName.SESSION, optional = true) {
        get("/") {
            if (call.principal<UserPrincipal>() == null) {
                call.respondRedirect(CommonRoutes.LOGIN)
            } else {
                call.respondRedirect(CommonRoutes.PROFILE)
            }
        }
    }
}

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

internal fun Routing.logoutRoute() {
    get(CommonRoutes.LOGOUT) {
        call.sessions.clear<UserPrincipal>()
        call.respondRedirect(CommonRoutes.LOGIN)
    }
}

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
                    }
                    div {
                        a(href = CommonRoutes.LOGOUT) {
                            +"Log out"
                        }
                    }
                }
            }
        }
    }
}

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
