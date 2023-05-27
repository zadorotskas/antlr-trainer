package ru.spbstu.icc.kspt.runner

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.InputStream
import java.nio.file.Path
import kotlin.io.path.*


internal class TestRunnerTest {
    private lateinit var tempPath: Path

    @BeforeEach
    fun setUp() {
        tempPath = createTempDirectory("antlr-trainer-test")
        tempPath.createDirectories()
    }

    @Test
    internal fun `should run and save tests`() = runBlocking {
        // given
        val jar = tempPath
            .resolve("test.jar")
            .createFile()
            .toFile()

        val dataPath = tempPath
            .resolve("data")
            .createDirectories()

        dataPath
            .resolve("test.in")
            .createFile()
            .writeText("test input")

        val process = mockk<Process>(relaxed = true)
        mockkStatic("java.lang.Runtime")
        every { Runtime.getRuntime().exec("java -jar $jar") } returns process
        every { Runtime.getRuntime().exit(0) } just runs

        every { process.outputStream.write(any<ByteArray>()) } just runs
        every { process.outputStream.close() } just runs
        mockkStatic("kotlin.io.ByteStreamsKt")
        every { process.inputStream.readBytes() } returns "test output".toByteArray()
        every { process.inputStream.close() } just runs

        every { process.waitFor(any(), any()) } returns true

        every { process.errorStream } returns InputStream.nullInputStream()

        // when
        val result = TestRunner.runAndSave(jar, dataPath.toFile())

        // then
        assertNull(result)

        val outputFile = dataPath
            .resolve("test.out")

        assertEquals("test output", outputFile.readText())
    }

    @Test
    internal fun `should run and assert tests`() = runBlocking {
        // given
        val jar = tempPath
            .resolve("test.jar")
            .createFile()
            .toFile()

        val correctSolutionPath = tempPath
            .resolve("correct").createDirectories()

        correctSolutionPath
            .resolve("test.in")
            .createFile()
            .writeText("test input")

        correctSolutionPath
            .resolve("test.out")
            .createFile()
            .writeText("test output")

        val outputSolutionPath = tempPath
            .resolve("output")
            .createDirectories()

        val process = mockk<Process>(relaxed = true)
        mockkStatic("java.lang.Runtime")
        every { Runtime.getRuntime().exec("java -jar $jar") } returns process
        every { Runtime.getRuntime().exit(0) } just runs

        every { process.outputStream.write(any<ByteArray>()) } just runs
        every { process.outputStream.close() } just runs
        mockkStatic("kotlin.io.ByteStreamsKt")
        every { process.inputStream.readBytes() } returns "".toByteArray()
        every { process.inputStream.close() } just runs

        every { process.waitFor(any(), any()) } returns false
        every { process.destroy() } just runs

        every { process.errorStream } returns "mistake".byteInputStream()

        // when
        val result = TestRunner.runAndAssert(jar, correctSolutionPath.toFile(), outputSolutionPath.toFile())

        // then
        val outputFile = outputSolutionPath
            .resolve("test.out")

        assertEquals("mistake", outputFile.readText())
        assertEquals("Tests failed: \n expected: \"test output\", actual: \"mistake\"", result)
    }

    @AfterEach
    fun tearDown() {
        tempPath.toFile().deleteRecursively()
    }
}