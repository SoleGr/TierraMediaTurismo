function confirmarEliminacion(id) {
    document.getElementById("userId").value = id;
    document.getElementById("modalEliminar").style.display = 'block';
}

function eliminarUsuario() {
    var id = document.getElementById("userId").value;
    $.ajax({
        type: "DELETE",
        url: "/turismo/attractions/delete.do?id=${attraction." + id + "}",
        success: function (result) {
            location.reload(true);
        }
    });
}


function cerrarModal() {
    document.getElementsByClassName("modal")[0].style.display = 'none';
}