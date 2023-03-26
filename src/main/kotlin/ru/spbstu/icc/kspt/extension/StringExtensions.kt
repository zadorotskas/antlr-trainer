package ru.spbstu.icc.kspt.extension

import java.util.*


fun capitalize(string: String): String {
    return string.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}