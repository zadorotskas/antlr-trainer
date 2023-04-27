package ru.spbstu.icc.kspt.generator.model

class TokenNot(
    private val elements: List<Token>,
    suffix: String? = null
) : Token, TokenWithSuffix(suffix) {
    override fun generate(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(elements.joinToString(separator = "") { it.generateNot() })
        }
        return sb.toString()
    }

    override fun generateNot(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(elements.joinToString(separator = "") { it.generate() })
        }
        return sb.toString()
    }
}