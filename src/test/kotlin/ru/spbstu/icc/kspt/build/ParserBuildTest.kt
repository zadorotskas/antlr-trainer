package ru.spbstu.icc.kspt.build

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.InputStream
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile
import kotlin.io.path.createTempDirectory
import kotlin.io.path.writeText

internal class ParserBuildTest {
    private lateinit var tempPath: Path

    @BeforeEach
    fun setUp() {
        tempPath = createTempDirectory("antlr-trainer-test")
        tempPath.createDirectories()
    }

    @Test
    internal fun `should fail if cannot build antlr grammar`() {
        // given
        val solutionDirectory = tempPath
            .resolve("solution")
            .createDirectories()
            .toFile()

        val antlrLibPath = "dummyPath"

        val process = mockk<Process>(relaxed = true)
        mockkStatic("java.lang.Runtime")
        every { Runtime.getRuntime().exec(match<String> { it.startsWith("java -cp $antlrLibPath org.antlr.v4.Tool") }) } returns process
        every { process.waitFor() } returns 0
        every { process.errorStream } returns "test error".byteInputStream()
        every { Runtime.getRuntime().exit(0) } just runs

        // when
        val exception = assertThrows<IllegalStateException> {
            ParserBuild.buildSolution(solutionDirectory, "test", antlrLibPath)
        }

        // then
        assertEquals("antlr run failed with message: test error", exception.message)
    }

    @Test
    internal fun `should fail if javac run fails`() {
        // given
        val solutionDirectory = tempPath
            .resolve("solution")
            .createDirectories()

        solutionDirectory
            .resolve("Main.java")
            .createFile()

        solutionDirectory
            .resolve("Listener.java")
            .createFile()

        solutionDirectory
            .resolve("Visitor.java")
            .createFile()

        val antlrLibPath = "dummyPath"

        val antlrProcess = mockk<Process>(relaxed = true)
        mockkStatic("java.lang.Runtime")
        every { Runtime.getRuntime().exit(0) } just runs
        every { Runtime.getRuntime().exec(match<String> { it.startsWith("java -cp $antlrLibPath org.antlr.v4.Tool") }) } returns antlrProcess
        every { antlrProcess.waitFor() } returns 0
        every { antlrProcess.errorStream } returns InputStream.nullInputStream()

        val javacProcess = mockk<Process>(relaxed = true)
        every { Runtime.getRuntime().exec(match<String> { it.startsWith("javac -cp $antlrLibPath") }) } returns javacProcess
        every { javacProcess.waitFor() } returns 0
        every { javacProcess.errorStream } returns "test error".byteInputStream()

        // when
        val exception = assertThrows<IllegalStateException> {
            ParserBuild.buildSolution(solutionDirectory.toFile(), "test", antlrLibPath)
        }

        // then
        assertEquals("javac run failed with message: test error", exception.message)
    }

    @Test
    internal fun `should build solution`() {
        // given
        val solutionDirectory = tempPath
            .resolve("solution")
            .createDirectories()

        solutionDirectory
            .resolve("Main.java")
            .createFile()
            .writeText("Main java")

        solutionDirectory
            .resolve("Listener.java")
            .createFile()
            .writeText("Listener java")

        solutionDirectory
            .resolve("Visitor.java")
            .createFile()
            .writeText("Visitor java")

        val antlrLibPath = "dummyPath"

        val antlrProcess = mockk<Process>(relaxed = true)
        mockkStatic("java.lang.Runtime")
        every { Runtime.getRuntime().exit(0) } just runs
        every { Runtime.getRuntime().exec(match<String> { it.startsWith("java -cp $antlrLibPath org.antlr.v4.Tool") }) } returns antlrProcess
        every { antlrProcess.waitFor() } returns 0
        every { antlrProcess.errorStream } returns InputStream.nullInputStream()

        val javacProcess = mockk<Process>(relaxed = true)
        every { Runtime.getRuntime().exec(match<String> { it.startsWith("javac -cp $antlrLibPath") }) } returns javacProcess
        every { javacProcess.waitFor() } returns 0
        every { javacProcess.errorStream } returns InputStream.nullInputStream()

        mockkConstructor(JarBuilder::class)
        every { anyConstructed<JarBuilder>().use {
            it.setMainClass("Main")
            it.openJar(any())
            it.add(
                any(),
                any()
            )
            it.addJar(any())
            it.close()
        } } just runs

        // when
        val result = ParserBuild.buildSolution(solutionDirectory.toFile(), "test", antlrLibPath)

        // then
        val tempDirForJar = result.parentFile.parentFile
        assertTrue(tempDirForJar.resolve("Main.java").exists())
        assertTrue(tempDirForJar.resolve("Listener.java").exists())
        assertTrue(tempDirForJar.resolve("Visitor.java").exists())
        tempDirForJar.deleteRecursively()
    }

    @AfterEach
    fun tearDown() {
        tempPath.toFile().deleteRecursively()
    }
}