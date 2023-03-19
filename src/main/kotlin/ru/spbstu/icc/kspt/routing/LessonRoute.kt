package ru.spbstu.icc.kspt.routing

import com.aspose.html.converters.Converter
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import ru.spbstu.icc.kspt.AuthName
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.dao
import ru.spbstu.icc.kspt.extension.lessonsPath
import ru.spbstu.icc.kspt.extension.uploadAndSaveFile
import ru.spbstu.icc.kspt.isAdmin
import java.io.File

internal fun Route.lessonRoute() {
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