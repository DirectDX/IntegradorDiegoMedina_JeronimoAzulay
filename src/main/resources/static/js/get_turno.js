window.addEventListener("load", function () {
  const patientTableBody = document.getElementById("turnTableBody");

  function getTurns() {
    fetch("/turnos/table")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error al obtener la lista de turnos");
        }
        return response.json();
      })
      .then((turns) => {
        // Once we get the list of patients, populate the table
        populateTurnTable(turns);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }

  function populateTurnTable(turns) {
    const tableBody = document.getElementById("turnTableBody");
    tableBody.innerHTML = "";

    turns.forEach((turno) => {
      // Create row for the turno
      const row = document.createElement("tr");

      // Turno data
      const turnoIdCell = document.createElement("td");
      turnoIdCell.textContent = turno.id;
      row.appendChild(turnoIdCell);

      const fechaCell = document.createElement("td");
      fechaCell.textContent = turno.fecha;
      row.appendChild(fechaCell);

      const horaCell = document.createElement("td");
      horaCell.textContent = turno.hora;
      row.appendChild(horaCell);

      // Odontologo data
      const odontologoNombreCell = document.createElement("td");
      odontologoNombreCell.textContent = turno.nombreOdontologo;
      row.appendChild(odontologoNombreCell);

      const odontologoApellidoCell = document.createElement("td");
      odontologoApellidoCell.textContent = turno.apellidoOdontologo;
      row.appendChild(odontologoApellidoCell);

      const matriculaCell = document.createElement("td");
      matriculaCell.textContent = turno.matriculaOdontologo;
      row.appendChild(matriculaCell);

      // Paciente data
      const pacienteNombreCell = document.createElement("td");
      pacienteNombreCell.textContent = turno.nombrePaciente;
      row.appendChild(pacienteNombreCell);

      const pacienteApellidoCell = document.createElement("td");
      pacienteApellidoCell.textContent = turno.apellidoPaciente;
      row.appendChild(pacienteApellidoCell);

      const dniCell = document.createElement("td");
      dniCell.textContent = turno.dniPaciente;
      row.appendChild(dniCell);

      // Create cell for "Eliminar" button
      const deleteCell = document.createElement("td");
      const deleteButton = document.createElement("button");
      deleteButton.type = "button";
      deleteButton.className = "btn btn-danger delete-btn";
      deleteButton.textContent = "Eliminar";
      deleteButton.addEventListener("click", function () {
        const turnoId =
          this.closest("tr").querySelector("td:first-child").textContent;
        if (confirm("¿Estás seguro de que quieres eliminar el turno?")) {
            deleteTurno(parseInt(turnoId));
            window.location.reload()
        }
      });
      deleteCell.appendChild(deleteButton);
      row.appendChild(deleteCell);

      // Append the row to the table body
      patientTableBody.appendChild(row);
    });
  }
  getTurns();
});
