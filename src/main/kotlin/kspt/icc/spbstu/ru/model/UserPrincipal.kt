package kspt.icc.spbstu.ru.model

import io.ktor.server.auth.*

class UserPrincipal(val name: String, val role: UserRole) : Principal