package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig


class RuleBlock(
    private val alts: MutableList<Rule> = mutableListOf(),
    suffix: String? = null,
    private var text: String = ""
) : Rule, RuleWithSuffix(suffix) {

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

        val separator = if (maxDepth <= -1) "" else " "
        var lastException: IllegalStateException? = null
        alts.shuffled().forEach { alt ->

            try {
                val sb = StringBuffer()
                repeat(suffix.getNumberFromSuffix() ?: return "") {
                    sb.append(generateFunction(alt))
                    sb.append(separator)
                }
                return sb.toString()
            } catch (e: IllegalStateException) {
                lastException = e
                // do nothing, try next
            }
        }

        throw IllegalStateException("Cannot generate $text with depth $maxDepth", lastException)
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }
}