window.addEventListener('load', function () {
    // Al cargar la página, buscamos y obtenemos el formulario donde estarán
    // los datos que el usuario cargará del nuevo paciente
    const formulario = document.querySelector('#add_new_patient');

    // Ante un submit del formulario se ejecutará la siguiente función
    formulario.addEventListener('submit', function (event) {
        // Creamos un JSON que tendrá los datos del nuevo paciente
        const formData = {
            nombre: document.querySelector('#add_nombre').value,
            apellido: document.querySelector('#add_apellido').value,
            dni: document.querySelector('#add_dni').value,
            fechaIngreso: document.querySelector('#add_fechaIngreso').value,
            domicilio: {
                calle: document.querySelector('#add_calle').value,
                numero: document.querySelector('#add_numero').value,
                localidad: document.querySelector('#add_localidad').value,
                provincia: document.querySelector('#add_provincia').value
            }
        };

        // Invocamos utilizando la función fetch la API pacientes con el método POST que guardará
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
            .then(response => response.json())
            .then(data => {
                // Si no hay ningún error se muestra un mensaje diciendo que el paciente
                // se agregó correctamente
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Paciente agregado:</strong> El paciente ha sido agregado correctamente. </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                // Si hay algún error se muestra un mensaje diciendo que el paciente
                // no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error:</strong> Hubo un problema al agregar el paciente. Intente nuevamente. </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                // Se dejan todos los campos vacíos por si se quiere ingresar otro paciente
                resetUploadForm();
            });

        // Prevenir el comportamiento por defecto del formulario
        event.preventDefault();
    });

    function resetUploadForm() {
        document.querySelector('#add_nombre').value = "";
        document.querySelector('#add_apellido').value = "";
        document.querySelector('#add_dni').value = "";
        document.querySelector('#add_fechaIngreso').value = "";
        document.querySelector('#add_calle').value = "";
        document.querySelector('#add_numero').value = "";
        document.querySelector('#add_localidad').value = "";
        document.querySelector('#add_provincia').value = "";
    }

    // Agregar la clase "active" al enlace correspondiente en la barra de navegación
    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname === "/pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
