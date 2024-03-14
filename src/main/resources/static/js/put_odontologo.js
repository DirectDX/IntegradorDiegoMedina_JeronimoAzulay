window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo odontólogo
        const formulario = document.querySelector('#put_dentist');
            const listaOdontologos = document.querySelector('#list_dentist');

            // Fetching data from the dentist API para hacer la lista
            const url = '/odontologos';
            const settings = {
                method: 'GET'
            };

            fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                    const select = document.getElementById("list_dentist");

                    // Itera sobre los datos de los odontólogos y agrega opciones al select
                    data.forEach(dentist => {
                        const option = document.createElement("option");
                        option.value = dentist.id; // Asigna el ID del odontólogo al valor de la opción
                        option.textContent = `${dentist.nombre} ${dentist.apellido}`; // Texto a mostrar en la opción
                        select.appendChild(option);
                    });
                })
                .catch(error => console.error('Error al obtener la lista de odontólogos:', error));

        // Adding "active" class to the last link in the nav if the pathname matches
        let pathname = window.location.pathname;
        if (pathname === "/odontologoLista.html") {
            document.querySelector(".nav .nav-item:last-child a").classList.add("active");
        }

    });

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

       //creamos un JSON que tendrá los datos del nuevo odontólogo
        const formData = {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };
        //invocamos utilizando la función fetch la API odontólogos con el método POST que guardará
        //el odontólogo que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 //Si no hay ningun error se muestra un mensaje diciendo que el odontólogo
                 //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Odontólogo agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    //Si hay algun error se muestra un mensaje diciendo que el odontólogo
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otro odontólogo
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#id').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/dentistList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});