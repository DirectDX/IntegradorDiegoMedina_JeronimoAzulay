window.addEventListener('DOMContentLoaded', function () {
    // Obtener el formulario de agregar turno
    const formAgregarTurno = document.getElementById('formAgregarTurno');

    // Agregar un evento de escucha para el envío del formulario
    formAgregarTurno.addEventListener('submit', function (event) {
        // Evitar que se envíe el formulario de manera predeterminada
        event.preventDefault();

        // Obtener los valores de los campos del formulario
        const odontologoId = document.getElementById('odontologo_selector').value;
        const pacienteId = document.getElementById('paciente_selector').value;
        const fechaTurno = document.getElementById('fechaTurno').value;
        const horaTurno = document.getElementById('horaTurno').value;

        // Crear un objeto con los datos del nuevo turno
        const formData = {
            odontologo_id: parseInt(odontologoId),
            paciente_id: parseInt(pacienteId),
            fecha: fechaTurno,
            hora: horaTurno
        };

        console.log(formData)

        // Realizar la solicitud POST para agregar el nuevo turno
        fetch('/turnos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Hubo un problema al agregar el turno. Intente nuevamente.');
            }
            return response.json();
        })
        .then(data => {
            // Si no hay ningún error, mostrar un mensaje de éxito
            alert('Turno agregado correctamente');
            // Opcional: puedes redirigir al usuario a otra página o actualizar la lista de turnos
            window.location.href = '/turnos.html';
        
        })
        .catch(error => {
            // Si hay algún error, mostrar un mensaje de error
            alert('Error al agregar el turno: ' + error.message);
        });
    });
});
