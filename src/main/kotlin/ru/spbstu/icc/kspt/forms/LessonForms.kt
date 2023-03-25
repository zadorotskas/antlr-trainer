package ru.spbstu.icc.kspt.forms

import kotlinx.html.*
import ru.spbstu.icc.kspt.model.Lesson

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

internal fun HTML.lessonForm(isAdmin: Boolean, lessonContent: String) {
    head {
        title = "Lesson"
    }
    body {
        if (isAdmin) {
            div {
                button {
                    type = ButtonType.button
                    id = "delete-lesson-btn"
                    +"Delete lesson"
                }
            }
            br
        }
        unsafe {
            +lessonContent
        }
        solution()
        script {
            src = "lesson.js"
        }
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