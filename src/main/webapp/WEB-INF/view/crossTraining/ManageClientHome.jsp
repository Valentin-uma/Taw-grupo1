<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo1.entity.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--
Autor: Valentin Pecqueux
-->

<!DOCTYPE html>
<html>

<%

    List<Rutina> rutinasList = (List<Rutina>) request.getAttribute("rutinas");
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");





%>
<head>
    <title>Edit rutina</title>

    <style><%@include file="./static/ManageClientHome.css"%></style>


</head>
<body>
<div class="sidebar">
<h1>Informacion del cliente <%=cliente.getUsuarioIdusuario().getNombre()%></h1>


Nombre: <p><%=cliente.getUsuarioIdusuario().getNombre()%></p><br/>
Apellido: <p><%=cliente.getUsuarioIdusuario().getApellido()%></p><br/>
Edad: <p><%=cliente.getUsuarioIdusuario().getRangoEdad()%></p><br/>
Email: <p><%=cliente.getUsuarioIdusuario().getEmail()%></p><br/>


Rutina asignada :

<%
    int clienteIdRutina = -1;
    if (cliente.getRutinaIdrutina() != null){
        out.println(cliente.getRutinaIdrutina().getNombre());
        clienteIdRutina = cliente.getRutinaIdrutina().getId();
    }
    else {
        out.println("Sin rutina asignada");

    }
%><br/>

<p>Cambiar rutina ?</p><br/>
<form method="post" action="/changeRutinaAsinada">

    <input type="hidden" name="clienteId" value="<%=cliente.getId()%>">

    <select  id="rutinaSelect" name="rutinaSelect">
        <%
            for (Rutina rutina : rutinasList) {

                if (rutina.getId().equals(clienteIdRutina)) {

        %>

        <option  value="<%= rutina.getId() %>" selected><%= rutina.getNombre() %></option >

        <%
        }else{
        %>
        <option  value="<%= rutina.getId() %>"><%= rutina.getNombre() %></option >
        <% }}%>
    </select>

    <input type="submit" value="Cambiar rutina"/>
</form>
</div>
<div class="sidebar">
    <a href="/testcreateFeedback/<%=clienteIdRutina%>/<%=cliente.getId()%>">ADD FEEDBACK TEST</a>


    <h1>FeedBack del cliente</h1>

    <%for (Feedback feedback : feedbacks) {%>

    <h3>Feedback para la rutina: <%=feedback.getSesionHasEjercicio().getSesionIdsesion().getRutinaIdrutina().getNombre()%></h3>
    <h4>Para la session de los <%
    switch (feedback.getSesionHasEjercicio().getSesionIdsesion().getDia()){
        case "L":
            out.println("Lunes");
            break;
        case "M":
            out.println("Martes");
            break;
        case "X":
            out.println("Miercoles");
            break;
        case "J":
            out.println("Jueves");
            break;
        case "V":
            out.println("Viernes");
            break;
        case "S":
            out.println("Sabado");
            break;
        case "D":
            out.println("Domingo");
            break;
        default:
            out.println("Lunes");
            break;

    }
    %></h4>
    <h4>Feedback dado el <%=feedback.getFecha()%></h4>

    <%Ejercicio findEjercicio = null;
        int idEjercicio = feedback.getSesionHasEjercicio().getId().getEjercicioIdejercicio();
    for (Ejercicio ejercicio : ejercicios) {
        if (ejercicio.getId().equals(idEjercicio)) {
            findEjercicio = ejercicio;
        }
    }
        assert findEjercicio != null;
    %>

    <h4>Sobre el ejercicio: <%= findEjercicio.getNombre()%></h4>

    <h5>Numero de series realizadas: <%=feedback.getSeries()%></h5>
    <h5>Numero de repeticiones realizadas: <%=feedback.getRepeticiones()%></h5>
    <h5>Peso usado: <%=feedback.getPeso()%></h5>

    <p>Contenido: <%=feedback.getDescripcion()%></p>

    <%}%>

</div>





</body>
</html>
