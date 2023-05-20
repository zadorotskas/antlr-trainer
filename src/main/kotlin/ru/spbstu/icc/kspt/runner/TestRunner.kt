package ru.spbstu.icc.kspt.runner

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.util.concurrent.TimeUnit

object TestRunner {
    suspend fun runAndSave(jar: File, inputDataDirectory: File): String? {
        inputDataDirectory.listFiles()?.let { files ->
            files
                .filter { it.name.substringAfterLast(".") == "in" }
                .forEach {
                    val command = "java -jar $jar"
                    val process = Runtime.getRuntime().exec(command)
                    val message = handleProcess(process, it, inputDataDirectory.resolve("${it.toString().substringBeforeLast(".")}.out"))
                    if (message != null) {
                        return message
                    }
            }
        }
        return null
    }

    suspend fun runAndAssert(jar: File, correctSolutionDirectory: File, outputFilesDirectory: File): String? {
        outputFilesDirectory.mkdirs()
        val correctSolutionFiles = correctSolutionDirectory.listFiles() ?: return "Cannot list files in solution directory"
        correctSolutionFiles
            .filter { it.name.substringAfterLast(".") == "in" }
            .forEach {
                val outputFile = outputFilesDirectory.resolve("${it.name.substringBeforeLast(".")}.out")
                val message = handleProcess(Runtime.getRuntime().exec("java -jar $jar"), it, outputFile)
                if (message != null) {
                    return message
                }
            }

        val correctOutputsByNames = correctSolutionFiles
            .filter { it.name.substringAfterLast(".") == "out" }
            .associateBy { it.name.substringBeforeLast(".") }

        outputFilesDirectory.listFiles()?.let { files ->
            files
                .filter { it.name.substringAfterLast(".") == "out" }
                .forEach {
                    val correct = correctOutputsByNames[it.name.substringBeforeLast(".")] ?: return "Cannot find solution for file"

                    if (Files.mismatch(correct.toPath(), it.toPath()) != -1L) {
                        return "Tests failed: \n expected: \"${correct.readText()}\", actual: \"${it.readText()}\""
                    }
                }
        }

        return null
    }

    private suspend fun handleProcess(process: Process, inputFile: File, outputFile: File): String? {
        val finished = withContext(Dispatchers.IO) {
            process.outputStream.write(inputFile.readBytes())
            process.outputStream.close()
            outputFile.writeBytes(process.inputStream.readBytes())
            process.inputStream.close()
            process.waitFor(3, TimeUnit.SECONDS)
        }
        if (!finished) {
            process.destroy()
        }

        val error = getInputStreamContent(process.errorStream)
        if (error.isNotEmpty()) {
            withContext(Dispatchers.IO) {
                outputFile.writeText(error)
            }
        }
        return null
    }

    private fun getInputStreamContent(inputStream: InputStream): String {
        var output: String
        BufferedReader(inputStream.reader()).use { reader ->
            output = reader.readText()
        }
        return output
    }
}