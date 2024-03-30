function deleteTurno(turnoId) {
    fetch(`/turnos/${turnoId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            // Remove the row from the table if deletion is successful
            const rowToDelete = document.querySelector(`#turnTableBody tr[data-turno-id="${turnoId}"]`);
            rowToDelete.closest('tr').remove();
            alert('Turno eliminado correctamente');
        } else {
            alert('Error al eliminar el turno');
        }
    })
    .catch(error => console.error('Error al eliminar el turno:', error));
}
