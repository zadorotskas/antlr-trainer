package ru.spbstu.icc.kspt.generator.model

import com.github.curiousoddman.rgxgen.RgxGen

class TokenString(
    private val string: String,
    suffix: String? = null
) : Token, TokenWithSuffix(suffix) {
    override fun generate(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(string)
        }
        return sb.toString()
    }

    override fun generateNot(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(RgxGen(string).generateNotMatching())
        }
        return sb.toString()
    }
}