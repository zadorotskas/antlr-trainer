package ru.spbstu.icc.kspt.generator

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.spbstu.icc.kspt.generator.grammar.ANTLRv4Lexer
import ru.spbstu.icc.kspt.generator.grammar.ANTLRv4Parser
import ru.spbstu.icc.kspt.generator.grammar.ANTLRv4ParserTestGeneratorVisitor
import ru.spbstu.icc.kspt.generator.model.TokenRef
import java.nio.file.Path
import kotlin.io.path.deleteIfExists
import kotlin.io.path.writeText

class TestGenerator(
    private val pathForTests: Path,
    private val pathWithGrammar: Path,
    val maxDepth: Int,
    val number: Int
) {
    fun generate() {
        val inputStream = CharStreams.fromPath(pathWithGrammar)
        val lexer = ANTLRv4Lexer(inputStream)
        val commonTokenStream = CommonTokenStream(lexer)
        val parser = ANTLRv4Parser(commonTokenStream)

        val context = parser.grammarSpec()
        val visitor = ANTLRv4ParserTestGeneratorVisitor()

        visitor.visitGrammarSpec(context)
        if (visitor.isLexerGrammar) {
            repeat(number) {
                val file = pathForTests.resolve("generated_test_$it.in")
                file.deleteIfExists()
                file.writeText(visitor.tokens.values.generateAllTokens())
            }
        }
    }

    private fun MutableCollection<TokenRef>.generateAllTokens(): String {
        return this.shuffled().joinToString(separator = "") { it.generate() }
    }
}
