const deleteLessonBtn = document.getElementById('delete-lesson-btn');
deleteLessonBtn.addEventListener('click', () => {
    var formData = new FormData();
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/theory/remove/" + location.pathname.split('/')[2], false);
    xhr.send(formData);
    window.location.href = '/theory/all';
});
