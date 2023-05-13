package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig

class RuleRef(
    private val alts: MutableList<Rule> = mutableListOf(),
    var hasLink: Boolean = false,
    private var text: String = ""
) : Rule {
    override fun generate(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate(maxDepth) {
            it.generate(maxDepth, mutationConfig)
        }
    }

    override fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate(maxDepth) {
            it.generateNot(maxDepth, mutationConfig)
        }
    }

    private fun generate(maxDepth: Int, generateFunction: (Rule) -> String): String {
        if (alts.isEmpty()) return ""

        var lastException: IllegalStateException? = null
        alts.shuffled().forEach { alt ->
            try {
                return generateFunction(alt)
            } catch (e: IllegalStateException) {
                lastException = e
                // do nothing, try next
            }
        }

        throw IllegalStateException("Cannot generate $text with depth $maxDepth", lastException)
    }

    fun updateAlts(newAlts: List<Rule>) {
        alts.addAll(newAlts)
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

}