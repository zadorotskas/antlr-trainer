package ru.spbstu.icc.kspt.routing

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LoginRouteKtTest {

    @Test
    fun loginRoute() = testApplication {
        val response = client.get("/login")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("HttpResponse[http://localhost/login, 200 OK]", response.toString())
    }
}
