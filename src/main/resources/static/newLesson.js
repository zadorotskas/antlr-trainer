const uploadLesson = document.getElementById('upload-lesson-btn');
uploadLesson.addEventListener('click', () => {
    var formData = new FormData();
    var lessonFile = document.getElementById("lesson-file").files[0]
    if (lessonFile) {
        formData.append("lesson", lessonFile);
    } else {
        formData.append("lesson", document.getElementById("lesson-text-area").value);
    }

    var solutionFiles = document.getElementById("solution-files").files
    if (solutionFiles && solutionFiles.length != 0) {
        for (var i = 0; i < solutionFiles.length; i++) {
            formData.append("solutionFiles", solutionFiles[i]);
        }
    } else {
        formData.append("g4", document.getElementById("g4-text-area").value);
        formData.append("main", document.getElementById("main-text-area").value);
        if (addListenerCheckbox.checked) {
            formData.append("listener", document.getElementById("listener-text-area").value);
        }
        if (addVisitorCheckbox.checked) {
            formData.append("visitor", document.getElementById("visitor-text-area").value);
        }
    }

    sendLesson(formData)
});

const sendLesson = (formData) => {
    formData.append("number", document.getElementById("lesson-number-input").value);
    formData.append("name", document.getElementById("lesson-name-input").value);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/lesson/upload", false);
    xhr.send(formData);
    window.location.href = '/lesson/all';
}

const addListenerDiv = document.getElementById('listener-div');
addListenerDiv.style.visibility = 'hidden'
const addListenerCheckbox = document.getElementById('add-listener-checkbox');
addListenerCheckbox.addEventListener('change', () => {
    if (addListenerCheckbox.checked) {
        addListenerDiv.style.visibility = 'visible'
    } else {
        addListenerDiv.style.visibility = 'hidden'
    }
});

const addVisitorDiv = document.getElementById('visitor-div');
addVisitorDiv.style.visibility = 'hidden'
const addVisitorCheckbox = document.getElementById('add-visitor-checkbox');
addVisitorCheckbox.addEventListener('change', () => {
    if (addVisitorCheckbox.checked) {
        addVisitorDiv.style.visibility = 'visible'
    } else {
        addVisitorDiv.style.visibility = 'hidden'
    }
});