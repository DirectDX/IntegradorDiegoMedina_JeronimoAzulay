window.addEventListener('load', function () {

    // Fetching data from the dentist API
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {

            // Accessing the select element
            const select = document.getElementById("select_dentists");

            // Iterating through the data and adding options to the select
            data.forEach(dentist => {
                const option = document.createElement("option");
                option.value = dentist.id; // Assigning the ID of the dentist to the value of the option
                option.textContent = `${dentist.nombre} ${dentist.apellido}`; // Text to display in the option
                select.appendChild(option);
            });
        });

    // Adding "active" class to the last link in the nav if the pathname matches
    let pathname = window.location.pathname;
    if (pathname === "/odontologoLista.html") {
        document.querySelector(".nav .nav-item:last-child a").classList.add("active");
    }

});