<%@ page import="es.taw.grupo1.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo1.entity.Usuario" %>
<%@ page import="es.taw.grupo1.entity.Cliente" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%

    List<Rutina> rutinasList = (List<Rutina>) request.getAttribute("rutinas");
    List<Cliente> clientesList = (List<Cliente>) request.getAttribute("clientes");
    List<String> typesRutinas = (List<String>) request.getAttribute("typesRutinas");


%>
<head>
    <title>Entrenador page</title>
    <style><%@include file="./static/CrossHome.css"%></style>

</head>
<body>

<div class="sidebar">
    <h2>Rutinas</h2>
    <form action="/crossFiltrar" method="post">

        <select name="filtro">
            <option value="null"></option>
            <%for (String type : typesRutinas){
            %>
            <option value="<%=type%>"><%=type%></option>
            <%}%>
        </select>

        <input type="submit" value="Filtrar">
    </form>
    <%for (Rutina rutina: rutinasList){%>
        <div class="rutina">
            <h3><%=rutina.getNombre()%></h3>
            <p><%=rutina.getDescripcion()%></p>
            <p>Tipo: <%=rutina.getTipo()%></p>
            <button onclick="editRutina(<%=rutina.getId()%>)">Editar</button>
            <button onclick="deleteRutina(<%=rutina.getId()%>)">Borrar</button>
        </div>
    <%};%>
    <button class="add-button" onclick="createRutina()">+</button>
</div>

<div class="sidebar">
    <h2>Clients</h2>
    <%for (Cliente cliente: clientesList){%>
        <div class="client">
            <h3><%=cliente.getUsuarioIdusuario().getNombre()%></h3>
            <p><%=cliente.getUsuarioIdusuario().getEmail()%></p>
            <button onclick="editClient(<%=cliente.getId()%>)">Editar</button>
        </div>
    <%};%>
</div>

<script>
    function editRutina(id) {
        window.location.href = '/editRutina/'+id;
    }
    function createRutina() {
        window.location.href = '/createRutina';
    }
    function editClient(id) {
        window.location.href = '/manageClient/'+id;
    }
    function deleteRutina(idRutina) {
        if (confirm("Are you sure you want to delete this Rutina?")) {
            fetch('/rutina/'+idRutina, {
                method: 'DELETE'
            })
                .then(response => {
                    window.location.reload(); // Refresh the page
                })

        }
    }
</script>
</body>
</html>
