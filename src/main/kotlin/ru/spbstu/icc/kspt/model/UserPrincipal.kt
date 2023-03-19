package ru.spbstu.icc.kspt.model

import io.ktor.server.auth.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class UserPrincipal(val name: String, val role: UserRole, val state: String, val token: String) : Principal

@Serializable
data class UserInfo(
    val id: String,
    val name: String,
    @SerialName("given_name") val givenName: String,
    val picture: String,
    val locale: String
)