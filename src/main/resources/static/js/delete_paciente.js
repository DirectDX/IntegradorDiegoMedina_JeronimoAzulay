
    // Function to delete a patient
    function deletePatient(id) {
        const url = '/pacientes/'+ id;
        const settings = {
            method: 'DELETE'
        };

        fetch(url, settings)
            .then(response => {
                if (response.ok) {
                    // If the delete operation was successful, remove the row from the table
                    document.getElementById('tr_' + id).remove();
                }
            });
    }

    // Event listener to handle delete button clicks
    document.getElementById("patientTable").addEventListener("click", function (event) {
        if (event.target && event.target.nodeName === "IMG") {
            const patientId = event.target.closest('tr').querySelector('.td_id').textContent;
            if (confirm("¿Estás seguro de que quieres eliminar a este paciente?")) {
                deletePatient(patientId);
            }
        }
    });
