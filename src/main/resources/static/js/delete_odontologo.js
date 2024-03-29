document.addEventListener("DOMContentLoaded", function() {
    // Function to delete dentist
    function deleteDentist(id) {
        const url = '/odontologos/' + id;
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
 // Event listener para manejar clics en los botones de eliminación
 document.getElementById("dentistTableBody").addEventListener("click", function(event) {
    if (event.target && event.target.nodeName === "IMG") {
        const dentistId = event.target.closest('tr').querySelector('.td_id').textContent;
        if (confirm("¿Estás seguro de que quieres eliminar a este odontólogo?")) {
            deleteDentist(dentistId);
        }
    }
});
});