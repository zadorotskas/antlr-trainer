package ru.spbstu.icc.kspt.routing

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*
import ru.spbstu.icc.kspt.CommonRoutes


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
                        form {
                            h1 {
                                +"Login"
                            }
                            div(classes = "action") {
                                button {
                                    id = "login-btn"
                                    type = ButtonType.button
                                    +"Login with Google"
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
    }
}
