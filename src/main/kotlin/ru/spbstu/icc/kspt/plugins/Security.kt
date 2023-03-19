package ru.spbstu.icc.kspt.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import ru.spbstu.icc.kspt.*
import ru.spbstu.icc.kspt.extension.oauthClientId
import ru.spbstu.icc.kspt.extension.oauthClientSecret
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

    val config = environment.config
    val redirects = mutableMapOf<String, String>()
    install(Authentication) {
        configureSessionAuth()
//        configureFormAuth()
        oauth(AuthName.OAUTH) {
            urlProvider = { "http://localhost:8080/callback" }
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
    routing {
        authenticate(AuthName.OAUTH) {
            get(CommonRoutes.LOGIN) {
                // Redirects to 'authorizeUrl' automatically
            }

            get("/callback") {
                val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
                val userInfo: UserInfo = applicationHttpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer ${principal!!.accessToken}")
                    }
                }.body()

                call.sessions.set(UserPrincipal(userInfo.name, UserRole.ADMIN, principal!!.state!!, principal.accessToken))
                val redirect = redirects[principal.state!!]
                call.respondRedirect(redirect!!)
            }
        }
    }
}

private fun AuthenticationConfig.configureSessionAuth() {
    session(AuthName.SESSION) {
        challenge {
//            call.respondRedirect(CommonRoutes.LOGIN)
            val redirectUrl = URLBuilder("http://0.0.0.0:8080/login").run {
                parameters.append("redirectUrl", call.request.uri)
                build()
            }
            call.respondRedirect(redirectUrl)
        }
        validate { session: UserPrincipal ->
            session
        }
    }
    session(AuthName.SESSION_ADMIN) {
        challenge {
//            call.respondRedirect(CommonRoutes.LOGIN)
            val redirectUrl = URLBuilder("http://0.0.0.0:8080/login").run {
                parameters.append("redirectUrl", call.request.uri)
                build()
            }
            call.respondRedirect(redirectUrl)
        }
        validate { session: UserPrincipal ->
            if (session.role == UserRole.ADMIN) {
                session
            } else null
        }
    }
}

//private fun AuthenticationConfig.configureFormAuth() {
//    form(AuthName.FORM) {
//        userParamName = FormFields.USERNAME
//        passwordParamName = FormFields.PASSWORD
//        challenge {
//            val errors = call.authentication.allFailures
//            when (errors.singleOrNull()) {
//                AuthenticationFailedCause.InvalidCredentials ->
//                    call.respondRedirect("${CommonRoutes.LOGIN}?invalid")
//
//                AuthenticationFailedCause.NoCredentials ->
//                    call.respondRedirect("${CommonRoutes.LOGIN}?no")
//
//                else ->
//                    call.respondRedirect(CommonRoutes.LOGIN)
//            }
//        }
//        validate { cred: UserPasswordCredential ->
//            val user = dao.user(cred.name)
//            if (user == null || !BCrypt.verifyer().verify(cred.password.toCharArray(), user.hashedPassword).verified) {
//                null
//            } else {
//                UserPrincipal(cred.name, user.role)
//            }
//        }
//    }
//}

val applicationHttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
}