package ru.spbstu.icc.kspt.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import com.aspose.html.converters.Converter
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.config.*
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
                resource("newLesson.js")
                resource("lesson.js")
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

private fun ApplicationCall.isAdmin() = this.sessions.get<UserPrincipal>()?.role == UserRole.ADMIN

internal fun Route.theoryRoute() {
    val config = environment?.config
    route(CommonRoutes.THEORY) {
        authenticate(AuthName.SESSION) {
            get("/all") {
                val lessons = dao.allLessons()
                call.respondHtml {
                    head {
                        title = "Lessons"
                    }
                    body {
                        h1 {
                            +"Lessons:"
                        }
                        lessons.forEach { lesson ->
//                            if (call.isAdmin()) {
//                                postButton {
//                                    type = ButtonType.button
//                                    id = "delete-lesson-btn"
//                                    +"Delete lesson"
//                                    para
//                                }
//                            }
                            a {
                                href = "/theory/${lesson.id}"
                                +"Lesson ${lesson.number}: ${lesson.name}"
                            }
                            br
                        }
                    }
                }
            }
            get("/{id}") {
                val lessonId = call.parameters["id"]?.toInt() ?: error("missing id in request")
                val lesson = dao.lesson(lessonId) ?: error("can't find lesson for id: $lessonId")

                val filePath = "${config.lessonsPath}${File.separator}${lesson.id}${File.separator}${lesson.name.replace(" ", "_")}"
                val htmlPath = "$filePath.html"
                val mdPath = "$filePath.md"
                Converter.convertMarkdown(mdPath, htmlPath)
                val htmlFile = File(htmlPath)
                call.respondHtml {
                    head {
                        title = "Lesson"
                    }
                    body {
                        if (call.isAdmin()) {
                            div {
                                button {
                                    type = ButtonType.button
                                    id = "delete-lesson-btn"
                                    +"Delete lesson"
                                }
                            }
                            br
                        }
                        unsafe {
                            +htmlFile.readText().substringAfter("<body>").substringBeforeLast("</body>")
                        }
                        script {
                            src = "lesson.js"
                        }
                    }
                }
                htmlFile.delete()
            }
        }
        authenticate(AuthName.SESSION_ADMIN) {
            post("/upload") {
                uploadAndSaveFile(config.lessonsPath)
                call.respondRedirect("/theory/all")
            }
            post("/remove/{id}") {
                val id = call.parameters["id"]?.toInt() ?: error("missing id in request")
                val lesson = dao.lesson(id) ?: error("can't find lesson for id: $id")
                val filePath = "${config.lessonsPath}${File.separator}${lesson.id}"
                if (dao.deleteLesson(id)) {
                    File(filePath).deleteRecursively()
                    call.respondRedirect("theory/all")
                }
            }
            get("/new") {
                call.respondHtml {
                    body {
                        form {
                            p {
                                +"Add new lesson"
                            }
                            div {
                                input {
                                    type = InputType.text
                                    id = "lesson-number-input"
                                    placeholder = "Number"
                                }
                                input {
                                    type = InputType.text
                                    id = "lesson-name-input"
                                    placeholder = "Name"
                                }
                            }
                            textArea {
                                id = "lesson-text-area"
                                placeholder = "Lesson content"
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
                            src = "newLesson.js"
                        }
                    }
                }
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.uploadAndSaveFile(path: String) {
    val multipart = call.receiveMultipart()
    var fileBytesParam: ByteArray? = null
    var fileNameParam: String? = null
    var numberParam: Int? = null
    var lessonParam: String? = null

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FileItem -> {
                fileBytesParam = part.streamProvider().readBytes()
            }

            is PartData.FormItem -> {
                when (part.name) {
                    "number" -> numberParam = part.value.toInt()
                    "name" -> fileNameParam = part.value
                    "lesson" -> lessonParam = part.value
                }
            }

            else -> {}
        }
        part.dispose()
    }

    val fileName = fileNameParam ?: error("does not receive file name")
    val number = numberParam ?: error("does not receive number")

    val lesson = dao.addLesson(fileName, number) ?: error("cant save file info in database")
    val directory = "$path${File.separator}${lesson.id}"
    File(directory).mkdirs()
    val file = File("$directory${File.separator}${fileName.replace(" ", "_")}.md")
    fileBytesParam?.let {
        file.writeBytes(it)
    } ?: lessonParam?.let {
        file.writeText(it)
    } ?: error("does not receive file")
}

private val ApplicationConfig?.lessonsPath: String
    get() = System.getProperty("user.dir") + (this?.propertyOrNull("data.lessonsFolder")?.getString() ?: "/data/lessons")
