package ru.spbstu.icc.kspt.generator.model

class TokenRef(
    private val alts: MutableList<Token> = mutableListOf()
) : Token {

    override fun generate(): String {
        if (alts.isEmpty()) return ""
        return alts.random().generate()
    }

    override fun generateNot(): String {
        if (alts.isEmpty()) return ""
        return alts.random().generateNot()
    }

    fun updateAlts(newAlts: List<Token>) {
        alts.addAll(newAlts)
    }
}
