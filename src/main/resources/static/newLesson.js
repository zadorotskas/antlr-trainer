const uploadTheoryFile = document.getElementById('upload-theory-from-file-btn');
uploadTheoryFile.addEventListener('click', () => {
    var formData = new FormData();
    formData.append("lesson", document.getElementById("theory-file").files[0]);
    sendLesson(formData)
});

const uploadTheory = document.getElementById('upload-theory-btn');
uploadTheory.addEventListener('click', () => {
    var formData = new FormData();
    formData.append("lesson", document.getElementById("lesson-text-area").value);
    sendLesson(formData)
});

const sendLesson = (formData) => {
    formData.append("number", document.getElementById("lesson-number-input").value);
    formData.append("name", document.getElementById("lesson-name-input").value);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/theory/upload", false);
    xhr.send(formData);
    window.location.href = '/theory/all';
}