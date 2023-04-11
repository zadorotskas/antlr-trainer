val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project
val bcrypt_version: String by project
val postgres_version: String by project
val email_version: String by project
val aspose_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    id("io.ktor.plugin") version "2.2.3"
}

group = "kspt.icc.spbstu.ru"
version = "0.0.1"
application {
    mainClass.set("ru.spbstu.icc.kspt.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repository.aspose.com/repo/")
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-freemarker-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-sessions-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")

    implementation("com.h2database:h2:$h2_version")
    implementation("at.favre.lib:bcrypt:$bcrypt_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("org.apache.commons:commons-email:$email_version")
    implementation(group = "com.aspose", name = "aspose-html" , version = aspose_version, classifier = "jdk16")
    implementation("com.xenoamess:nashorn:jdk8u265-b01-x3")
    implementation("com.ibm.icu:icu4j:72.1")
    implementation("org.commonjava.googlecode.markdown4j:markdown4j:2.2-cj-1.0")
    implementation("org.pegdown:pegdown:1.6.0")
    implementation("com.github.rjeschke:txtmark:0.13")
    implementation("com.vladsch.flexmark:flexmark-all:0.64.0")
    implementation("org.jetbrains:markdown:0.4.0")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}