package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig

class LexerRuleRef(
    private val alts: MutableList<Rule> = mutableListOf(),
    private var text: String = ""
) : Rule {

    override fun generate(maxDepth: Int, mutationConfig: MutationConfig): String {
        if (alts.isEmpty()) return ""
        return alts.random().generate(maxDepth, mutationConfig)
    }

    override fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String {
        if (alts.isEmpty()) return ""
        return alts.random().generateNot(maxDepth, mutationConfig)
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
