window.addEventListener('load', function () {
    const pacienteSelect = document.getElementById('paciente_selector');
    
    fetch('/pacientes')
        .then(response => response.json())
        .then(data => {
            data.forEach(paciente => {
                const option = document.createElement('option');
                option.value = paciente.id;
                option.textContent = `${paciente.nombre} ${paciente.apellido}`;
                pacienteSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error al obtener la lista de pacientes:', error));
});
