window.addEventListener('load', function () {

    const url = '/pacientes';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            var tableBody = document.getElementById("patientTableBody");

            data.forEach(patient => {
                let tr_id = 'tr_' + patient.id;
                var patientRow = document.createElement("tr");
                patientRow.id = tr_id;

                patientRow.innerHTML =
                    '<td class="td_id">' + patient.id + '</td>' +
                    '<td class="td_nombre">' + patient.nombre.toUpperCase() + '</td>' +
                    '<td class="td_apellido">' + patient.apellido.toUpperCase() + '</td>' +
                    '<td class="td_dni">' + patient.dni + '</td>' +
                    '<td class="td_ingreso">' + patient.fechaIngreso + '</td>' +
                    '<td class="td_calle">' + patient.domicilio.calle + '</td>' +
                    '<td class="td_numero">' + patient.domicilio.numero + '</td>' +
                    '<td class="td_localidad">' + patient.domicilio.localidad + '</td>' +
                    '<td class="td_provincia">' + patient.domicilio.provincia + '</td>';

                tableBody.appendChild(patientRow);
            });
        });

    let pathname = window.location.pathname;
    if (pathname === "/pacienteLista.html") {
        document.querySelector(".nav .nav-item:last-child a").classList.add("active");
    }
});
