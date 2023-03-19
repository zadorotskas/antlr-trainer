package ru.spbstu.icc.kspt.extension

import io.ktor.server.config.*

internal val ApplicationConfig?.lessonsPath: String
    get() = System.getProperty("user.dir") + (this?.propertyOrNull("data.lessonsFolder")?.getString() ?: "/data/lessons")

internal val ApplicationConfig?.oauthClientId: String
    get() = this?.propertyOrNull("ktor.deployment.clientId")?.getString() ?: error("missing clientId in config")

internal val ApplicationConfig?.oauthClientSecret: String
    get() = this?.propertyOrNull("ktor.deployment.clientSecret")?.getString() ?: error("missing clientSecret in config")
