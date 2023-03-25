package ru.spbstu.icc.kspt.extension

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.pipeline.*
import ru.spbstu.icc.kspt.dao
import java.io.File

internal suspend fun PipelineContext<Unit, ApplicationCall>.uploadAndSaveFile(path: String) {
    val multipart = call.receiveMultipart()

    var lessonFileBytesParam: ByteArray? = null

    var fileNameParam: String? = null
    var numberParam: Int? = null
    var lessonParam: String? = null

    val solution = Solution()

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FileItem -> {
                when (part.name) {
                    "lesson" -> lessonFileBytesParam = part.streamProvider().readBytes()
                    "solutionFiles" -> solution.addFile(part.originalFileName, part.streamProvider().readBytes())
                }
            }

            is PartData.FormItem -> {
                when (part.name) {
                    "number" -> numberParam = part.value.toInt()
                    "name" -> fileNameParam = part.value
                    "lesson" -> lessonParam = part.value

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

    val fileName = fileNameParam ?: error("does not receive file name")
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

    val solutionFolder = file.parentFile.resolve("solution")
    solutionFolder.mkdirs()
    solution.checkHasCorrectFiles()
    solution.createFiles(solutionFolder)
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

    fun addFile(fileName: String?, bytes: ByteArray) {
        fileName ?: error("missing file name")
        when {
            fileName.endsWith(".g4") -> g4File = bytes
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

    fun createFiles(parentFolder: File) {
        parentFolder.resolve("solution.g4").create(g4String, g4File)
        parentFolder.resolve("SolutionMain.java").create(mainString, mainFile)
        parentFolder.resolve("SolutionListener.java").create(listenerString, listenerFile)
        parentFolder.resolve("SolutionVisitor.java").create(visitorString, visitorFile)
    }

    private fun File.create(str: String?, bytes: ByteArray?) {
        bytes?.let {
            this.writeBytes(it)
        } ?: str?.let {
            this.writeText(it)
        }
    }
}