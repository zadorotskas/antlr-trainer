package ru.spbstu.icc.kspt.extension

import io.ktor.server.config.*

internal val ApplicationConfig?.lessonsPath: String
    get() = System.getProperty("user.dir") + (this?.propertyOrNull("data.lessonsFolder")?.getString() ?: "/data/lessons")

internal val ApplicationConfig?.testsPath: String
    get() = System.getProperty("user.dir") + (this?.propertyOrNull("data.testsFolder")?.getString() ?: "/data/tests")

internal val ApplicationConfig?.oauthClientId: String
    get() = this?.propertyOrNull("ktor.deployment.clientId")?.getString() ?: error("missing clientId in config")

internal val ApplicationConfig?.oauthClientSecret: String
    get() = this?.propertyOrNull("ktor.deployment.clientSecret")?.getString() ?: error("missing clientSecret in config")

internal val ApplicationConfig?.antlrLibPath: String
    get() = this?.propertyOrNull("build.antlrLibPath")?.getString() ?: error("missing antlrLibPath in config")

internal val ApplicationConfig?.url: String
    get() = this?.propertyOrNull("ktor.deployment.url")?.getString() ?: "http://localhost:8080"
