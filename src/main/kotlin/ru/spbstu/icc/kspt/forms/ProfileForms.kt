package ru.spbstu.icc.kspt.forms

import kotlinx.html.*
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole

internal fun HTML.profileForm(principal: UserPrincipal) {
    head {
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
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
                    id = "add-lesson-btn"
                    type = ButtonType.button
                    +"Add lesson"
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
        unsafe {
            +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>"
        }
    }
}