package ru.spbstu.icc.kspt.forms

import kotlinx.html.*
import ru.spbstu.icc.kspt.model.Lesson
import ru.spbstu.icc.kspt.model.TaskSolution

internal fun HTML.allLessonsForm(lessons: List<Lesson>) {
    head {
        title = "Lessons"
    }
    body {
        h1 {
            +"Lessons:"
        }
        lessons.forEach { lesson ->
            a {
                href = "/lesson/${lesson.id}"
                +"Lesson ${lesson.number}: ${lesson.name}"
            }
            br
        }
    }
}

internal fun HTML.studentLessonForm(lessonContent: String, lessonNumber: Int, lessonName: String, message: String?) {
    head {
        title = "Lesson"
    }
    body {
        h1 {
            +"Lesson $lessonNumber: $lessonName"
        }
        unsafe {
            +lessonContent
        }
        solution()
        br
        div {
            button {
                id = "upload-solution-btn"
                type = ButtonType.button
                +"Upload solution"
            }
        }
        br
        h1 {
            +"Last solution result:"
        }
        message?.let { lastAttemptMessage(it) }
        testResult()
    }
}

internal fun HTML.adminLessonForm(
    lessonContent: String,
    lessonNumber: Int,
    lessonName: String,
    progress: List<TaskSolution>
) {
    head {
        title = "Lesson"
    }
    body {
        h1 {
            +"Lesson $lessonNumber: $lessonName"
        }
        div {
            button {
                type = ButtonType.button
                id = "delete-lesson-btn"
                +"Delete lesson"
            }
        }
        unsafe {
            +lessonContent
        }
        br
        solution()
        br
        h1 {
            +"Progress:"
            progress.forEach {
                div {

                }
            }
        }
        script {
            src = "studentLesson.js"
        }
    }
}

private fun BODY.lastAttemptMessage(message: String) {
    div {
        id = "last-attempt-message-div"
        +message
    }
}

private fun BODY.testResult() {
    div {
        id = "test-result-div"
    }
}

internal fun HTML.addLessonForm() {
    body {
        h1 {
            +"Add new lesson"
        }
        div {
            input {
                type = InputType.text
                id = "lesson-number-input"
                placeholder = "Number"
            }
            input {
                type = InputType.text
                id = "lesson-name-input"
                placeholder = "Name"
            }
        }
        textArea {
            id = "lesson-text-area"
            placeholder = "Lesson content"
        }
        div {
            input {
                type = InputType.file
                id = "lesson-file"
            }
        }
        solution()
        br
        tests()
        br
        div {
            button {
                id = "upload-lesson-btn"
                type = ButtonType.button
                +"Add lesson"
            }
        }
        script {
            src = "newLesson.js"
        }
    }
}

fun BODY.solution() {
    h1 {
        +"Solution"
    }
    div {
        textArea {
            id = "g4-text-area"
            placeholder = "g4 grammar"
        }
    }
    div {
        textArea {
            id = "main-text-area"
            placeholder = "Main java class"
        }
    }
    checkBoxInput {
        checked = false
        id = "add-listener-checkbox"
        +"Add listener file"
    }
    div {
        id = "listener-div"
        textArea {
            id = "listener-text-area"
            placeholder = "Listener java class"
        }
    }
    checkBoxInput {
        checked = false
        id = "add-visitor-checkbox"
        +"Add visitor file"
    }
    div {
        id = "visitor-div"
        textArea {
            id = "visitor-text-area"
            placeholder = "Visitor java class"
        }
    }
    div {
        input {
            type = InputType.file
            id = "solution-files"
            multiple = true
        }
    }
}

fun BODY.tests() {
    h1 {
        +"Tests"
    }
    div {
        input {
            type = InputType.file
            id = "test-files"
            multiple = true
        }
    }
}