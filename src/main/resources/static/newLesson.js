const uploadLessonFile = document.getElementById('upload-lesson-from-file-btn');
uploadLessonFile.addEventListener('click', () => {
    var formData = new FormData();
    formData.append("lesson", document.getElementById("lesson-file").files[0]);
    sendLesson(formData)
});

const uploadLesson = document.getElementById('upload-lesson-btn');
uploadLesson.addEventListener('click', () => {
    var formData = new FormData();
    formData.append("lesson", document.getElementById("lesson-text-area").value);
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