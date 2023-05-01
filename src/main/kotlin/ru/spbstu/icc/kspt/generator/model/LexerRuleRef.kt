package ru.spbstu.icc.kspt.generator.model

class LexerRuleRef(
    private val alts: MutableList<Rule> = mutableListOf(),
    private var text: String = ""
) : Rule {

    override fun generate(maxDepth: Int): String {
        if (alts.isEmpty()) return ""
        return alts.random().generate(maxDepth)
    }

    override fun generateNot(maxDepth: Int): String {
        if (alts.isEmpty()) return ""
        return alts.random().generateNot(maxDepth)
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

    fun updateAlts(newAlts: List<Rule>) {
        alts.addAll(newAlts)
    }
}
