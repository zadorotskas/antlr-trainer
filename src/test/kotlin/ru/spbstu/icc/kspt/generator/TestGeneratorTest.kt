package ru.spbstu.icc.kspt.generator

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.spbstu.icc.kspt.generator.grammar.generated.Test1
import ru.spbstu.icc.kspt.generator.grammar.generated.Test1.*
import ru.spbstu.icc.kspt.generator.grammar.generated.Test2BaseListener
import ru.spbstu.icc.kspt.generator.grammar.generated.Test2Lexer
import ru.spbstu.icc.kspt.generator.grammar.generated.Test2Parser
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
            pathWithGrammar = Path.of("src", "test", "resources", "grammar", "lexer", "Test1.g4"),
            maxDepth = 1,
            number = testsCount,
            numberOfMutations = 0
        )

        generator.generate()

        val generatedFiles = tempDirForTests.listDirectoryEntries()
        assertEquals(testsCount, generatedFiles.size)

        generatedFiles.forEach {
            assertGeneratedTestsForLexer(it)
        }
    }

    private fun assertGeneratedTestsForLexer(testFile: Path) {
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

    @Test
    fun shouldGenerateTestsForParserGrammar() {
        val testsCount = 30
        val generator = TestGenerator(
            pathForTests = tempDirForTests,
            pathWithGrammar = Path.of("src", "test", "resources", "grammar", "parser", "Test2.g4"),
            maxDepth = 10,
            number = testsCount,
            numberOfMutations = 0
        )

        generator.generate()

        val generatedFiles = tempDirForTests.listDirectoryEntries()
        assertEquals(testsCount, generatedFiles.size)

        generatedFiles.forEach {
            assertGeneratedTestsForParser(it)
        }
    }

    @Test
    fun shouldGenerateTestsWithMutationsForParserGrammar() {
        val testsCount = 30
        val generator = TestGenerator(
            pathForTests = tempDirForTests,
            pathWithGrammar = Path.of("src", "test", "resources", "grammar", "parser", "Test2.g4"),
            maxDepth = 10,
            number = 0,
            numberOfMutations = testsCount
        )

        generator.generate()

        val generatedFiles = tempDirForTests.listDirectoryEntries()
        assertEquals(testsCount, generatedFiles.size)

        generatedFiles.forEach {
            assertGeneratedTestsForParser(it)
        }
    }

    private fun assertGeneratedTestsForParser(testFile: Path) {
        val inputStream: CharStream = CharStreams.fromPath(testFile)
        val lexer = Test2Lexer(inputStream)
        val commonTokenStream = CommonTokenStream(lexer)
        val parser = Test2Parser(commonTokenStream)

        val context = parser.program()
        val listener = Test2BaseListener()

        ParseTreeWalker.DEFAULT.walk(listener, context)
    }

    @AfterEach
    fun cleanUp() {
        tempDirForTests.toFile().deleteRecursively()
    }
}