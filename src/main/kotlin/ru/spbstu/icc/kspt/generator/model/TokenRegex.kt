package ru.spbstu.icc.kspt.generator.model

import com.github.curiousoddman.rgxgen.RgxGen

class TokenRegex(
    private val regex: String,
    suffix: String? = null
) : Token, TokenWithSuffix(suffix) {
    override fun generate(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(RgxGen(regex).generate())
        }
        return sb.toString()
    }

    override fun generateNot(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(RgxGen(regex).generateNotMatching())
        }
        return sb.toString()
    }
}