package ru.spbstu.icc.kspt.extension

import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole


internal class ApplicationCallExtensionsKtTest {

    @Test
    fun shouldReturnTrueWhenUserRoleIsAdmin() {
        val callMock = mockk<ApplicationCall>(relaxed = true)
        val userPrincipal = UserPrincipal(
            name = "name",
            role = UserRole.ADMIN,
            state = "state",
            token = "token"
        )

        every { (callMock.sessions.get<UserPrincipal>()) } returns userPrincipal

        val result = callMock.isAdmin()

        assertTrue(result)
    }

    @Test
    fun shouldReturnFalseWhenUserRoleIsNotAdmin() {
        val callMock = mockk<ApplicationCall>(relaxed = true)
        val userPrincipal = UserPrincipal(
            name = "name",
            role = UserRole.STUDENT,
            state = "state",
            token = "token"
        )

        every { (callMock.sessions.get<UserPrincipal>()) } returns userPrincipal

        val result = callMock.isAdmin()

        assertFalse(result)
    }

    @Test
    fun shouldReturnFalseWhenUserPrincipalIsMissing() {
        val callMock = mockk<ApplicationCall>(relaxed = true)

        every { (callMock.sessions.get<UserPrincipal>()) } returns null

        val result = callMock.isAdmin()

        assertFalse(result)
    }

    @Test
    fun shouldReturnUserName() {
        val callMock = mockk<ApplicationCall>(relaxed = true)
        val userPrincipal = UserPrincipal(
            name = "name",
            role = UserRole.ADMIN,
            state = "state",
            token = "token"
        )

        every { (callMock.sessions.get<UserPrincipal>()) } returns userPrincipal

        val result = callMock.userName()

        assertEquals("name", result)
    }

    @Test
    fun shouldThrowExceptionWhenUserPrincipalIsMissing() {
        val callMock = mockk<ApplicationCall>(relaxed = true)

        every { (callMock.sessions.get<UserPrincipal>()) } returns null

        val result = assertThrows<IllegalStateException> {
            callMock.userName()
        }

        assertEquals("missing user name", result.message)
    }
}