package ru.spbstu.icc.kspt.generator

import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.spbstu.icc.kspt.generator.grammar.generated.Test1
import ru.spbstu.icc.kspt.generator.grammar.generated.Test1.*
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createTempDirectory
import kotlin.io.path.listDirectoryEntries

internal class TestGeneratorTest {

    private lateinit var tempDirForTests: Path

    @BeforeEach
    fun setUp() {
        tempDirForTests = createTempDirectory("antlr-trainer-tests")
        tempDirForTests.createDirectories()
    }

    @Test
    fun shouldGenerateTestsForLexerGrammar() {
        val testsCount = 30
        val generator = TestGenerator(
            pathForTests = tempDirForTests,
            pathWithGrammar = Path.of("src", "test", "resources", "grammar", "lexer", "Test1.g4"), //File(this::class.java.getResource("grammar")!!.path).toPath().resolve("lexer").resolve("Test1.g4"),
            maxDepth = 1,
            number = testsCount
        )

        generator.generate()

        val generatedFiles = tempDirForTests.listDirectoryEntries()
        assertEquals(testsCount, generatedFiles.size)

        generatedFiles.forEach {
            assertGeneratedTests(it)
        }
    }

    private fun assertGeneratedTests(testFile: Path) {
        var numCount = 0
        var indifCount = 0
        var otherCount = 0

        val lexer = Test1(CharStreams.fromPath(testFile))
        while (true) {
            val token = lexer.nextToken()
            when {
                token.type == Num -> numCount++
                token.type == Indif -> indifCount++
                token.type == Other -> otherCount++
                token.text == "<EOF>" -> break
            }
        }

        assertEquals(1, numCount)
        assertEquals(1, indifCount)
        assertEquals(1, otherCount)
    }

    @AfterEach
    fun cleanUp() {
        tempDirForTests.toFile().deleteRecursively()
    }
}