package ru.spbstu.icc.kspt.generator.model


class TokenBlock(
    private val alts: MutableList<Token> = mutableListOf(),
    suffix: String? = null
) : Token, TokenWithSuffix(suffix) {
    override fun generate(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(alts.random().generate())
        }
        return sb.toString()
    }

    override fun generateNot(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(alts.random().generateNot())
        }
        return sb.toString()
    }
}