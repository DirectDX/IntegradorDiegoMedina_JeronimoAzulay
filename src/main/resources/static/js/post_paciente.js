window.addEventListener('load', function () {

    // Al cargar la página buscamos y obtenemos el formulario donde estarán
    // los datos que el usuario cargará del nuevo paciente
    const formulario = document.querySelector('#add_new_patient');

    // Ante un submit del formulario se ejecutará la siguiente función
    formulario.addEventListener('submit', function (event) {
        console.log("llegamos al listener del formulario")

       // Creamos un JSON que tendrá los datos del nuevo paciente
        const formData = {
            nombre: document.querySelector('#add_nombre').value,
            apellido: document.querySelector('#add_apellido').value,
            dni: document.querySelector('#add_dni').value,
            fechaIngreso: document.querySelector('#add_fechaIngreso').value,
            domicilio: {
                calle: document.querySelector('#add_calle').value,
                numero: parseInt(document.querySelector('#add_numero').value),
                localidad: document.querySelector('#add_localidad').value,
                provincia: document.querySelector('#add_provincia').value
            }
        };

        console.log(formData)

        // Invocamos utilizando la función fetch la API de pacientes con el método POST que guardará
        // el paciente que enviaremos en formato JSON
        const url = '/pacientes';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Hubo un problema al agregar el paciente. Intente nuevamente.');
                }
                return response.json();
            })
            .then(data => {
                 // Si no hay ningún error se muestra un mensaje diciendo que el paciente
                 // se agregó correctamente
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong>Paciente agregado:</strong> El paciente ha sido agregado correctamente.</div>';

                 document.querySelector('#add_response').innerHTML = successAlert;
                 document.querySelector('#add_response').style.display = "block";
                 resetUploadForm();
            })
            .catch(error => {
                // Si hay algún error se muestra un mensaje diciendo que el paciente
                // no se pudo guardar y se intenta nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                 `<strong>Error:</strong> ${error.message}</div>`;

                document.querySelector('#add_response').innerHTML = errorAlert;
                document.querySelector('#add_response').style.display = "block";
                // Dejamos todos los campos vacíos por si se quiere ingresar otro paciente
                resetUploadForm();
                getPatients();
            });

        // Prevenir el comportamiento por defecto del formulario
        event.preventDefault();
        
    });

    function resetUploadForm(){
        document.querySelector('#add_nombre').value = "";
        document.querySelector('#add_apellido').value = "";
        document.querySelector('#add_dni').value = "";
        document.querySelector('#add_fechaIngreso').value = "";
        document.querySelector('#add_calle').value = "";
        document.querySelector('#add_numero').value = "";
        document.querySelector('#add_localidad').value = "";
        document.querySelector('#add_provincia').value = "";
    }
});
