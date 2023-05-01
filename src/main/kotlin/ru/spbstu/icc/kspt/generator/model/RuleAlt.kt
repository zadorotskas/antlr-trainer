package ru.spbstu.icc.kspt.generator.model

class RuleAlt(
    private val elements: List<Rule>,
    private var text: String = ""
) : Rule {
    override fun generate(maxDepth: Int): String {
        val separator = if (maxDepth == -1) "" else " "
        return elements.joinToString(separator = separator) { it.generate(maxDepth) }
    }

    override fun generateNot(maxDepth: Int): String {
        val separator = if (maxDepth == -1) "" else " "
        return elements.joinToString(separator = separator) { it.generateNot(maxDepth) }
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

}