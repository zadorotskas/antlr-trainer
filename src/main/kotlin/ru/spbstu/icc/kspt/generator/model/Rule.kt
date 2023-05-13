package ru.spbstu.icc.kspt.generator.model

import ru.spbstu.icc.kspt.generator.MutationConfig

interface Rule {
    fun generate(maxDepth: Int, mutationConfig: MutationConfig): String
    fun generateNot(maxDepth: Int, mutationConfig: MutationConfig): String

    fun setText(text: String)
    fun getText(): String
}