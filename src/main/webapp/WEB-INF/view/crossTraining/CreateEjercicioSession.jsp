<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo1.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo1.entity.Usuario" %>
<%@ page import="es.taw.grupo1.entity.Cliente" %>
<%@ page import="es.taw.grupo1.entity.Ejercicio" %>
<%@ page import="es.taw.grupo1.ui.EjercicioObject" %>
<%@ page import="jakarta.websocket.Session" %>
<%@ page import="es.taw.grupo1.ui.SessionObject" %>
<%@ page import="es.taw.grupo1.ui.RutinaObject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--
Autor: Valentin Pecqueux
-->

<!DOCTYPE html>
<html>

<% String dia = request.getParameter("dia");%>
<%

    List<Ejercicio> ejerciciosList = (List<Ejercicio>) request.getAttribute("ejercicios"); //list ejercicios from bdd


    String randomId = (String) request.getParameter("randomId");
    String edit = (String) request.getParameter("edit");
    String rutinaId = (String) request.getParameter("rutinaId");
    String addUrl = "/saveEjercicioToSession/null";
    if(edit.equals("true")){
        addUrl = "/saveEjercicioToSession/"+rutinaId;
    }

    System.out.println("RANDOM ID");
    System.out.println(randomId);

    List<SessionObject> sessionObjects = (List<SessionObject>) session.getAttribute("sessions"); //Toutes les sessions d'entrainement

    EjercicioObject ejercicioFromSession = new EjercicioObject();

    for (SessionObject sessionObject : sessionObjects) {
        if(sessionObject.getDia().equals(dia)){
            for (EjercicioObject ejercicioObjectBoucle : sessionObject.getEjercicios()){
                if(ejercicioObjectBoucle.getIdSessionEx().equals(randomId)){
                    ejercicioFromSession = ejercicioObjectBoucle;
                }
            }
        }
    }



%>
<head>
    <title>Crear un ejercicio</title>
    <style><%@include file="./static/CreateEjercicioSessionStyle.css"%></style>


</head>
<body>
<h2>Añadir un ejercicio</h2>

<form:form action="<%=addUrl%>" method="post" modelAttribute="ejercicioObject">

    <form:hidden path="dia" value="<%=dia%>"/>
    <form:hidden path="idSessionEx" value="<%=randomId%>"/>

    <form:select id="ejercicioSeleccionado" name="ejercicioSeleccionado" path="idejercicio">
        <%
            for (Ejercicio ejercicio : ejerciciosList) {
                System.out.println(ejercicio.getId());
                System.out.println(ejercicioFromSession.getIdejercicio());
                System.out.println(ejercicio.getId() == ejercicioFromSession.getIdejercicio());

                if (ejercicio.getId().equals(ejercicioFromSession.getIdejercicio())) {



        %>

        <form:option  value="<%= ejercicio.getId() %>" selected="true"><%= ejercicio.getNombre() %></form:option >

        <%
            }else{
        %>
        <form:option  value="<%= ejercicio.getId() %>"><%= ejercicio.getNombre() %></form:option >
        <% }}%>

    </form:select>
    <br>

    <label for="series">Número de series:</label>
    <form:input type="number" id="series" name="series" path="numeroSeries" value="<%=ejercicioFromSession.getNumeroSeries()%>"/><br><br>


    <label for="repeticiones">Número de repeticiones:</label>
    <form:input type="number" id="repeticiones" name="repeticiones" path="numeroRepeticion" value="<%=ejercicioFromSession.getNumeroRepeticion()%>"/><br><br>

    <label for="peso">Peso (kg):</label>
    <form:input type="number" id="peso" name="peso" path="peso" value="<%=ejercicioFromSession.getPeso()%>"/><br><br>

    <form:button>Save</form:button>

</form:form>

</body>
</html>
