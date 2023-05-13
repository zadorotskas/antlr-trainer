package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig

class LexerRuleRefLink(
    private val lexerRuleRef: LexerRuleRef,
    suffix: String? = null,
    private var text: String = ""
) : Rule, RuleWithSuffix(suffix) {
    override fun generate(maxDepth: Int, mutationConfig: MutationConfig): String {
        if (mutationConfig.mutationLevel >= maxDepth && !mutationConfig.hasBeenMutated) {
            return generate {
                it.generateNot(maxDepth, mutationConfig)
            }
        }
        return generate {
            it.generate(maxDepth, mutationConfig)
        }
    }

    override fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String {
        if (mutationConfig.mutationLevel >= maxDepth && !mutationConfig.hasBeenMutated) {
            return generate {
                it.generate(maxDepth, mutationConfig)
            }
        }
        return generate {
            it.generateNot(maxDepth, mutationConfig)
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