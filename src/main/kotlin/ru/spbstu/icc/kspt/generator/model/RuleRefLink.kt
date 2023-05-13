package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig

class RuleRefLink(
    private val ruleRef: RuleRef,
    suffix: String? = null,
    private var text: String = ""
) : Rule, RuleWithSuffix(suffix) {

    override fun generate(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate(maxDepth, mutationConfig) {
            it.generate(maxDepth - 1, mutationConfig)
        } ?: generate(maxDepth, mutationConfig) {
            it.generateNot(maxDepth - 1, mutationConfig)
        }!!
    }

    override fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate(maxDepth, mutationConfig) {
            it.generateNot(maxDepth - 1, mutationConfig)
        } ?: generate(maxDepth, mutationConfig) {
            it.generate(maxDepth - 1, mutationConfig)
        }!!
    }

    private fun generate(maxDepth: Int, mutationConfig: MutationConfig, generateFunction: (Rule) -> String): String? {
        when {
            maxDepth == 0 && (suffix == "?" || suffix == "*") -> {
                return ""
            }
            maxDepth == 0 -> {
                error("Cannot generate $text with depth 0")
            }
            maxDepth == mutationConfig.mutationLevel && !mutationConfig.hasBeenMutated-> {
                mutationConfig.hasBeenMutated = true
                return null
            }
        }

        val sb = StringBuffer()
        try {
            repeat(suffix.getNumberFromSuffix() ?: return "") {
                sb.append(generateFunction(ruleRef))
                sb.append(" ")
            }
            return sb.removeSuffix(" ").toString()
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