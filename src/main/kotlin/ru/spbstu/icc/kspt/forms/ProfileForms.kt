package ru.spbstu.icc.kspt.forms

import kotlinx.html.*
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole

internal fun HTML.profileForm(principal: UserPrincipal) {
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