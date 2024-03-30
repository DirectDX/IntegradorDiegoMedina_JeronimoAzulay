function deleteTurno(turnoId) {
    const url = '/turnos/' + turnoId;
    const settings = {
        method: 'DELETE'
    };
    fetch(url, settings)
    .then(response => {
      if (response.ok) {
        // If the delete operation was successful, remove the row from the table
        document.getElementById('tr_' + id).remove();
        console.log("llegue a la linea")
        window.location.reload();
        
      } else {
        throw new Error('Error al eliminar el turno');
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
}




