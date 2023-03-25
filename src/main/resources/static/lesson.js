const deleteLessonBtn = document.getElementById('delete-lesson-btn');
deleteLessonBtn.addEventListener('click', () => {
    var formData = new FormData();
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/lesson/remove/" + location.pathname.split('/')[2], false);
    xhr.send(formData);
    window.location.href = '/lesson/all';
});

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