package ru.spbstu.icc.kspt.generator

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.spbstu.icc.kspt.generator.grammar.ANTLRv4Lexer
import ru.spbstu.icc.kspt.generator.grammar.ANTLRv4Parser
import ru.spbstu.icc.kspt.generator.grammar.ANTLRv4ParserTestGeneratorVisitor
import ru.spbstu.icc.kspt.generator.model.LexerRuleRef
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
                file.writeText(visitor.tokens.values.generateAllRules())
            }
        } else {
            val topLevelRule = visitor.rules
                .filterNot { it.value.hasLink }
            if (topLevelRule.size != 1) error("Grammar should have 1 unused rule")

            val ruleForGeneration = topLevelRule.values.first()
            repeat(number) {
                val file = pathForTests.resolve("generated_test_$it.in")
                file.deleteIfExists()
                file.writeText(ruleForGeneration.generate(maxDepth))
            }
        }
    }

    private fun MutableCollection<LexerRuleRef>.generateAllRules(): String {
        return this.shuffled().joinToString(separator = "") { it.generate(-1) }
    }
}
