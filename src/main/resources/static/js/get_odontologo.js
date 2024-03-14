window.addEventListener('load', function () {

    // Fetching data from the dentist API
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {

            // Accessing the dentistTable and adding rows dynamically
            var table = document.getElementById("dentistTable");
            data.forEach(dentist => {
                let tr_id = 'tr_' + dentist.id;
                var dentistRow = table.insertRow();
                dentistRow.id = tr_id;
                
                // Adding data to the table cells
                dentistRow.innerHTML =
                    '<td class="td_id">' + dentist.id + '</td>' +
                    '<td class="td_nombre">' + dentist.nombre.toUpperCase() + '</td>' +
                    '<td class="td_apellido">' + dentist.apellido.toUpperCase() + '</td>' +
                    '<td class="td_matricula">' + dentist.matricula + '</td>';
            });
        });

    // Adding "active" class to the last link in the nav if the pathname matches
    let pathname = window.location.pathname;
    if (pathname === "/odontologoLista.html") {
        document.querySelector(".nav .nav-item:last-child a").classList.add("active");
    }

});