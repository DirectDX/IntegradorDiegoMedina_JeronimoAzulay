window.addEventListener('DOMContentLoaded', function () {
    const patientTableBody = document.getElementById('patientTableBody');

    fetch('/turnos')
        .then(response => response.json())
        .then(data => {
            data.forEach(turno => {
                // Create row for the turno
                const row = document.createElement('tr');

                // Turno data
                const turnoIdCell = document.createElement('td');
                turnoIdCell.textContent = turno.id;
                row.appendChild(turnoIdCell);

                const fechaCell = document.createElement('td');
                fechaCell.textContent = turno.fecha;
                row.appendChild(fechaCell);

                const horaCell = document.createElement('td');
                horaCell.textContent = turno.hora;
                row.appendChild(horaCell);

                // Odontologo data
                const odontologoNombreCell = document.createElement('td');
                odontologoNombreCell.textContent = turno.odontologo.nombre;
                row.appendChild(odontologoNombreCell);

                const odontologoApellidoCell = document.createElement('td');
                odontologoApellidoCell.textContent = turno.odontologo.apellido;
                row.appendChild(odontologoApellidoCell);

                const matriculaCell = document.createElement('td');
                matriculaCell.textContent = turno.odontologo.matricula;
                row.appendChild(matriculaCell);

                // Paciente data
                const pacienteNombreCell = document.createElement('td');
                pacienteNombreCell.textContent = turno.paciente.nombre;
                row.appendChild(pacienteNombreCell);

                const pacienteApellidoCell = document.createElement('td');
                pacienteApellidoCell.textContent = turno.paciente.apellido;
                row.appendChild(pacienteApellidoCell);

                const dniCell = document.createElement('td');
                dniCell.textContent = turno.paciente.dni;
                row.appendChild(dniCell);

                // Create cell for "Eliminar" button
                const deleteCell = document.createElement('td');
                const deleteButton = document.createElement('button');
                deleteButton.type = 'button';
                deleteButton.className = 'btn btn-danger delete-btn';
                deleteButton.textContent = 'Eliminar';
                deleteButton.addEventListener('click', function () {
                    const turnoId = this.closest('tr').querySelector('td:first-child').textContent;
                    if (confirm("¿Estás seguro de que quieres eliminar el turno?")) {
                        deleteTurno(parseInt(turnoId));
                    }
                });
                deleteCell.appendChild(deleteButton);
                row.appendChild(deleteCell);

                // Append the row to the table body
                patientTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error al obtener la lista de turnos:', error));
});