package ru.spbstu.icc.kspt.generator.model

class TokenRefLink(
    private val tokenRef: TokenRef,
    suffix: String? = null
) : Token, TokenWithSuffix(suffix) {
    override fun generate(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(tokenRef.generate())
        }
        return sb.toString()
    }

    override fun generateNot(): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(tokenRef.generateNot())
        }
        return sb.toString()
    }
}