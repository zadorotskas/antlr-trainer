package ru.spbstu.icc.kspt.generator.model

interface Rule {
    fun generate(maxDepth: Int): String
    fun generateNot(maxDepth: Int): String

    fun setText(text: String)
    fun getText(): String
}