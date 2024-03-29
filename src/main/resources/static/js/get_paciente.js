window.addEventListener('load', function () {
    // Obtener todos los elementos con la clase "close"
  const closeButtonElements = document.querySelectorAll(".cerrarActualizar");

    // Agregar un event listener a cada elemento
    closeButtonElements.forEach((element) => {
        element.addEventListener("click", function () {
          // Recargar la página
          console.log("click en close");
          window.location.reload();
        });
      });

    // Function to get the list of patients from the server
    function getPatients() {
        fetch('/pacientes')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener la lista de pacientes');
                }
                return response.json();
            })
            .then(patients => {
                // Once we get the list of patients, populate the table
                populatePatientTable(patients);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

// Function to populate the patient table with the received data
function populatePatientTable(patients) {
    const tableBody = document.getElementById('patientTableBody');
    tableBody.innerHTML = ''; // Clearing previous content of the table

    patients.forEach(patient => {
        const row = document.createElement('tr');
        row.id = 'tr_' + patient.id;

        const idCell = document.createElement('td');
        idCell.textContent = patient.id;
        row.appendChild(idCell);

        const nombreCell = document.createElement('td');
        nombreCell.textContent = patient.nombre;
        row.appendChild(nombreCell);

        const apellidoCell = document.createElement('td');
        apellidoCell.textContent = patient.apellido;
        row.appendChild(apellidoCell);

        const dniCell = document.createElement('td');
        dniCell.textContent = patient.dni;
        row.appendChild(dniCell);

        const ingresoCell = document.createElement('td');
        ingresoCell.textContent = patient.fechaIngreso;
        row.appendChild(ingresoCell);

        // Accessing address details
        if (patient.domicilio) {
            const domicilio = patient.domicilio;

            const calleCell = document.createElement('td');
            calleCell.textContent = domicilio.calle;
            row.appendChild(calleCell);

            const numeroCell = document.createElement('td');
            numeroCell.textContent = domicilio.numero;
            row.appendChild(numeroCell);

            const localidadCell = document.createElement('td');
            localidadCell.textContent = domicilio.localidad;
            row.appendChild(localidadCell);

            const provinciaCell = document.createElement('td');
            provinciaCell.textContent = domicilio.provincia;
            row.appendChild(provinciaCell);
        } else {
            // If domicilio is not present, add empty cells
            for (let i = 0; i < 4; i++) {
                const emptyCell = document.createElement('td');
                emptyCell.textContent = '';
                row.appendChild(emptyCell);
            }
        }
    
            // Create cell for "Eliminar" button
            const deleteCell = document.createElement('td');
            const deleteButton = document.createElement('button');
            deleteButton.type = 'button';
            deleteButton.className = 'btn btn-danger delete-btn';
            deleteButton.textContent = 'Eliminar';
            deleteButton.addEventListener('click', function () {
                const patientId = this.closest('tr').querySelector('td:first-child').textContent;
                if (confirm("¿Estás seguro de que quieres eliminar a este paciente?")) {
                    deletePatient(parseInt(patientId));
                }
            });
            deleteCell.appendChild(deleteButton);
            row.appendChild(deleteCell);
    
            // Create cell for "Actualizar" button
            const updateCell = document.createElement('td');
            const updateButton = document.createElement('button');
            updateButton.type = 'button';
            updateButton.className = 'btn btn-primary update-btn';
            updateButton.textContent = 'Actualizar';
            updateButton.dataset.id = patient.id; // Save patient ID in the button
            updateCell.appendChild(updateButton);
            row.appendChild(updateCell);
    
            tableBody.appendChild(row);
        });

                // Event listener para manejar los clicks en los botones de actualización
                document.querySelectorAll('.update-btn').forEach(btn => {
                    btn.addEventListener('click', function () {
                        const patientId = this.dataset.id;
                        // Llamar a la función para abrir el modal de actualización con los datos del odontólogo seleccionado
                        openUpdateModal(patientId);
                    });
                });
    }
    
    // Function to open the update modal with the selected patient's data
    function openUpdateModal(patientId) {
        // Call the API to get the patient data by ID
        fetch(`/pacientes/${patientId}`)
            .then(response => response.json())
            .then(data => {
                // Populate the update form with the patient's data
                document.getElementById('put_patient_id').value = data.id;
                document.getElementById('put_nombre').value = data.nombre;
                document.getElementById('put_apellido').value = data.apellido;
                document.getElementById('put_dni').value = data.dni;
                document.getElementById('put_fechaIngreso').value = data.fechaIngreso;
                document.getElementById('put_calle').value = data.calle;
                document.getElementById('put_numero').value = data.numero;
                document.getElementById('put_localidad').value = data.localidad;
                document.getElementById('put_provincia').value = data.provincia;
                // Show the update modal
                $('#putModalPatient').modal('show');
            })
            .catch(error => console.error('Error:', error));
    }

    // Call the function to get the list of patients when the page loads
    getPatients();
});
