const uploadTheoryFile = document.getElementById('upload-theory-from-file-btn');
uploadTheoryFile.addEventListener('click', () => {
  const file = document.getElementById('theory-file');
  const fileReader = new FileReader();
  fileReader.readAsArrayBuffer(file.files[0]);
  fileReader.onload = async (event) => {
      const content = event.target.result;
      const CHUNK_SIZE = 5000;
      const totalChunks = event.target.result.byteLength / CHUNK_SIZE;

      for (let chunk = 0; chunk < totalChunks + 1; chunk++) {
          let CHUNK = content.slice(chunk * CHUNK_SIZE, (chunk + 1) * CHUNK_SIZE)
          await fetch('/theory/upload', {
                          'method' : 'POST',
                          'headers' : {
                              'content-type' : "application/octet-stream",
                              'content-length' : CHUNK.length,
                          },
                          'body': CHUNK
                  })
      }
  }
});


const uploadTheory = document.getElementById('upload-theory-btn');
uploadTheory.addEventListener('click', () => {
  window.location.href = '/theory/new';
});

