package ru.spbstu.icc.kspt.forms

import kotlinx.html.*

internal fun HTML.loginForm() {
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