package ru.spbstu.icc.kspt.build

import io.ktor.util.logging.*
import ru.spbstu.icc.kspt.extension.capitalize
import java.io.File

object ParserBuild {
    private val Logger: Logger = KtorSimpleLogger("solution.build")

    fun buildSolution(solutionDirectory: File, grammarName: String, antlrLibPath: String): File {
        Runtime.getRuntime().exec("java org.antlr.v4.Tool -o $solutionDirectory  ${solutionDirectory.resolve("$grammarName.g4")}").waitFor()

        Runtime.getRuntime().exec("javac $solutionDirectory${File.separatorChar}*.java").waitFor()

        return buildJar(
            solutionDirectory.resolve("jar"),
            solutionDirectory,
            grammarName,
            antlrLibPath
        )
    }

    private fun handleProcess(process: Process) {
        Logger.info(process.inputStream)
    }

    private fun buildJar(outputDir: File, inputDir: File, jarName: String, antlrLibPath: String): File {
        val jarPath = outputDir.resolve(jarName)

        JarBuilder().use {
            it.setMainClass("${capitalize(jarName)}.java")
            it.openJar(jarPath)
            it.add(
                inputDir.resolve("ru"),
                inputDir
            )
            it.addJar(File(antlrLibPath))
            it.close()
        }

        return jarPath
    }
}