document.addEventListener('DOMContentLoaded', function () {
    const formulario = document.querySelector('#put_dentist');
    const selectDentists = document.getElementById("select_dentists");

    // Fetching data from the dentist API para hacer la lista
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            // Itera sobre los datos de los odontólogos y agrega opciones al select
            data.forEach(dentist => {
                const option = document.createElement("option");
                option.value = dentist.id; // Asigna el ID del odontólogo al valor de la opción
                option.textContent = `${dentist.nombre} ${dentist.apellido}`; // Texto a mostrar en la opción
                // Asigna los atributos data-* de la opción para su posterior uso
                option.dataset.id = dentist.id;
                option.dataset.nombre = dentist.nombre;
                option.dataset.apellido = dentist.apellido;
                option.dataset.matricula = dentist.matricula;
                selectDentists.appendChild(option);
            });
        })
        .catch(error => console.error('Error al obtener la lista de odontólogos:', error));

    // Event listener para manejar el cambio en el selector
    selectDentists.addEventListener('change', function () {
        const selectedOption = this.options[this.selectedIndex];
        // Asignar los valores de la opción seleccionada como placeholders en el formulario
        document.getElementById('id').placeholder = selectedOption.getAttribute('data-id');
        document.getElementById('nombre').placeholder = selectedOption.getAttribute('data-nombre');
        document.getElementById('apellido').placeholder = selectedOption.getAttribute('data-apellido');
        document.getElementById('matricula').placeholder = selectedOption.getAttribute('data-matricula');
    });

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault(); // Evita que se recargue la página al enviar el formulario

        //creamos un JSON que tendrá los datos del nuevo odontólogo
        const formData = {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };

        //invocamos utilizando la función fetch la API odontólogos con el método PUT que actualizará
        //el odontólogo que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                //Si no hay ningún error se muestra un mensaje diciendo que el odontólogo se actualizó correctamente
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Odontólogo actualizado </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
            .catch(error => {
                //Si hay algún error se muestra un mensaje diciendo que el odontólogo no se pudo actualizar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error: </strong> ' + error.message + '</div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
            });
    });

    function resetUploadForm() {
        document.querySelector('#id').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }

    // Verificar la ruta actual y agregar la clase "active" al enlace correspondiente en la barra de navegación
    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname === "/dentistList.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
