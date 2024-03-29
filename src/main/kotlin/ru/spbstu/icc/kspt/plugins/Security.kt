package ru.spbstu.icc.kspt.plugins

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.util.*
import ru.spbstu.icc.kspt.*
import ru.spbstu.icc.kspt.extension.oauthClientId
import ru.spbstu.icc.kspt.extension.oauthClientSecret
import ru.spbstu.icc.kspt.extension.url
import ru.spbstu.icc.kspt.model.UserInfo
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole
import kotlin.collections.set

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<UserPrincipal>(
            Cookies.AUTH_COOKIE
        ) {
            cookie.path = "/"
            cookie.extensions["SameSite"] = "lax"
        }
    }

    val redirects = mutableMapOf<String, String>()
    val config = environment.config
    install(Authentication) {
        configureSessionAuth()
        configureOauth(config, redirects)
    }
    routing {
        authenticate(AuthName.OAUTH) {
            get(CommonRoutes.AUTH) {
                // for redirect
            }
            get("/callback") {
                val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
                val response = applicationHttpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer ${principal!!.accessToken}")
                    }
                }
                val userInfo: UserInfo = response.body()

                val role = if (dao.admin(userInfo.name) != null) {
                    UserRole.ADMIN
                } else UserRole.STUDENT

                call.sessions.set(UserPrincipal(userInfo.name, role, principal!!.state!!, principal.accessToken))
                val redirect = redirects[principal.state!!]
                call.respondRedirect(redirect!!)
            }
        }
    }
}

fun AuthenticationConfig.configureOauth(config: ApplicationConfig, redirects: MutableMap<String, String>) {
    oauth(AuthName.OAUTH) {
        urlProvider = { "${config.url}/callback" }
        providerLookup = {
            OAuthServerSettings.OAuth2ServerSettings(
                name = "google",
                authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
                requestMethod = HttpMethod.Post,
                clientId = config.oauthClientId,
                clientSecret = config.oauthClientSecret,
                defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile"),
                extraAuthParameters = listOf("access_type" to "offline"),
                onStateCreated = { call, state ->
                    redirects[state] = call.request.queryParameters["redirectUrl"]!!
                }
            )
        }
        client = applicationHttpClient
    }
}


private fun AuthenticationConfig.configureSessionAuth() {
    session(AuthName.SESSION) {
        challenge {
            call.respondRedirect(createRedirectUrl(call))
        }
        validate { session: UserPrincipal ->
            session
        }
    }
    session(AuthName.SESSION_ADMIN) {
        challenge {
            call.respondRedirect(createRedirectUrl(call))
        }
        validate { session: UserPrincipal ->
            if (session.role == UserRole.ADMIN) {
                session
            } else null
        }
    }
}

private fun createRedirectUrl(call: ApplicationCall): Url {
    return URLBuilder.createFromCall(call).run {
        path(CommonRoutes.AUTH)
        parameters.append("redirectUrl", call.request.uri)
        build()
    }
}
