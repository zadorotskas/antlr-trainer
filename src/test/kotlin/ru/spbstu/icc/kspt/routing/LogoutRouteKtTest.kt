package ru.spbstu.icc.kspt.routing

import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.spbstu.icc.kspt.applicationHttpClient
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole

internal class LogoutRouteKtTest {

    @Test
    fun logoutRoute() = testApplication {
        val client = createClient {
            install(HttpCookies)
        }
        routing {
            get("/login-test") {
                call.sessions.set(UserPrincipal(name = "", role = UserRole.STUDENT, state = "", token = ""))
            }
        }
        externalServices {
            hosts("https://oauth2.googleapis.com") {
                install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                    })
                }
                routing {
                    post("revoke") {
                        call.respond(HttpStatusCode.OK)
                    }
                }
            }
        }

        mockkStatic(::applicationHttpClient)
        every { applicationHttpClient } returns client

        client.get("/login-test")

        val response = client.get("/logout")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("HttpResponse[http://localhost/login, 200 OK]", response.toString())
    }
}