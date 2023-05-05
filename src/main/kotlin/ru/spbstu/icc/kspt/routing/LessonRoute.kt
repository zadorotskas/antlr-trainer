package ru.spbstu.icc.kspt.routing

import com.aspose.html.converters.Converter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import ru.spbstu.icc.kspt.AuthName
import ru.spbstu.icc.kspt.CommonRoutes
import ru.spbstu.icc.kspt.build.ParserBuild
import ru.spbstu.icc.kspt.dao
import ru.spbstu.icc.kspt.extension.*
import ru.spbstu.icc.kspt.forms.addLessonForm
import ru.spbstu.icc.kspt.forms.adminLessonForm
import ru.spbstu.icc.kspt.forms.allLessonsForm
import ru.spbstu.icc.kspt.forms.studentLessonForm
import ru.spbstu.icc.kspt.generator.TestGenerator
import ru.spbstu.icc.kspt.generator.TestGeneratorConfig
import ru.spbstu.icc.kspt.model.SolutionState
import ru.spbstu.icc.kspt.model.UserPrincipal
import ru.spbstu.icc.kspt.runner.TestRunner
import java.io.File
import java.nio.file.Path
import kotlin.io.path.readText


internal fun Route.lessonRoute() {
    val config = environment?.config
    route(CommonRoutes.LESSON) {
        authenticate(AuthName.SESSION) {
            get("/all") {
                val lessons = dao.allLessons()
                val principal = call.principal<UserPrincipal>()!!
                call.respondHtml {
                    allLessonsForm(lessons, principal)
                }
            }
            get("/{id}") {
                val lessonId = call.parameters["id"]?.toInt() ?: error("missing id in request")
                val lesson = dao.lesson(lessonId) ?: error("can't find lesson for id: $lessonId")

                val lessonName = lesson.name.replace(" ", "_")
                val filePath = "${config.lessonsPath}${File.separator}${lesson.id}${File.separator}$lessonName"
                val htmlPath = "$filePath.html"
                val mdPath = "$filePath.md"
                Converter.convertMarkdown(mdPath, htmlPath)
                val htmlFile = File(htmlPath)
                val lessonContent = htmlFile.readText().substringAfter("<body>").substringBeforeLast("</body>")

                val src = Path.of(mdPath).readText()
                val flavour = CommonMarkFlavourDescriptor()
                val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(src)
                val html = HtmlGenerator(src, parsedTree, flavour).generateHtml()

                val principal = call.principal<UserPrincipal>()!!
                if (call.isAdmin()) {
                    val progress = dao.getProgress(lessonId)
                    call.respondHtml {
                        adminLessonForm(html, lesson.number, lesson.name, progress, config.testsPath, emptyList(), principal)
                    }
                } else {
                    val lastAttempt = dao.getLastAttempt(call.userName(), lessonId)
                    val message = lastAttempt?.message
                    call.respondHtml {
                        studentLessonForm(html, lesson.number, lesson.name, message, principal)
                    }
                }
                htmlFile.delete()
            }
            post("/solution/{id}") {
                val lessonId = call.parameters["id"]?.toInt() ?: error("missing id in request")
                try {
                    val testsPath = config.testsPath
                    val grammarFile = uploadAndSaveSolution(testsPath, lessonId, call.userName())
                    val jarFile = withContext(Dispatchers.IO) {
                        ParserBuild.buildSolution(grammarFile.parentFile, grammarFile.name.substringBeforeLast("."), config.antlrLibPath)
                    }
                    val result = TestRunner.runAndAssert(
                        jarFile,
                        File(config.lessonsPath).resolve("$lessonId").resolve("test"),
                        grammarFile.parentFile.parentFile.resolve("result")
                    )
                    jarFile.parentFile.parentFile.deleteRecursively()
                    result?.let {
                        dao.updateTaskSolutionState(call.userName(), lessonId, SolutionState.FAILED, result)
                        call.respond(HttpStatusCode.ExpectationFailed, result)
                    } ?: run {
                        dao.updateTaskSolutionState(call.userName(), lessonId, SolutionState.FINISHED, "")
                        call.respond("Finished successfully")
                    }
                } catch (e: RuntimeException) {
                    dao.updateTaskSolutionState(call.userName(), lessonId, SolutionState.FAILED, e.message ?: "")
                    call.respond(HttpStatusCode.ExpectationFailed, e.message ?: "Unknown error")
                }
            }
        }
        authenticate(AuthName.SESSION_ADMIN) {
            post("/upload") {
                val lessonPath = config.lessonsPath
                val testGeneratorConfig = TestGeneratorConfig()
                val grammarFile = uploadAndSaveNewLesson(lessonPath, testGeneratorConfig)
                val jarFile = withContext(Dispatchers.IO) {
                    ParserBuild.buildSolution(grammarFile.parentFile, grammarFile.name.substringBeforeLast("."), config.antlrLibPath)
                }
                if (testGeneratorConfig.needToGenerateTests) {
                    TestGenerator(testGeneratorConfig).generate()
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
                val principal = call.principal<UserPrincipal>()!!
                call.respondHtml {
                    addLessonForm(principal)
                }
            }
        }
    }
}
