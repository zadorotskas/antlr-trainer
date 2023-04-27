package ru.spbstu.icc.kspt.generator.model

import kotlin.random.Random

internal fun String?.getNumberFromSuffix(): Int? = when (this) {
    null -> 1
    "?" -> Random.nextInt(2)
    "*" -> Random.nextInt(32)
    "+" -> Random.nextInt(1, 32)
    else -> null
}