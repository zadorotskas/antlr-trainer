package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig

class RuleBlockLink(
    private val ruleBlock: RuleBlock,
    suffix: String? = null,
    private var text: String = ""
): Rule, RuleWithSuffix(suffix) {

    override fun generate(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate {
            it.generate(maxDepth, mutationConfig)
        }
    }

    private fun generate(generateFunction: (RuleBlock) -> String): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(generateFunction(ruleBlock))
            sb.append(" ")
        }
        return sb.removeSuffix(" ").toString()
    }

    override fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate {
            it.generateNot(maxDepth, mutationConfig)
        }
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }
}