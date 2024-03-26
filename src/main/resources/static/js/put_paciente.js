document.addEventListener('DOMContentLoaded', function () {
    const formulario = document.querySelector('#update_patient_form');

    // Event listener for the form submission
    formulario.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent page reload on form submission

        // Create a JSON object with the patient data from the form
        const formData = {
            id: parseInt(document.querySelector('#put_patient_id').value),
            nombre: document.querySelector('#put_nombre').value,
            apellido: document.querySelector('#put_apellido').value,
            dni: document.querySelector('#put_dni').value,
            fechaIngreso: document.querySelector('#put_fechaIngreso').value,
            calle: document.querySelector('#put_calle').value,
            numero: document.querySelector('#put_numero').value,
            localidad: document.querySelector('#put_localidad').value,
            provincia: document.querySelector('#put_provincia').value
        };

        // Call the PUT endpoint to update the patient data
        const url = `/pacientes`;
        const settingsPut = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settingsPut)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error updating patient data');
                }
                return response.json();
            })
            .then(data => {
                // If successful, show a success message
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Success!</strong> Patient data updated successfully.</div>';

                document.querySelector('#put_response').innerHTML = successAlert;
                document.querySelector('#put_response').style.display = "block";
                resetForm();
                // Optionally, you can reload the page to reflect the changes
                // window.location.reload();
            })
            .catch(error => {
                // If an error occurs, show an error message
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error:</strong> ' + error.message + '</div>';

                document.querySelector('#put_response').innerHTML = errorAlert;
                document.querySelector('#put_response').style.display = "block";
            });
    });

    // Function to reset the form fields
    function resetForm() {
        document.querySelector('#put_patient_id').value = "";
        document.querySelector('#put_nombre').value = "";
        document.querySelector('#put_apellido').value = "";
        document.querySelector('#put_dni').value = "";
        document.querySelector('#put_fechaIngreso').value = "";
        document.querySelector('#put_calle').value = "";
        document.querySelector('#put_numero').value = "";
        document.querySelector('#put_localidad').value = "";
        document.querySelector('#put_provincia').value = "";
    }
    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
