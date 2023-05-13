package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig

class RuleNot(
    private val elements: List<Rule>,
    suffix: String? = null,
    private var text: String = ""
) : Rule, RuleWithSuffix(suffix) {
    override fun generate(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate(maxDepth) {
            it.generateNot(maxDepth, mutationConfig)
        }
    }

    override fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate(maxDepth) {
            it.generate(maxDepth, mutationConfig)
        }
    }

    private fun generate(maxDepth: Int, generateFunction: (Rule) -> String): String {
        val separator = if (maxDepth <= -1) "" else " "
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(elements.joinToString(separator = separator) { generateFunction(it) })
            sb.append(" ")
        }
        return sb.removeSuffix(" ").toString()
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

}