package ru.spbstu.icc.kspt.routing

import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole

internal class HomepageRoutingKtTest {

    @Test
    fun testRoot() = testApplication {
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("HttpResponse[http://localhost/login, 200 OK]", response.toString())
    }

    @Test
    fun testRootWithUserPrincipal() = testApplication {
        val client = createClient {
            install(HttpCookies)
        }

        routing {
            get("/login-test") {
                call.sessions.set(UserPrincipal(name = "", role = UserRole.STUDENT, state = "", token = ""))
            }
        }

        client.get("/login-test")
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("HttpResponse[http://localhost/lesson/all, 200 OK]", response.toString())
    }
}