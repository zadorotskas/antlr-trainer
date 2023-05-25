package ru.spbstu.icc.kspt.extension

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.pipeline.*
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.spbstu.icc.kspt.dao
import ru.spbstu.icc.kspt.dao.DAOFacade
import ru.spbstu.icc.kspt.generator.TestGeneratorConfig
import ru.spbstu.icc.kspt.model.Lesson
import ru.spbstu.icc.kspt.model.SolutionState
import java.io.File
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createTempDirectory

internal class CallExtensionKtTest {

    private lateinit var tempDirForTests: Path

    @BeforeEach
    fun setUp() {
        tempDirForTests = createTempDirectory("antlr-trainer-tests")
        tempDirForTests.createDirectories()
    }

    @Test
    fun `should upload and save solution when upload data from files`() = runTest {
        val userName = "user"
        val lessonId = 42
        val path = tempDirForTests.toString()

        val callMock = mockk<ApplicationCall>(relaxed = true)
        val daoMock = mockk<DAOFacade>(relaxed = true)

        val multipart = mockk<MultiPartData>(relaxed = true)

        var partNumber = 0

        coEvery { multipart.readPart() } answers  {
            when (partNumber++) {
                0 -> mockFileItem("solutionFiles", "test.g4")
                1 -> mockFileItem("solutionFiles", "Main.java")
                2 -> mockFileItem("solutionFiles", "Listener.java")
                3 -> mockFileItem("solutionFiles", "Visitor.java")
                else -> null
            }
        }
        mockkStatic("io.ktor.server.request.ApplicationReceiveFunctionsKt")
        coEvery { callMock.receiveMultipart() } returns multipart

        coEvery { daoMock.getAttemptsCount(userName, lessonId) } returns 1
        coEvery { daoMock.addTaskSolution(userName, lessonId, any(), SolutionState.LOADED, 2, "") } returns mockk()

        mockkStatic(::dao)
        every { dao } returns daoMock

        val context = mockk<PipelineContext<Unit, ApplicationCall>>(relaxed = true)
        coEvery { context.call } returns callMock

        context.uploadAndSaveSolution(path, lessonId, userName)

        val solutionFolder = File("$path${File.separator}${lessonId}${File.separator}${userName.replace(" ", "_")}${File.separator}attempts${File.separator}2${File.separator}solution")
        assertEquals("Main.java", solutionFolder.resolve("Main.java").readText())
        assertEquals("test.g4", solutionFolder.resolve("test.g4").readText())
        assertEquals("Listener.java", solutionFolder.resolve("Listener.java").readText())
        assertEquals("Visitor.java", solutionFolder.resolve("Visitor.java").readText())
    }

    @Test
    fun `should upload and save solution when upload data from text`() = runTest {
        val userName = "user"
        val lessonId = 42
        val path = tempDirForTests.toString()

        val callMock = mockk<ApplicationCall>(relaxed = true)
        val daoMock = mockk<DAOFacade>(relaxed = true)

        val multipart = mockk<MultiPartData>(relaxed = true)

        var partNumber = 0

        coEvery { multipart.readPart() } answers  {
            when (partNumber++) {
                0 -> mockFormItem("g4", "grammar test;")
                1 -> mockFormItem("main", "Main.java")
                2 -> mockFormItem("listener", "Listener.java")
                3 -> mockFormItem("visitor", "Visitor.java")
                else -> null
            }
        }
        mockkStatic("io.ktor.server.request.ApplicationReceiveFunctionsKt")
        coEvery { callMock.receiveMultipart() } returns multipart

        coEvery { daoMock.getAttemptsCount(userName, lessonId) } returns 1
        coEvery { daoMock.addTaskSolution(userName, lessonId, any(), SolutionState.LOADED, 2, "") } returns mockk()

        mockkStatic(::dao)
        every { dao } returns daoMock

        val context = mockk<PipelineContext<Unit, ApplicationCall>>(relaxed = true)
        coEvery { context.call } returns callMock

        context.uploadAndSaveSolution(path, lessonId, userName)

        val solutionFolder = File("$path${File.separator}${lessonId}${File.separator}${userName.replace(" ", "_")}${File.separator}attempts${File.separator}2${File.separator}solution")
        assertEquals("Main.java", solutionFolder.resolve("Main.java").readText())
        assertEquals("grammar test;", solutionFolder.resolve("test.g4").readText())
        assertEquals("Listener.java", solutionFolder.resolve("Listener.java").readText())
        assertEquals("Visitor.java", solutionFolder.resolve("Visitor.java").readText())
    }

    @Test
    fun `should upload and save lesson when upload data from files`() = runTest {
        val path = tempDirForTests.toString()

        val callMock = mockk<ApplicationCall>(relaxed = true)
        val daoMock = mockk<DAOFacade>(relaxed = true)

        val multipart = mockk<MultiPartData>(relaxed = true)

        var partNumber = 0

        coEvery { multipart.readPart() } answers  {
            when (partNumber++) {
                0 -> mockFileItem("solutionFiles", "test.g4")
                1 -> mockFileItem("solutionFiles", "Main.java")
                2 -> mockFileItem("solutionFiles", "Listener.java")
                3 -> mockFileItem("solutionFiles", "Visitor.java")

                4 -> mockFormItem("number", "3")
                5 -> mockFormItem("name", "lesson name")

                6 -> mockFileItem("lesson", "lesson content")

                7 -> mockFormItem("maxDepth", "7")
                8 -> mockFormItem("testNumber", "8")

                9 -> mockFileItem("testFiles", "test1")
                10 -> mockFileItem("testFiles", "test2")
                else -> null
            }
        }
        mockkStatic("io.ktor.server.request.ApplicationReceiveFunctionsKt")
        coEvery { callMock.receiveMultipart() } returns multipart

        val lessonMock = mockk<Lesson>()
        every { lessonMock.id } returns 1

        coEvery { daoMock.addLesson("lesson name", 3) } returns lessonMock

        mockkStatic(::dao)
        every { dao } returns daoMock

        val context = mockk<PipelineContext<Unit, ApplicationCall>>(relaxed = true)
        coEvery { context.call } returns callMock

        val testGeneratorConfig = TestGeneratorConfig()

        context.uploadAndSaveNewLesson(path, testGeneratorConfig)

        val directory = "$path${File.separator}1"

        val solutionFolder = File("$directory${File.separator}solution")
        assertEquals("Main.java", solutionFolder.resolve("Main.java").readText())
        assertEquals("test.g4", solutionFolder.resolve("test.g4").readText())
        assertEquals("Listener.java", solutionFolder.resolve("Listener.java").readText())
        assertEquals("Visitor.java", solutionFolder.resolve("Visitor.java").readText())

        val lessonPath = "$directory${File.separator}lesson_name.md"
        assertEquals("lesson content", File(lessonPath).readText())

        val testDirectory = "$directory${File.separator}test"
        assertEquals("test1", File(testDirectory).resolve("test1").readText())
        assertEquals("test2", File(testDirectory).resolve("test2").readText())

        assertEquals(true, testGeneratorConfig.needToGenerateTests)
        assertEquals(8, testGeneratorConfig.number)
        assertEquals(7, testGeneratorConfig.maxDepth)
        assertEquals(Path.of(testDirectory), testGeneratorConfig.pathForTests)
        assertEquals(solutionFolder.resolve("test.g4").toPath(), testGeneratorConfig.pathWithGrammar)
    }

    @Test
    fun `should upload and save lesson when upload data from text`() = runTest {
        val path = tempDirForTests.toString()

        val callMock = mockk<ApplicationCall>(relaxed = true)
        val daoMock = mockk<DAOFacade>(relaxed = true)

        val multipart = mockk<MultiPartData>(relaxed = true)

        var partNumber = 0

        coEvery { multipart.readPart() } answers  {
            when (partNumber++) {
                0 -> mockFormItem("g4", "grammar test;")
                1 -> mockFormItem("main", "Main.java")
                2 -> mockFormItem("listener", "Listener.java")
                3 -> mockFormItem("visitor", "Visitor.java")

                4 -> mockFormItem("number", "3")
                5 -> mockFormItem("name", "lesson name")

                6 -> mockFormItem("lesson", "lesson content")
                7 -> mockFormItem("maxDepth", "7")
                8 -> mockFormItem("testNumber", "8")
                else -> null
            }
        }
        mockkStatic("io.ktor.server.request.ApplicationReceiveFunctionsKt")
        coEvery { callMock.receiveMultipart() } returns multipart

        val lessonMock = mockk<Lesson>()
        every { lessonMock.id } returns 1

        coEvery { daoMock.addLesson("lesson name", 3) } returns lessonMock

        mockkStatic(::dao)
        every { dao } returns daoMock

        val context = mockk<PipelineContext<Unit, ApplicationCall>>(relaxed = true)
        coEvery { context.call } returns callMock

        val testGeneratorConfig = TestGeneratorConfig()

        context.uploadAndSaveNewLesson(path, testGeneratorConfig)

        val directory = "$path${File.separator}1"

        val solutionFolder = File("$directory${File.separator}solution")
        assertEquals("Main.java", solutionFolder.resolve("Main.java").readText())
        assertEquals("grammar test;", solutionFolder.resolve("test.g4").readText())
        assertEquals("Listener.java", solutionFolder.resolve("Listener.java").readText())
        assertEquals("Visitor.java", solutionFolder.resolve("Visitor.java").readText())

        val lessonPath = "$directory${File.separator}lesson_name.md"
        assertEquals("lesson content", File(lessonPath).readText())

        val testDirectory = "$directory${File.separator}test"
        assertEquals(true, testGeneratorConfig.needToGenerateTests)
        assertEquals(8, testGeneratorConfig.number)
        assertEquals(7, testGeneratorConfig.maxDepth)
        assertEquals(Path.of(testDirectory), testGeneratorConfig.pathForTests)
        assertEquals(solutionFolder.resolve("test.g4").toPath(), testGeneratorConfig.pathWithGrammar)
    }

    private fun mockFileItem(partName: String, fileName: String): PartData.FileItem {
        val res = mockk<PartData.FileItem>(relaxed = true)

        every { res.name } returns partName
        every { res.originalFileName } returns fileName
        mockkStatic("io.ktor.http.content.MultipartJvmKt")
        mockkStatic("kotlin.io.ByteStreamsKt")
        every { res.streamProvider().readBytes() } returns fileName.toByteArray()

        return res
    }

    private fun mockFormItem(partName: String, partValue: String): PartData.FormItem {
        val res = mockk<PartData.FormItem>(relaxed = true)

        every { res.name } returns partName
        every { res.value } returns partValue

        return res
    }

    @AfterEach
    fun cleanUp() {
        tempDirForTests.toFile().deleteRecursively()
    }
}
