package ru.spbstu.icc.kspt.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import com.aspose.html.converters.Converter
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*
import kotlinx.html.*
import ru.spbstu.icc.kspt.AuthName
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.FormFields
import ru.spbstu.icc.kspt.dao
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.model.UserRole
import java.io.File

fun Application.configureRouting() {

    routing {
        homepageRoute()
        loginRoute()
        logoutRoute()
        profileRoute()
        registerRoute()
        theoryRoute()

        static("/") {
            staticBasePackage = "static"
            resources(".")
            static("theory") {
                resource("theory.js")
            }
        }
    }
}

internal fun Routing.homepageRoute() {
    authenticate(AuthName.SESSION, optional = true) {
        get("/") {
            if (call.principal<UserPrincipal>() == null) {
                call.respondRedirect(CommonRoutes.LOGIN)
            } else {
                call.respondRedirect(CommonRoutes.PROFILE)
            }
        }
    }
}

internal fun Routing.loginRoute() {
    route(CommonRoutes.LOGIN) {
        get {
            call.respondHtml {
                head {
                    link {
                        rel = "stylesheet"
                        href = "./style.css"
                    }
                }
                body {
                    div(classes = "login-form") {
                        form(encType = FormEncType.applicationXWwwFormUrlEncoded) {
                            h1 {
                                +"Login"
                            }
                            val queryParams = call.request.queryParameters
                            val errorMsg = when {
                                "invalid" in queryParams -> "Sorry, incorrect username or password."
                                "no" in queryParams -> "Sorry, you need to be logged in to do that."
                                else -> null
                            }
                            if (errorMsg != null) {
                                div {
                                    style = "color:red;"
                                    +errorMsg
                                }
                            } else if ("registered" in queryParams) {
                                div {
                                    style = "color:green;"
                                    +"Successfully registered"
                                }
                            }
                            div(classes = "content") {
                                div(classes = "input-field") {
                                    textInput(name = FormFields.USERNAME) {
                                        placeholder = "user"
                                    }
                                }
                                div(classes = "input-field") {
                                    passwordInput(name = FormFields.PASSWORD) {
                                        placeholder = "password"
                                    }
                                }
                            }
                            div(classes = "action") {
                                button(formMethod = ButtonFormMethod.post, formEncType = ButtonFormEncType.applicationXWwwFormUrlEncoded) {
                                    +"Log in"
                                }
                                button {
                                    id = "register-btn"
                                    type = ButtonType.button
                                    +"Register"
                                }
                            }
                        }
                    }
                    script {
                        src = "login.js"
                    }
                }
            }
        }

        authenticate(AuthName.FORM) {
            post {
                val principal = call.principal<UserPrincipal>()!!
                call.sessions.set(principal)
                call.respondRedirect(CommonRoutes.PROFILE)
            }
        }
    }
}

internal fun Routing.logoutRoute() {
    get(CommonRoutes.LOGOUT) {
        call.sessions.clear<UserPrincipal>()
        call.respondRedirect(CommonRoutes.LOGIN)
    }
}

internal fun Route.profileRoute() {
    authenticate(AuthName.SESSION) {
        get(CommonRoutes.PROFILE) {
            val principal = call.principal<UserPrincipal>()!!
            call.respondHtml {
                body {
                    div {
                        +"Hello, ${principal.name}!"
                    }
                    if (principal.role == UserRole.ADMIN) {
                        div {
                            +"You are admin!"
                        }
                        div {
                            button {
                                id = "add-theory-btn"
                                type = ButtonType.button
                                +"Add theory"
                            }
                        }
                    }
                    div {
                        a(href = CommonRoutes.LOGOUT) {
                            +"Log out"
                        }
                    }
                    script {
                        src = "profile.js"
                    }
                }
            }
        }
    }
}

internal fun Route.registerRoute() {
    route(CommonRoutes.REGISTER) {
        get {
            call.respondHtml {
                body {
                    form(method = FormMethod.post, encType = FormEncType.applicationXWwwFormUrlEncoded) {
                        val queryParams = call.request.queryParameters
                        val errorMsg = when {
                            "exist" in queryParams -> "Sorry, username is already in used."
                            else -> null
                        }
                        if (errorMsg != null) {
                            div {
                                style = "color:red;"
                                +errorMsg
                            }
                        }
                        textInput(name = FormFields.USERNAME) {
                            placeholder = "login"
                        }
                        br
                        passwordInput(name = FormFields.PASSWORD) {
                            placeholder = "password"
                        }
                        br
                        submitInput {
                            value = "Register"
                        }
                    }
                }
            }
        }
        post {
            val formParameters = call.receiveParameters()
            val login = formParameters[FormFields.USERNAME] ?: error("Empty username field")

            if (dao.user(login) != null) {
                call.respondRedirect("${CommonRoutes.REGISTER}?exist")
                return@post
            }

            val password = formParameters[FormFields.USERNAME] ?: error("Empty password field")

            dao.addUser(
                login = login,
                hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray()),
                role = UserRole.STUDENT
            )

            call.respondRedirect("${CommonRoutes.LOGIN}?registered")
        }
    }
}

internal fun Route.theoryRoute() {
    val config = environment?.config
    route(CommonRoutes.THEORY) {
        authenticate(AuthName.SESSION) {
            get("/all") {

            }
            get("/{id}") {
                val id = call.parameters["id"]?.toInt() ?: error("missing id in request")
                val theory = dao.theory(id) ?: error("can't find theory for id: $id")
                val htmlPath = theory.path.replace(".md", ".html")
                Converter.convertMarkdown(theory.path, htmlPath)
                call.respond(FreeMarkerContent("theory.ftl", mapOf("name" to theory.name, "number" to theory.number, "body" to File(htmlPath).readText())))
            }
        }
        authenticate(AuthName.SESSION_ADMIN) {
            post("/upload") {
                val number = 2 //call.receiveParameters()["number"]?.toInt() ?: 1 // error("missing number in request")
                val theoryFolder = config?.propertyOrNull("data.theoryFolder")?.getString() ?: "/data/theory"
                uploadAndSaveFile(theoryFolder, number)
            }
            post("/remove/{id}") {
                val id = call.parameters["id"]?.toInt() ?: error("missing id in request")
            }
            get("/new") {
                call.respondHtml {
                    body {
                        form {
                            p {
                                +"Add new lesson"
                            }
                            textArea {

                            }
                            div {
                                button {
                                    id = "upload-theory-btn"
                                    type = ButtonType.button
                                    +"Add lesson"
                                }
                            }
                            div {
                                input {
                                    type = InputType.file
                                    id = "theory-file"
                                }
                                button {
                                    id = "upload-theory-from-file-btn"
                                    type = ButtonType.button
                                    +"Upload from file"
                                }
                            }
                        }
                        script {
                            src = "theory.js"
                        }
                    }
                }
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.uploadAndSaveFile(path: String, number: Int) {
    val multipart = call.receiveMultipart()
    multipart.forEachPart { part ->
        if(part is PartData.FileItem) {
            val name = part.originalFileName!!
            val fullPath = "$path${File.pathSeparator}$name"
            val file = File(fullPath)

            part.streamProvider().use { its ->
                file.outputStream().buffered().use {
                    its.copyTo(it)
                }
            }
            dao.addTheory(name, number, fullPath)
        }
        part.dispose()
    }
}