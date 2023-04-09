package ru.spbstu.icc.kspt.forms

import kotlinx.html.*

internal fun HTML.loginForm() {
    head {
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        form(classes = "container d-flex align-items-center flex-column mb-3") {
           div(classes = "") {
               h1(classes = "p-2") {
                   +"Login"
               }
               div(classes = "p-2") {
                   button(classes = "btn btn-primary mb-3") {
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
        unsafe {
            +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>"
        }
    }
}