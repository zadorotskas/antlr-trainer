window.onload = function() {
    const testResultDiv = document.getElementById('test-result-div')
    const lastAttemptMessageDiv = document.getElementById('last-attempt-message-div')

    const uploadSolutionBtn = document.getElementById('upload-solution-btn');
    uploadSolutionBtn.addEventListener('click', () => {
        var formData = new FormData();

        var solutionFiles = document.getElementById("solution-files").files
        if (solutionFiles && solutionFiles.length != 0) {
            for (var i = 0; i < solutionFiles.length; i++) {
                formData.append("solutionFiles", solutionFiles[i]);
            }
        } else {
            if (!appendValueToFormDataIfNotEmpty("g4", document.getElementById("g4-text-area").value)) return
            if (!appendValueToFormDataIfNotEmpty("main", document.getElementById("main-text-area").value)) return
            if (addListenerCheckbox.checked) {
                appendValueToFormDataIfNotEmpty("listener", document.getElementById("listener-text-area").value);
            }
            if (addVisitorCheckbox.checked) {
                appendValueToFormDataIfNotEmpty("visitor", document.getElementById("visitor-text-area").value);
            }
        }

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/lesson/solution/" + location.pathname.split('/')[2], true);
        xhr.onreadystatechange = function() {
          if (xhr.readyState === 4)  {
            lastAttemptMessageDiv.innerHTML = "";
            testResultDiv.innerHTML = "";
            const result = document.createTextNode(xhr.responseText);
            testResultDiv.appendChild(result);
          }
        };
        xhr.send(formData);
    });

    const appendValueToFormDataIfNotEmpty = (formData, name, value) => {
        if (value.length == 0) {
            return false
        }
        formData.append(name, value);
        return true
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
}