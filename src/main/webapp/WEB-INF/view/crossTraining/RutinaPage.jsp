<%@ page import="es.taw.grupo1.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo1.entity.Usuario" %>
<%@ page import="es.taw.grupo1.entity.Cliente" %>
<%@ page import="es.taw.grupo1.entity.Ejercicio" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<style>

    .add-button {

        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 50%;
        cursor: pointer;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2);
    }
</style>

<%

    List<Ejercicio> ejerciciosList = (List<Ejercicio>) request.getAttribute("ejercicios");

    String[] weekDays = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
%>


<head>

    <script>
        let weekDayEjercicios = new Map([
            ['Lunes', 0],
            ['Martes', 0],
            ['Miercoles', 0],
            ['Jueves', 0],
            ['Viernes', 0],
            ['Sabado', 0],
            ['Domingo', 0]
        ]);
    </script>
    <title>Crear una rutina</title>

</head>
<body>
<h1>Planifier votre routine</h1>
<form action="submitRoutine" method="post">
    <label for="routineName">Nom de la routine :</label>
    <input type="text" id="routineName" name="routineName" required><br><br>

    <h2>Contenu pour chaque jour :</h2>
    <table border="1">
        <tr>
            <th>Dia</th>
            <th></th>
        </tr>
        <%for (String day : weekDays){%>
        <tr>
            <div id="<%=day%>Add">
            <td><%=day%></td>
            </div>
            <td><button type="button" class="add-button" onclick="increment('<%=day%>');return false;">+</button></td>

        </tr>
        <%}%>

    </table>
    <br>
    <input type="submit" value="Enviar">
</form>


<script>

    function increment(day) {

        console.log("TEST");
        var jspIncludeElement = document.createElement('div');
        jspIncludeElement.setAttribute('data-jsp-include', 'CreateEjercicioSession.jsp');
        var td = document.createElement("td");
        td.appendChild(jspIncludeElement);
        document.getElementById(day+'Add').appendChild(td);


    }

</script>
</body>


</html>
