package ru.spbstu.icc.kspt.model

import io.ktor.server.auth.*

class UserPrincipal(val name: String, val role: UserRole) : Principal