package ru.spbstu.icc.kspt.generator.model

import com.github.curiousoddman.rgxgen.RgxGen
import ru.spbstu.icc.kspt.generator.MutationConfig

class RuleString(
    private val string: String,
    suffix: String? = null,
    private var text: String = ""
) : Rule, RuleWithSuffix(suffix) {

    override fun generate(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate {
           it
        }
    }

    private fun generate(generateFunction: (String) -> String): String {
        val sb = StringBuffer()
        repeat(suffix.getNumberFromSuffix() ?: return "") {
            sb.append(generateFunction(string))
        }
        return sb.toString()
    }

    override fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String {
        return generate {
            RgxGen(it).generateNotMatching()
        }
    }

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

}