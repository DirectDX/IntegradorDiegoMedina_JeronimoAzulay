
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
                } else {
                    throw new Error('Error al eliminar el paciente');
                  }
            })
            .catch(error => {
                console.error('Error:', error);
              });
    }


