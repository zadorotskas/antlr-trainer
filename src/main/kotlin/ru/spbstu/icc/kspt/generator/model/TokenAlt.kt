package ru.spbstu.icc.kspt.generator.model

class TokenAlt(
    private val elements: List<Token>
) : Token {
    override fun generate(): String {
        return elements.joinToString(separator = "") { it.generate() }
    }

    override fun generateNot(): String {
        return elements.joinToString(separator = "") { it.generateNot() }
    }
}