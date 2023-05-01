package ru.spbstu.icc.kspt.generator.model

class RuleNot(
    private val elements: List<Rule>,
    suffix: String? = null,
    private var text: String = ""
) : Rule, RuleWithSuffix(suffix) {
    override fun generate(maxDepth: Int): String {
        return generate(maxDepth) {
            it.generateNot(maxDepth)
        }
    }

    override fun generateNot(maxDepth: Int): String {
        return generate(maxDepth) {
            it.generate(maxDepth)
        }
    }

    private fun generate(maxDepth: Int, generateFunction: (Rule) -> String): String {
        val separator = if (maxDepth == -1) "" else " "
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(elements.joinToString(separator = separator) { generateFunction(it) })
            sb.append(" ")
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