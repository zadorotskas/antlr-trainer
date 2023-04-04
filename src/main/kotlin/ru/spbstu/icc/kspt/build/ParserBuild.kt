package ru.spbstu.icc.kspt.build

import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import kotlin.io.path.createTempDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name

object ParserBuild {
    fun buildSolution(solutionDirectory: File, grammarName: String, antlrLibPath: String): File {
        val tempDirectory = createTempDirectory("antlr-trainer")
        var message: String? = handleProcess(Runtime.getRuntime().exec("java -cp $antlrLibPath org.antlr.v4.Tool -o $tempDirectory  ${solutionDirectory.resolve("$grammarName.g4")}"))
        if (message != null) {
            error("antlr run failed with message: $message")
        }

        Files.copy(solutionDirectory.resolve("Main.java").toPath(), tempDirectory.resolve("Main.java"))
        val classes = tempDirectory.listDirectoryEntries()
            .filter { it.name.endsWith(".java") }
            .joinToString(separator = " ") { "$tempDirectory${File.separatorChar}${it.fileName}" }

        message = handleProcess(Runtime.getRuntime().exec("javac -cp $antlrLibPath $classes"))
        if (message != null) {
            error("javac run failed with message: $message")
        }

        return buildJar(
            tempDirectory.resolve("jar").toFile(),
            tempDirectory.toFile(),
            grammarName,
            antlrLibPath
        )
    }

    private fun handleProcess(process: Process): String? {
        process.waitFor()

        val error = getInputStreamContent(process.errorStream)
        if (error.isNotEmpty()) return error
        return null
    }

    private fun getInputStreamContent(inputStream: InputStream): String {
        var output: String
        BufferedReader(inputStream.reader()).use { reader ->
            output = reader.readText()
        }
        return output
    }

    private fun buildJar(outputDir: File, inputDir: File, jarName: String, antlrLibPath: String): File {
        val jarPath = outputDir.resolve("$jarName.jar")

        jarPath.parentFile.mkdirs()
        JarBuilder().use {
            it.setMainClass("Main")
            it.openJar(jarPath)
            it.add(
                inputDir,
                inputDir
            )
            it.addJar(File(antlrLibPath))
            it.close()
        }

        return jarPath
    }
}