package ru.spbstu.icc.kspt.generator.model

class RuleRefLink(
    private val ruleRef: RuleRef,
    suffix: String? = null,
    private var text: String = ""
) : Rule, RuleWithSuffix(suffix) {

    override fun generate(maxDepth: Int): String {
        return generate(maxDepth) {
            it.generate(maxDepth - 1)
        }
    }

    override fun generateNot(maxDepth: Int): String {
        return generate(maxDepth) {
            it.generateNot(maxDepth - 1)
        }
    }

    private fun generate(maxDepth: Int, generateFunction: (Rule) -> String): String {
        when {
            maxDepth == 0 && (suffix == "?" || suffix == "*") -> {
                return ""
            }
            maxDepth == 0 -> {
                error("Cannot generate $text with depth 0")
            }
        }

        val sb = StringBuffer()
        try {
            repeat(suffix.getNumberFromSuffix() ?: return "") {
                sb.append(generateFunction(ruleRef))
                sb.append(" ")
            }
            return sb.toString()
        } catch (e: IllegalStateException) {
            if (suffix == "?" || suffix == "*") {
                return ""
            } else {
                error("Cannot generate $text with depth $maxDepth")
            }
        }
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

}