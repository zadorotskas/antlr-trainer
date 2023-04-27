package ru.spbstu.icc.kspt.generator.model

interface Token {
    fun generate(): String
    fun generateNot(): String
}