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
                href = "/theory/${lesson.id}"
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
        script {
            src = "lesson.js"
        }
    }
}