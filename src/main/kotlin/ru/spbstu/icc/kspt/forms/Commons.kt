package ru.spbstu.icc.kspt.forms

import kotlinx.html.*
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole


internal fun BODY.navigation(principal: UserPrincipal) {
    nav(classes = "navbar navbar-expand-lg bg-light") {
        div(classes = "container-fluid") {
            a(classes = "navbar-brand") {
                href = "/lesson/all"
                +"ANTLR Trainer"
            }
            div(classes = "collapse navbar-collapse") {
                id = "navbarNavDropdown"
                ul(classes = "navbar-nav") {
                    li(classes = "nav-item dropdown") {
                        val shownName = if (principal.role == UserRole.ADMIN) "[Admin] ${principal.name}" else principal.name
                        unsafe {
                            +"<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\n $shownName\n </a>"
                        }
                        ul(classes = "dropdown-menu") {
                            li {
                                a(classes = "dropdown-item") {
                                    href = CommonRoutes.LOGOUT
                                    +"Log out"
                                }
                            }
                            if (principal.role == UserRole.ADMIN) {
                                li {
                                    a(classes = "dropdown-item") {
                                        href = "/lesson/new"
                                        +"Add lesson"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}