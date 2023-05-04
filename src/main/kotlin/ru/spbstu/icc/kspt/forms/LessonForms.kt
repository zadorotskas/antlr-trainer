package ru.spbstu.icc.kspt.forms

import kotlinx.html.*
import ru.spbstu.icc.kspt.model.Lesson
import ru.spbstu.icc.kspt.model.SolutionState
import ru.spbstu.icc.kspt.model.TaskSolution
import ru.spbstu.icc.kspt.model.UserPrincipal
import java.time.format.DateTimeFormatter

internal fun HTML.allLessonsForm(lessons: List<Lesson>, principal: UserPrincipal) {
    head {
        title = "Lessons"
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        navigation(principal)
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

internal fun HTML.studentLessonForm(lessonContent: String, lessonNumber: Int, lessonName: String, message: String?, principal: UserPrincipal) {
    head {
        title = "Lesson"
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        navigation(principal)
        form(classes = "container") {
            h1(classes = "mb-3") {
                +"Lesson $lessonNumber: $lessonName"
            }
            div(classes = "mb-3") {
                unsafe {
                    +lessonContent
                }
            }
            solution()
            br
            button(classes = "btn btn-success mb-3") {
                id = "upload-solution-btn"
                type = ButtonType.button
                +"Upload solution"
            }
            br
            h1(classes = "mb-3") {
                if (message == null) style = "visibility: hidden"
                id = "solution-result-h1"
                +"Last solution result:"
            }
            message?.let {
                lastAttemptMessage(it)
            }
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
    notStartedNames: List<String>,
    principal: UserPrincipal
) {
    head {
        title = "Lesson"
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        navigation(principal)
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
            ul(classes = "list-group mb-3") {
                progress.forEach {
                    val cssClass = when (it.state) {
                        SolutionState.FINISHED -> "list-group-item-success"
                        else -> "list-group-item-warning"
                    }

                    li(classes = "list-group-item $cssClass") {
                        val userName = it.userName.replace(" ", "_")
                        div(classes = "d-flex justify-content-between mb-3") {
                            h5(classes = "mb-1") {
                                +it.userName
                            }
                            p(classes = "mb-1") {
                                +"Uploaded at ${it.datetime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss"))}"
                            }
                            unsafe {
                                +"<button class=\"btn btn-primary\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collapse_${userName}\" aria-expanded=\"false\" aria-controls=\"collapse_${userName}\"> Show message </button>"
                            }
                        }
                        div(classes = "collapse mb-3") {
                            id = "collapse_${userName}"
                            div(classes = "card card-body") {
                                +(it.message ?: "No message")
                            }
                        }
                    }
                }
                notStartedNames.forEach {
                    li(classes = "list-group-item list-group-item-danger") {
                        p {
                            +"$it:"
                        }
                        div {
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

internal fun HTML.addLessonForm(principal: UserPrincipal) {
    head {
        unsafe {
            +"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">"
        }
    }
    body {
        navigation(principal)
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
    div(classes = "form-check mb-3") {
        checkBoxInput(classes = "form-check-input") {
            checked = false
            id = "add-listener-checkbox"
        }
        label(classes = "form-check-label") {
            +"Add listener file"
        }
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
    div(classes = "form-check mb-3") {
        checkBoxInput(classes = "form-check-input") {
            checked = false
            id = "add-visitor-checkbox"
        }
        label(classes = "form-check-label") {
            +"Add visitor file"
        }
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
    br
    div(classes = "input-group") {
        input(classes = "form-control") {
            type = InputType.file
            id = "test-files"
            multiple = true
        }
    }
    br
    div(classes = "form-check mb-3") {
        checkBoxInput(classes = "form-check-input") {
            checked = false
            id = "generate-tests-checkbox"
        }
        label(classes = "form-check-label") {
            +"Generate tests"
        }
    }
    div(classes = "input-group mb-3") {
        id = "generate-tests-input-div"
        input(classes = "form-control") {
            type = InputType.text
            id = "generate-tests-number-input"
            placeholder = "Number"
        }
        input(classes = "form-control") {
            type = InputType.text
            id = "generate-tests-depth-input"
            placeholder = "Depth"
        }
    }
}