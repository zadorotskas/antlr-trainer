package ru.spbstu.icc.kspt

import io.ktor.server.application.*
import io.ktor.server.sessions.*
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole

internal fun ApplicationCall.isAdmin() = this.sessions.get<UserPrincipal>()?.role == UserRole.ADMIN
