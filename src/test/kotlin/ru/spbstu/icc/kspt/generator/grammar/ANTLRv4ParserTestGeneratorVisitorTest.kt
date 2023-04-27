package ru.spbstu.icc.kspt.generator.grammar

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ANTLRv4ParserTestGeneratorVisitorTest {
    
    @Test
    fun shouldGenerateForLexerRules() {
        val inputStream = CharStreams.fromString(
            "lexer grammar Example1_1;\n" +
                    "\n" +
                    "Num         :   [0-9]+;\n" +
                    "Indif       :   [a-zA-Z]+;\n" +
                    "Other       :   ~[0-9a-zA-Z]+;\n" +
                    "Test1       :   [a-zA-Z]+ [0-9]+;\n" +
                    "Test2       :   ([a-zA-Z]+ | [0-9]+);\n" +
                    "Test3       :   ~\'A\';\n" +
                    "Test4       :   ~([a-zA-Z]+ | [0-9]+);\n" +
                    "Test5       :   \'TEST\';\n" +
                    "Test7       :   \'A\'*;\n" +
                    "Test8       :   Test7;\n" +
                    "Test9       :   Test6+;\n" +
                    "Test6       :   \'A\'?;\n"
        )
        val lexer = ANTLRv4Lexer(inputStream)
        val commonTokenStream = CommonTokenStream(lexer)
        val parser = ANTLRv4Parser(commonTokenStream)

        val context = parser.grammarSpec()

        val visitor = ANTLRv4ParserTestGeneratorVisitor()
        visitor.visitGrammarSpec(context)

        assertTrue(visitor.isLexerGrammar)
        repeat(50) {
            val result = visitor
                .tokens
                .mapValues { it.value.generate() }

            assertEquals(13, result.size)
            assertTrue(Regex("[0-9]+").matches(result["Num"]!!))
            assertTrue(Regex("[a-zA-Z]+").matches(result["Indif"]!!))
            assertTrue(Regex("[^0-9a-zA-Z]+").matches(result["Other"]!!))
            assertTrue(Regex("[a-zA-Z]+[0-9]+").matches(result["Test1"]!!))
            assertTrue(Regex("([a-zA-Z]+|[0-9]+)").matches(result["Test2"]!!))
            assertTrue(Regex("[^A]").matches(result["Test3"]!!))
            assertFalse(Regex("([a-zA-Z]+|[0-9]+)").matches(result["Test4"]!!))
            assertEquals("TEST", result["Test5"]!!)
            assertTrue(Regex("A?").matches(result["Test6"]!!))
            assertTrue(Regex("A*").matches(result["Test7"]!!))
            assertTrue(Regex("A*").matches(result["Test8"]!!))
            assertTrue(Regex("(A?)+").matches(result["Test9"]!!))
        }
    }

    @Test
    fun shouldGenerateForLexerRulesWithAlts() {
        val inputStream = CharStreams.fromString(
            "lexer grammar Example1_1;\n" +
                    "\n" +
                    "Num         :   [0-9]+\n" +
                    "            |   [<>]\n" +
                    "            ;\n"
        )
        val lexer = ANTLRv4Lexer(inputStream)
        val commonTokenStream = CommonTokenStream(lexer)
        val parser = ANTLRv4Parser(commonTokenStream)

        val context = parser.grammarSpec()

        val visitor = ANTLRv4ParserTestGeneratorVisitor()
        visitor.visitGrammarSpec(context)

        assertTrue(visitor.isLexerGrammar)
        repeat(50) {
            val result = visitor.tokens.mapValues { it.value.generate() }

            assertEquals(2, result.size)
            val first = Regex("[0-9]+").matches(result["Num"]!!)
            val second = Regex("[<>]").matches(result["Num"]!!)
            assertTrue(first || second)
        }
    }
}