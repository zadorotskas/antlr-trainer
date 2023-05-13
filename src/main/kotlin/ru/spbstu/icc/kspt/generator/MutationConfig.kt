package ru.spbstu.icc.kspt.generator

data class MutationConfig(
    val mutationLevel: Int,
    var hasBeenMutated: Boolean = false
)
