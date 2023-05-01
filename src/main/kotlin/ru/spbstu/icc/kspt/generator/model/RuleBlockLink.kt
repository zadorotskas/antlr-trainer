package ru.spbstu.icc.kspt.generator.model

class RuleBlockLink(
    private val ruleBlock: RuleBlock,
    suffix: String? = null,
    private var text: String = ""
): Rule, RuleWithSuffix(suffix) {

    override fun generate(maxDepth: Int): String {
        return generate {
            it.generate(maxDepth)
        }
    }

    private fun generate(generateFunction: (RuleBlock) -> String): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(generateFunction(ruleBlock))
            sb.append(" ")
        }
        return sb.toString()
    }

    override fun generateNot(maxDepth: Int): String {
        return generate {
            it.generateNot(maxDepth)
        }
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }
}