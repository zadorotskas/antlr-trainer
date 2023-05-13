package ru.spbstu.icc.kspt.generator

import java.nio.file.Path

data class TestGeneratorConfig(
    var pathForTests: Path? = null,
    var pathWithGrammar: Path? = null,
    var maxDepth: Int? = null,
    var number: Int? = null,
    var numberOfMutations: Int = 1
) {
    val needToGenerateTests: Boolean
        get() {
            return pathForTests != null
                    && pathWithGrammar != null
                    && maxDepth != null
                    && number != null
        }
}
