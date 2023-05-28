package ru.spbstu.icc.kspt.routing

import io.ktor.client.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.testing.*
import io.ktor.util.pipeline.*
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.spbstu.icc.kspt.applicationHttpClient
import ru.spbstu.icc.kspt.build.ParserBuild
import ru.spbstu.icc.kspt.dao
import ru.spbstu.icc.kspt.dao.DAOFacade
import ru.spbstu.icc.kspt.extension.uploadAndSaveNewLesson
import ru.spbstu.icc.kspt.extension.uploadAndSaveSolution
import ru.spbstu.icc.kspt.model.*
import ru.spbstu.icc.kspt.runner.TestRunner
import java.io.File
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createTempDirectory
import kotlin.io.path.writeText

internal class LessonRouteKtTest {

    private lateinit var httpClient: HttpClient
    private lateinit var daoMock: DAOFacade
    private lateinit var tempPath: Path

    @BeforeEach
    fun setUp() {
        mockkStatic(::applicationHttpClient)

        daoMock = mockk(relaxed = true)
        mockkStatic(::dao)
        every { dao } returns daoMock

        tempPath = createTempDirectory("antlr-trainer-test")
        tempPath.createDirectories()
    }

    @Test
    fun allLessonsRoute() = testApplication {
        initClient()
        initPrincipal(UserRole.STUDENT)
        coEvery { daoMock.allLessons() } returns emptyList()


        val response = httpClient.get("/lesson/all")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("HttpResponse[http://localhost/lesson/all, 200 OK]", response.toString())
    }

    @Test
    fun getOneLessonForStudent() = testApplication {
        initClient()
        initPrincipal(UserRole.STUDENT)

        mockkStatic("java.nio.file.Path")
        val testFile = tempPath
            .resolve("test.md")

        testFile.writeText("* test")

        every { Path.of(match<String> { it.endsWith("md") }) } returns testFile

        val lastAttempt = mockk<TaskSolution>(relaxed = true)
        coEvery { daoMock.getLastAttempt("", 3) } returns lastAttempt
        every { lastAttempt.message } returns "test message"

        val response = httpClient.get("/lesson/3")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("HttpResponse[http://localhost/lesson/3, 200 OK]", response.toString())
    }

    @Test
    fun getOneLessonForAdmin() = testApplication {
        initClient()
        initPrincipal(UserRole.ADMIN)

        mockkStatic("java.nio.file.Path")
        val testFile = tempPath
            .resolve("test.md")

        testFile.writeText("* test")

        every { Path.of(match<String> { it.endsWith("md") }) } returns testFile

        val lastAttempt = mockk<TaskSolution>(relaxed = true)
        coEvery { daoMock.getProgress(3) } returns emptyList()
        every { lastAttempt.message } returns "test message"

        val response = httpClient.get("/lesson/3")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("HttpResponse[http://localhost/lesson/3, 200 OK]", response.toString())
    }

    @Test
    fun postSuccessSolutionForStudent() = testApplication {
        initClient()
        initPrincipal(UserRole.STUDENT)

        mockkStatic("java.nio.file.Path")
        val testFile = tempPath
            .resolve("test.md")

        testFile.writeText("* test")

        every { Path.of(match<String> { it.endsWith("md") }) } returns testFile

        mockkStatic("ru.spbstu.icc.kspt.extension.CallExtensionKt")
        coEvery { any<PipelineContext<Unit, ApplicationCall>>().uploadAndSaveSolution(any(), any(), any()) } returns mockk(relaxed = true)

        val jarFile = mockk<File>(relaxed = true)

        mockkObject(ParserBuild)
        every { ParserBuild.buildSolution(any(), any(), any()) } returns jarFile

        mockkObject(TestRunner)
        coEvery { TestRunner.runAndAssert(any(), any(), any()) } returns null

        mockkStatic("kotlin.io.FilesKt")
        every { jarFile.parentFile.parentFile.deleteRecursively() } returns true

        coEvery { daoMock.updateTaskSolutionState("", 3, SolutionState.FINISHED,"") } returns true

        val response = httpClient.post("lesson/solution/3")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Finished successfully", response.bodyAsText())
    }

    @Test
    fun postFailedSolutionForStudent() = testApplication {
        initClient()
        initPrincipal(UserRole.STUDENT)

        mockkStatic("java.nio.file.Path")
        val testFile = tempPath
            .resolve("test.md")

        testFile.writeText("* test")

        every { Path.of(match<String> { it.endsWith("md") }) } returns testFile

        mockkStatic("ru.spbstu.icc.kspt.extension.CallExtensionKt")
        coEvery { any<PipelineContext<Unit, ApplicationCall>>().uploadAndSaveSolution(any(), any(), any()) } returns mockk(relaxed = true)

        val jarFile = mockk<File>(relaxed = true)

        mockkObject(ParserBuild)
        every { ParserBuild.buildSolution(any(), any(), any()) } returns jarFile

        mockkObject(TestRunner)
        coEvery { TestRunner.runAndAssert(any(), any(), any()) } returns "failed"

        mockkStatic("kotlin.io.FilesKt")
        every { jarFile.parentFile.parentFile.deleteRecursively() } returns true

        coEvery { daoMock.updateTaskSolutionState("", 3, SolutionState.FINISHED,"failed") } returns true

        val response = httpClient.post("lesson/solution/3")
        assertEquals(HttpStatusCode.ExpectationFailed, response.status)
        assertEquals("failed", response.bodyAsText())
    }

    @Test
    fun postSolutionForStudentThrowsException() = testApplication {
        initClient()
        initPrincipal(UserRole.STUDENT)

        mockkStatic("java.nio.file.Path")
        val testFile = tempPath
            .resolve("test.md")

        testFile.writeText("* test")

        every { Path.of(match<String> { it.endsWith("md") }) } returns testFile

        mockkStatic("ru.spbstu.icc.kspt.extension.CallExtensionKt")
        coEvery { any<PipelineContext<Unit, ApplicationCall>>().uploadAndSaveSolution(any(), any(), any()) } throws RuntimeException("error")

        val response = httpClient.post("lesson/solution/3")
        assertEquals(HttpStatusCode.ExpectationFailed, response.status)
        assertEquals("error", response.bodyAsText())
    }

    @Test
    fun uploadLesson() = testApplication {
        initClient()
        initPrincipal(UserRole.ADMIN)

        mockkStatic("ru.spbstu.icc.kspt.extension.CallExtensionKt")
        coEvery { any<PipelineContext<Unit, ApplicationCall>>().uploadAndSaveNewLesson(any(), any()) } returns mockk(relaxed = true)

        val jarFile = mockk<File>(relaxed = true)

        mockkObject(ParserBuild)
        every { ParserBuild.buildSolution(any(), any(), any()) } returns jarFile

        mockkObject(TestRunner)
        coEvery { TestRunner.runAndSave(any(), any()) } returns null

        mockkStatic("kotlin.io.FilesKt")
        every { jarFile.parentFile.parentFile.deleteRecursively() } returns true

        val response = httpClient.post("lesson/upload")
        assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun uploadLessonFails() = testApplication {
        initClient()
        initPrincipal(UserRole.ADMIN)

        mockkStatic("ru.spbstu.icc.kspt.extension.CallExtensionKt")
        coEvery { any<PipelineContext<Unit, ApplicationCall>>().uploadAndSaveNewLesson(any(), any()) } returns mockk(relaxed = true)

        val jarFile = mockk<File>(relaxed = true)

        mockkObject(ParserBuild)
        every { ParserBuild.buildSolution(any(), any(), any()) } returns jarFile

        mockkObject(TestRunner)
        coEvery { TestRunner.runAndSave(any(), any()) } returns "error"

        mockkStatic("kotlin.io.FilesKt")
        every { jarFile.parentFile.parentFile.deleteRecursively() } returns true

        val response = httpClient.post("lesson/upload")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("error", response.bodyAsText())
    }

    @Test
    fun removeLesson() = testApplication {
        initClient()
        initPrincipal(UserRole.ADMIN)

        val lesson = mockk<Lesson>(relaxed = true)
        coEvery { daoMock.lesson(3) } returns lesson
        every { lesson.id } returns 103

        coEvery { daoMock.deleteLesson(3) } returns true

        val response = httpClient.post("lesson/remove/3")
        assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun createNewLesson() = testApplication {
        initClient()
        initPrincipal(UserRole.ADMIN)

        val response = httpClient.get("lesson/new")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("HttpResponse[http://localhost/lesson/new, 200 OK]", response.toString())
    }

    private fun ApplicationTestBuilder.initClient() {
        httpClient = createClient {
            install(HttpCookies)
        }
        every { applicationHttpClient } returns httpClient
    }

    private suspend fun ApplicationTestBuilder.initPrincipal(role: UserRole) {
        routing {
            get("/login-test") {
                call.sessions.set(UserPrincipal(name = "", role = role, state = "", token = ""))
            }
        }
        httpClient.get("/login-test")
    }

    @AfterEach
    fun tearDown() {
        tempPath.toFile().deleteRecursively()
    }
}