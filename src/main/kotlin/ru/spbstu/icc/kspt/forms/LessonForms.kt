package ru.spbstu.icc.kspt.forms

import kotlinx.html.*
import ru.spbstu.icc.kspt.model.Lesson
import ru.spbstu.icc.kspt.model.SolutionState
import ru.spbstu.icc.kspt.model.TaskSolution

internal fun HTML.allLessonsForm(lessons: List<Lesson>) {
    head {
        title = "Lessons"
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        div(classes = "container") {
            h1("mb-3") {
                +"Lessons:"
            }
            ul(classes = "list-group") {
                lessons.forEach { lesson ->
                    a(classes = "list-group-item list-group-item-action") {
                        href = "/lesson/${lesson.id}"
                        +"Lesson ${lesson.number}: ${lesson.name}"
                    }
                }
            }
        }
        unsafe {
            +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>"
        }
    }
}

internal fun HTML.studentLessonForm(lessonContent: String, lessonNumber: Int, lessonName: String, message: String?) {
    head {
        title = "Lesson"
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        form(classes = "container") {
            div(classes = "row") {
                h1 {
                    +"Lesson $lessonNumber: $lessonName"
                }
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
        script {
            src = "studentLesson.js"
        }
        unsafe {
            +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>"
        }
    }
}

internal fun HTML.adminLessonForm(
    lessonContent: String,
    lessonNumber: Int,
    lessonName: String,
    progress: List<TaskSolution>,
    notStartedNames: List<String>
) {
    head {
        title = "Lesson"
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        form(classes = "container") {
            h1(classes = "mb-3") {
                +"Lesson $lessonNumber: $lessonName"
            }
            div(classes = "mb-3") {
                unsafe {
                    +lessonContent
                }
            }
            div(classes = "btn-group mb-3") {
                button(classes = "btn btn-danger") {
                    type = ButtonType.button
                    id = "delete-lesson-btn"
                    +"Delete lesson"
                }
                button(classes = "btn btn-warning") {
                    type = ButtonType.button
                    id = "edit-lesson-btn"
                    +"Edit lesson"
                }
            }
            h1 {
                +"Progress"
            }
            div(classes = "mb-3") {
                progress.forEach {
                    val cssClass = when (it.state) {
                        SolutionState.FINISHED -> "finished"
                        else -> "not-finished"
                    }
                    div {
                        p {
                            +"${it.userName}:"
                        }
                        div(classes = cssClass) {
                            +"${it.datetime}"
                        }
                    }
                }
                notStartedNames.forEach {
                    div {
                        p {
                            +"$it:"
                        }
                        div(classes = "not-started") {
                            +"Not started"
                        }
                    }
                }
            }
        }
        script {
            src = "adminLesson.js"
        }
        unsafe {
            +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>"
        }
    }
}

private fun FORM.lastAttemptMessage(message: String) {
    div {
        id = "last-attempt-message-div"
        +message
    }
}

private fun FORM.testResult() {
    div {
        id = "test-result-div"
    }
}

internal fun HTML.addLessonForm() {
    head {
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        form(classes = "container") {
            h1 {
                +"Add new lesson"
            }
            div(classes = "input-group mb-3") {
                input(classes = "form-control") {
                    type = InputType.text
                    id = "lesson-number-input"
                    placeholder = "Number"
                }
                input(classes = "form-control") {
                    type = InputType.text
                    id = "lesson-name-input"
                    placeholder = "Name"
                }
            }
            div(classes = "form-floating mb-3") {
                textArea(classes = "form-control") {
                    id = "lesson-text-area"
                    placeholder = "Lesson content"
                    style = "height: 150px"
                }
                unsafe {
                    +"<label for=\"floatingTextarea2\">Lesson</label>"
                }
            }
            div(classes = "input-group mb-3") {
                input(classes = "form-control") {
                    type = InputType.file
                    id = "lesson-file"
                }
            }
            solution()
            br
            tests()
            br
            button(classes = "btn btn-primary mb-3") {
                id = "upload-lesson-btn"
                type = ButtonType.submit
                +"Add lesson"
            }

        }
        script {
            src = "newLesson.js"
        }
        unsafe {
            +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>"
        }
    }
}

fun FORM.solution() {
    h1 {
        +"Solution"
    }
    div(classes = "form-floating mb-3") {
        textArea(classes = "form-control") {
            id = "g4-text-area"
            placeholder = "g4 grammar"
            style = "height: 150px"
        }
        unsafe {
            +"<label for=\"floatingTextarea2\">ANTLR grammar</label>"
        }
    }
    div(classes = "form-floating mb-3") {
        textArea(classes = "form-control") {
            id = "main-text-area"
            placeholder = "Main java class"
            style = "height: 150px"
        }
        unsafe {
            +"<label for=\"floatingTextarea2\">Main class</label>"
        }
    }
    checkBoxInput {
        checked = false
        id = "add-listener-checkbox"
        +"Add listener file"
    }
    div(classes = "form-floating mb-3") {
        id = "listener-div"
        textArea(classes = "form-control") {
            id = "listener-text-area"
            placeholder = "Listener java class"
            style = "height: 150px"
        }
        unsafe {
            +"<label for=\"floatingTextarea2\">Listener</label>"
        }
    }
    checkBoxInput {
        checked = false
        id = "add-visitor-checkbox"
        +"Add visitor file"
    }
    div(classes = "form-floating mb-3") {
        id = "visitor-div"
        textArea(classes = "form-control") {
            id = "visitor-text-area"
            placeholder = "Visitor java class"
            style = "height: 150px"
        }
        unsafe {
            +"<label for=\"floatingTextarea2\">Visitor</label>"
        }
    }
    div(classes = "input-group mb-3") {
        input(classes = "form-control") {
            type = InputType.file
            id = "solution-files"
            multiple = true
        }
    }
}

fun FORM.tests() {
    h1 {
        +"Tests"
    }
    div(classes = "input-group") {
        input(classes = "form-control") {
            type = InputType.file
            id = "test-files"
            multiple = true
        }
    }
}