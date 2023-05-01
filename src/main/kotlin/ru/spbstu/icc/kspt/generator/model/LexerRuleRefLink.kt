package ru.spbstu.icc.kspt.generator.model

class LexerRuleRefLink(
    private val lexerRuleRef: LexerRuleRef,
    suffix: String? = null,
    private var text: String = ""
) : Rule, RuleWithSuffix(suffix) {
    override fun generate(maxDepth: Int): String {
        return generate {
            it.generate(maxDepth)
        }
    }

    override fun generateNot(maxDepth: Int): String {
        return generate {
            it.generateNot(maxDepth)
        }
    }

    private fun generate(generateFunction: (Rule) -> String): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(generateFunction(lexerRuleRef))
        }
        return sb.toString()
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

}