package ru.spbstu.icc.kspt.build

import io.ktor.util.logging.*
import ru.spbstu.icc.kspt.extension.capitalize
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import kotlin.io.path.createTempDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name

object ParserBuild {
    private val Logger: Logger = KtorSimpleLogger("solution.build")

    fun buildSolution(solutionDirectory: File, grammarName: String, antlrLibPath: String): File {
        val tempDirectory = createTempDirectory("antlr-trainer")
        if (handleProcess(Runtime.getRuntime().exec("java -cp $antlrLibPath org.antlr.v4.Tool -o $tempDirectory  ${solutionDirectory.resolve("$grammarName.g4")}")) != 0) {
            error("antlr run failed")
        }

        Files.copy(solutionDirectory.resolve("Main.java").toPath(), tempDirectory.resolve("Main.java"))
        val classes = tempDirectory.listDirectoryEntries()
            .filter { it.name.endsWith(".java") }
            .joinToString(separator = " ") { "$tempDirectory${File.separatorChar}${it.fileName}" }

        if (handleProcess(Runtime.getRuntime().exec("javac -cp $antlrLibPath $classes")) != 0) {
            error("javac run failed")
        }

        //return tempDirectory.resolve("Main.java").toFile()

        return buildJar(
            tempDirectory.resolve("jar").toFile(),
            tempDirectory.toFile(),
            grammarName,
            antlrLibPath
        )
    }

    private fun handleProcess(process: Process): Int {
        process.waitFor()

        val output = getInputStreamContent(process.inputStream)
        if (output.isNotEmpty()) Logger.info(output)

        val error = getInputStreamContent(process.errorStream)
        if (error.isNotEmpty()) Logger.error(error)
        return process.exitValue()
    }

    private fun getInputStreamContent(inputStream: InputStream): String {
        var output: String
        BufferedReader(inputStream.reader()).use { reader ->
            output = reader.readText()
        }
        return output
    }

    private fun buildJar(outputDir: File, inputDir: File, jarName: String, antlrLibPath: String): File {
        val jarPath = outputDir.resolve(jarName)

        jarPath.parentFile.mkdirs()
        JarBuilder().use {
            it.setMainClass("${capitalize(jarName)}.java")
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