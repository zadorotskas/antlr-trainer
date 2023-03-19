package ru.spbstu.icc.kspt.extension

import io.ktor.server.config.*

internal val ApplicationConfig?.lessonsPath: String
    get() = System.getProperty("user.dir") + (this?.propertyOrNull("data.lessonsFolder")?.getString() ?: "/data/lessons")
