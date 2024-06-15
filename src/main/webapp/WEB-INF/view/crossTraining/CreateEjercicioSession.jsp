<%@ page import="es.taw.grupo1.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo1.entity.Usuario" %>
<%@ page import="es.taw.grupo1.entity.Cliente" %>
<%@ page import="es.taw.grupo1.entity.Ejercicio" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%

    List<Ejercicio> ejerciciosList = (List<Ejercicio>) request.getAttribute("ejercicios");

%>
<head>
    <title>Crear una rutina</title>

</head>
<body>
<h2>Crear Rutina de Entrenamiento</h2>
<form action="/generarRutina" method="post">
    <label for="sesiones">Número de sesiones semanales:</label>
    <input type="number" id="sesiones" name="sesiones" required><br><br>

    <select id="ejerciciosSeleccionados" name="ejerciciosSeleccionados" multiple required>
        <%
            for (Ejercicio ejercicio : ejerciciosList) {


        %>
        <option value="<%= ejercicio.getId() %>"><%= ejercicio.getNombre() %></option>
        <%
            }
        %>

    </select><br>

    <label for="repeticiones">Número de repeticiones por ejercicio:</label>
    <input type="number" id="repeticiones" name="repeticiones" required><br><br>

    <label for="peso">Peso (kg) por ejercicio:</label>
    <input type="number" id="peso" name="peso" required><br><br>

    <input type="submit" value="Generar Rutina">
</form>
</body>
</html>
