package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig

class RuleAlt(
    private val elements: List<Rule>,
    private var text: String = ""
) : Rule {
    override fun generate(maxDepth: Int, mutationConfig: MutationConfig): String {
        val separator = if (maxDepth <= -1) "" else " "
        return elements.joinToString(separator = separator) { it.generate(maxDepth, mutationConfig) }
    }

    override fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String {
        val separator = if (maxDepth <= -1) "" else " "
        return elements.joinToString(separator = separator) { it.generateNot(maxDepth, mutationConfig) }
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

}