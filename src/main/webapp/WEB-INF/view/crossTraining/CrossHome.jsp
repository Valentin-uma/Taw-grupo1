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

%>
<head>
    <title>Entrenador page</title>
    <style>
        body {
            display: flex;
            font-family: Arial, sans-serif;
        }
        .sidebar {
            width: 50%;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
            overflow-y: auto;
            height: 100vh;
        }
        .main-content {
            flex: 1;
            padding: 20px;
            overflow-y: auto;
        }
        .rutina, .client {
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .rutina button, .client button {
            margin-top: 10px;
        }
        .add-button {

            bottom: 20px;
            right: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 50%;
            cursor: pointer;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }
        .add-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="sidebar">
    <h2>Rutinas</h2>
    <%for (Rutina rutina: rutinasList){%>
        <div class="rutina">
            <h3>${rutina.name}</h3>
            <p>${rutina.description}</p>
            <a onclick="editRutina(<%=rutina.getId()%>)">Editar</a>
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
        window.location.href = '/editCliente/'+id;
    }
</script>
</body>
</html>
