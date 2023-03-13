const deleteLessonBtn = document.getElementById('delete-lesson-btn');
deleteLessonBtn.addEventListener('click', () => {
    var formData = new FormData();
    var xhr = new XMLHttpRequest();
    const queryString = window.location.search
    const urlParams = new URLSearchParams(queryString);
    xhr.open("POST", "/theory/remove/" + urlParams.get('id'), false);
    xhr.send(formData);
    window.location.href = '/theory/all';
});
