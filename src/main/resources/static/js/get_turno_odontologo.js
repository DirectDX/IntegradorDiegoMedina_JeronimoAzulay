window.addEventListener('load', function () {
    const odontologoSelect = document.getElementById('odontologo_selector');
    
    fetch('/odontologos')
        .then(response => response.json())
        .then(data => {
            data.forEach(odontologo => {
                const option = document.createElement('option');
                option.value = odontologo.id;
                option.textContent = `${odontologo.nombre} ${odontologo.apellido}`;
                odontologoSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error al obtener la lista de odontólogos:', error));
});
