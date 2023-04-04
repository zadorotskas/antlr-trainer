package ru.spbstu.icc.kspt.routing

import com.aspose.html.converters.Converter
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.spbstu.icc.kspt.AuthName
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.build.ParserBuild
import ru.spbstu.icc.kspt.dao
import ru.spbstu.icc.kspt.extension.antlrLibPath
import ru.spbstu.icc.kspt.extension.lessonsPath
import ru.spbstu.icc.kspt.extension.uploadAndSaveNewLesson
import ru.spbstu.icc.kspt.forms.addLessonForm
import ru.spbstu.icc.kspt.forms.allLessonsForm
import ru.spbstu.icc.kspt.forms.lessonForm
import ru.spbstu.icc.kspt.isAdmin
import ru.spbstu.icc.kspt.runner.TestRunner
import java.io.File

internal fun Route.lessonRoute() {
    val config = environment?.config
    route(CommonRoutes.LESSON) {
        authenticate(AuthName.SESSION) {
            get("/all") {
                val lessons = dao.allLessons()
                call.respondHtml {
                    allLessonsForm(lessons)
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
                val lessonContent = htmlFile.readText().substringAfter("<body>").substringBeforeLast("</body>")
                call.respondHtml {
                    lessonForm(call.isAdmin(), lessonContent)
                }
                htmlFile.delete()
            }
        }
        authenticate(AuthName.SESSION_ADMIN) {
            post("/upload") {
                val lessonPath = config.lessonsPath
                val grammarFile = uploadAndSaveNewLesson(lessonPath)
                val jarFile = withContext(Dispatchers.IO) {
                    ParserBuild.buildSolution(grammarFile.parentFile, grammarFile.name.substringBeforeLast("."), config.antlrLibPath)
                }
                val result = TestRunner.runAndSave(jarFile, grammarFile.parentFile.parentFile.resolve("test"))
                jarFile.parentFile.parentFile.deleteRecursively()
                result
                    ?.let { call.respond(it) } ?:
                    call.respondRedirect("/lesson/all")
            }
            post("/remove/{id}") {
                val id = call.parameters["id"]?.toInt() ?: error("missing id in request")
                val lesson = dao.lesson(id) ?: error("can't find lesson for id: $id")
                val filePath = "${config.lessonsPath}${File.separator}${lesson.id}"
                if (dao.deleteLesson(id)) {
                    File(filePath).deleteRecursively()
                    call.respondRedirect("lesson/all")
                }
            }
            get("/new") {
                call.respondHtml {
                    addLessonForm()
                }
            }
        }
    }
}
