package ru.spbstu.icc.kspt.extension

import com.ibm.icu.text.Transliterator
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.pipeline.*
import ru.spbstu.icc.kspt.dao
import ru.spbstu.icc.kspt.generator.TestGeneratorConfig
import ru.spbstu.icc.kspt.model.SolutionState
import java.io.File
import java.nio.file.Path
import java.time.LocalDateTime

const val CYRILLIC_TO_LATIN = "Russian-Latin/BGN"

internal suspend fun PipelineContext<Unit, ApplicationCall>.uploadAndSaveNewLesson(
    path: String,
    testGeneratorConfig: TestGeneratorConfig
): File {
    val multipart = call.receiveMultipart()

    var lessonFileBytesParam: ByteArray? = null

    var fileNameParam: String? = null
    var numberParam: Int? = null
    var lessonParam: String? = null

    val solution = Solution()
    val testFiles = mutableMapOf<String, ByteArray>()

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FileItem -> {
                when (part.name) {
                    "lesson" -> lessonFileBytesParam = part.streamProvider().readBytes()
                    "solutionFiles" -> solution.addFile(part.originalFileName, part.streamProvider().readBytes())
                    "testFiles" -> testFiles[part.originalFileName!!] = part.streamProvider().readBytes()
                }
            }

            is PartData.FormItem -> {
                when (part.name) {
                    "number" -> numberParam = part.value.toInt()
                    "name" -> fileNameParam = part.value
                    "lesson" -> lessonParam = part.value
                    "maxDepth" -> testGeneratorConfig.maxDepth = part.value.toInt()
                    "testNumber" -> testGeneratorConfig.number = part.value.toInt()

                    "g4" -> solution.g4String = part.value
                    "main" -> solution.mainString = part.value
                    "listener" -> solution.listenerString = part.value
                    "visitor" -> solution.visitorString = part.value
                }
            }

            else -> {}
        }
        part.dispose()
    }

    var fileName = fileNameParam ?: error("does not receive file name")

    if (fileName.contains(Regex("""[а-яА-Я]"""))) {
        val toLatinTrans: Transliterator = Transliterator.getInstance(CYRILLIC_TO_LATIN)
        fileName = toLatinTrans.transliterate(fileName)
    }

    val number = numberParam ?: error("does not receive number")

    val lesson = dao.addLesson(fileName, number) ?: error("cant save file info in database")
    val directory = "$path${File.separator}${lesson.id}"
    File(directory).mkdirs()
    val file = File("$directory${File.separator}${fileName.replace(" ", "_")}.md")
    lessonFileBytesParam?.let {
        file.writeBytes(it)
    } ?: lessonParam?.let {
        file.writeText(it)
    } ?: error("does not receive lesson file")

    val testDirectory = "$directory${File.separator}test"
    File(testDirectory).mkdirs()
    testFiles.forEach { (name, bytes) ->
        File("$testDirectory${File.separator}$name").writeBytes(bytes)
    }
    testGeneratorConfig.pathForTests = Path.of(testDirectory)

    val solutionFolder = file.parentFile.resolve("solution")
    solutionFolder.mkdirs()
    solution.checkHasCorrectFiles()
    return solution.createFiles(solutionFolder).also {
        testGeneratorConfig.pathWithGrammar = it.toPath()
    }
}

internal suspend fun PipelineContext<Unit, ApplicationCall>.uploadAndSaveSolution(path: String, lessonId: Int, userName: String): File {
    val multipart = call.receiveMultipart()

    val solution = Solution()

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FileItem -> {
                when (part.name) {
                    "solutionFiles" -> solution.addFile(part.originalFileName, part.streamProvider().readBytes())
                }
            }

            is PartData.FormItem -> {
                when (part.name) {
                    "g4" -> solution.g4String = part.value
                    "main" -> solution.mainString = part.value
                    "listener" -> solution.listenerString = part.value
                    "visitor" -> solution.visitorString = part.value
                }
            }

            else -> {}
        }
        part.dispose()
    }

    val currentAttempt = dao.getAttemptsCount(userName, lessonId) + 1
    dao.addTaskSolution(userName, lessonId, LocalDateTime.now(), SolutionState.LOADED, currentAttempt, "")

    val solutionFolder = File("$path${File.separator}${lessonId}${File.separator}${userName.replace(" ", "_")}${File.separator}attempts${File.separator}$currentAttempt${File.separator}solution")
    solutionFolder.mkdirs()
    solution.checkHasCorrectFiles()
    return solution.createFiles(solutionFolder)
}

class Solution {
    var g4File: ByteArray? = null
    var mainFile: ByteArray? = null
    var listenerFile: ByteArray? = null
    var visitorFile: ByteArray? = null

    var g4String: String? = null
    var mainString: String? = null
    var listenerString: String? = null
    var visitorString: String? = null

    var filesName: String? = null

    fun addFile(fileName: String?, bytes: ByteArray) {
        fileName ?: error("missing file name")
        when {
            fileName.endsWith(".g4") -> {
                filesName = fileName.substringBeforeLast(".")
                g4File = bytes
            }
            fileName == "Main.java" -> mainFile = bytes
            fileName.lowercase().contains("listener") -> listenerFile = bytes
            fileName.lowercase().contains("visitor") -> visitorFile = bytes
            else -> error("unrecognized file name")
        }
    }

    fun checkHasCorrectFiles() {
        if (g4File == null && g4String == null) error("missing g4 file")
        if (mainFile == null && mainString == null) error("missing main file")
    }

    fun createFiles(parentFolder: File): File {
        if (filesName == null) {
            filesName = g4String?.trim()?.split(" ")?.get(1)?.substringBeforeLast(";") ?: error("cannot receive files name")
        }
        parentFolder.resolve("Main.java").create(mainString, mainFile)
        parentFolder.resolve("Listener.java").create(listenerString, listenerFile)
        parentFolder.resolve("Visitor.java").create(visitorString, visitorFile)
        return parentFolder.resolve("$filesName.g4").also {
            it.create(g4String, g4File)
        }
    }

    private fun File.create(str: String?, bytes: ByteArray?) {
        bytes?.let {
            this.writeBytes(it)
        } ?: str?.let {
            this.writeText(it)
        }
    }
}