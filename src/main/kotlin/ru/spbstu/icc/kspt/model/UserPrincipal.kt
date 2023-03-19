package ru.spbstu.icc.kspt.model

import io.ktor.server.auth.*
import kotlinx.serialization.SerialName

class UserPrincipal(val name: String, val role: UserRole, val state: String, val token: String) : Principal

data class UserInfo(
    val id: String,
    val name: String,
    @SerialName("given_name") val givenName: String,
    @SerialName("family_name") val familyName: String,
    val picture: String,
    val locale: String
)