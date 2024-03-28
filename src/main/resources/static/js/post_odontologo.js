window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo odontólogo
    const formulario = document.querySelector('#add_new_dentist');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

       //creamos un JSON que tendrá los datos del nuevo odontólogo
        const formData = {
            nombre: document.querySelector('#add_nombre').value,
            apellido: document.querySelector('#add_apellido').value,
            matricula: document.querySelector('#add_matricula').value
        };

        //invocamos utilizando la función fetch la API odontólogos con el método POST que guardará
        //el odontólogo que enviaremos en formato JSON
        const url = '/odontologos';
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
                 //Si no hay ningun error se muestra un mensaje diciendo que el odontólogo
                 //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong>Odontólogo agregado:</strong> El odontólogo ha sido agregado correctamente.</div>';

                 document.querySelector('#add_response').innerHTML = successAlert;
                 document.querySelector('#add_response').style.display = "block";
                 resetUploadForm();
                 

            })
            .catch(error => {
                    //Si hay algun error se muestra un mensaje diciendo que el odontólogo
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong>Error:</strong> Hubo un problema al agregar el odontólogo. Intente nuevamente.</div>';

                      document.querySelector('#add_response').innerHTML = errorAlert;
                      document.querySelector('#add_response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otro odontólogo
                     resetUploadForm();
            });

        // Prevenir el comportamiento por defecto del formulario
        event.preventDefault();
        
    });


    function resetUploadForm(){
        document.querySelector('#add_nombre').value = "";
        document.querySelector('#add_apellido').value = "";
        document.querySelector('#add_matricula').value = "";
        getDentists();
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/odontologos.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
