package ru.spbstu.icc.kspt.build

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Path
import java.util.jar.Attributes
import java.util.jar.JarFile
import kotlin.io.path.createDirectories
import kotlin.io.path.createTempDirectory

internal class JarBuilderTest {
    private lateinit var tempPath: Path

    @BeforeEach
    fun setUp() {
        tempPath = createTempDirectory("antlr-trainer-test")
        tempPath.createDirectories()
    }

    @Test
    fun `happy path`() {
        val jarBuilder = JarBuilder()

        val jarPath = tempPath.resolve("output").also { it.createDirectories() }.resolve("test.jar").toFile()
        val inputDir = tempPath.resolve("input").toFile().also { it.mkdir() }
        val inputFile = inputDir.resolve("Main.class").also { it.createNewFile() }

        inputFile.writeText("""
            public class Main {
            public static void main(String[] args) throws IOException {
                 System.out.println("Hello, World!");   
            }
        """.trimIndent())

        jarBuilder.use {
            it.setMainClass("Main")
            it.openJar(jarPath)
            it.add(
                inputFile,
                inputDir
            )
            it.close()
        }

        val jarFile = JarFile(jarPath)
        assertEquals("Main", jarFile.manifest.mainAttributes[Attributes.Name.MAIN_CLASS])
        assertEquals("1.0", jarFile.manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION])

        jarFile.entries().asIterator().forEach {
            if (!it.name.startsWith("META-INF")) {
                assertEquals("Main.class", it.name)
            }
        }
    }

    @Test
    fun `test add jar`() {
        val firstJarBuilder = JarBuilder()
        val firstJar = tempPath.resolve("first")
        val firstJarPath = firstJar.resolve("output").also { it.createDirectories() }.resolve("first.jar").toFile()
        val firstInputDir = firstJar.resolve("input").toFile().also { it.mkdir() }
        val firstInputFile = firstInputDir.resolve("Test.class").also { it.createNewFile() }
        
        firstInputFile.writeText("""
            public class Test {
            public static void main(String[] args) throws IOException {
                 System.out.println("Hello, World!");   
            }
        """.trimIndent())

        firstJarBuilder.use {
            it.openJar(firstJarPath)
            it.add(
                firstInputFile,
                firstInputDir
            )
            it.close()
        }

        val secondJarBuilder = JarBuilder()
        val secondJar = tempPath.resolve("second")
        val secondJarPath = secondJar.resolve("output").also { it.createDirectories() }.resolve("second.jar").toFile()
        val secondInputDir = secondJar.resolve("input").toFile().also { it.mkdir() }
        val secondInputFile = secondInputDir.resolve("Main.class").also { it.createNewFile() }

        secondInputFile.writeText("""
            public class Main {
            public static void main(String[] args) throws IOException {
                 System.out.println("Hello, World!");   
            }
        """.trimIndent())

        secondJarBuilder.use {
            it.setMainClass("Main")
            it.openJar(secondJarPath)
            it.add(
                secondInputDir,
                secondInputDir
            )
            it.addJar(firstJarPath)
            it.close()
        }

        val jarFile = JarFile(secondJarPath)
        assertEquals("Main", jarFile.manifest.mainAttributes[Attributes.Name.MAIN_CLASS])
        assertEquals("1.0", jarFile.manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION])

        val entriesIterator = jarFile.entries().asIterator()
        repeat(2) {
            entriesIterator.next()
        }
        val first = entriesIterator.next()
        assertEquals("Main.class", first.name)
        val second = entriesIterator.next()
        assertEquals("Test.class", second.name)
    }

    @AfterEach
    fun tearDown() {
        tempPath.toFile().deleteRecursively()
    }
}