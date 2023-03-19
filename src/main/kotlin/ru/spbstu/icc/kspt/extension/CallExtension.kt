package ru.spbstu.icc.kspt.extension

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.pipeline.*
import ru.spbstu.icc.kspt.dao
import java.io.File

internal suspend fun PipelineContext<Unit, ApplicationCall>.uploadAndSaveFile(path: String) {
    val multipart = call.receiveMultipart()
    var fileBytesParam: ByteArray? = null
    var fileNameParam: String? = null
    var numberParam: Int? = null
    var lessonParam: String? = null

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FileItem -> {
                fileBytesParam = part.streamProvider().readBytes()
            }

            is PartData.FormItem -> {
                when (part.name) {
                    "number" -> numberParam = part.value.toInt()
                    "name" -> fileNameParam = part.value
                    "lesson" -> lessonParam = part.value
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
    fileBytesParam?.let {
        file.writeBytes(it)
    } ?: lessonParam?.let {
        file.writeText(it)
    } ?: error("does not receive file")
}