<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo1.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo1.entity.Usuario" %>
<%@ page import="es.taw.grupo1.entity.Cliente" %>
<%@ page import="es.taw.grupo1.entity.Ejercicio" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="es.taw.grupo1.ui.EjercicioObject" %>
<%@ page import="org.hibernate.dialect.identity.SybaseJconnIdentityColumnSupport" %>
<%@ page import="es.taw.grupo1.ui.SessionObject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>



<%


    String[] weekDays = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"}; //static

    List<SessionObject> sessionObjects = (List<SessionObject>) session.getAttribute("sessions"); //Toutes les sessions d'entrainement

    String edit = (String) request.getAttribute("edit"); //modo edicion ?
    String rutinaName = (String) request.getAttribute("rutinaName"); //name if mod edition
    String rutinaDesc = (String) request.getAttribute("rutinaDesc"); //name if mod edition
    String rutinaId= (String) request.getAttribute("rutinaId"); //id if mod edition


%>


<head>

    <style><%@include file="./static/RutinaageStyle.css"%></style>

    <title>Crear una rutina</title>

</head>
<body>
<h1>Crear una rutina</h1>
<%if(edit.equals("true")){%>
<form method="post" action="/modifyRutina">
<%}else{%>
<form method="post" action="/submitRutina">
<%}%>


    <%if(edit.equals("true")){%>
    Nombre de la rutina: <input type="text" id="routineName" name="routineName" value="<%=rutinaName%>" required><br><br>
    Descripción de la rutina: <input type="text" id="routineDesc" name="routineDesc" value="<%=rutinaDesc%>" required><br><br>
    <input type="hidden" id="rutinaId" name="rutinaId" value="<%=rutinaId%>">
    <%}else{%>
    Nombre de la rutina: <input type="text" id="routineName" name="routineName" required><br><br>
    Descripcion de la rutina: <input type="text" id="routineDesc" name="routineDesc" required><br><br>
    <%}%>

    <button type="submit">Crear la rutina</button>
</form>

    <h2>Contenido de rutina</h2>
    <table border="1">
        <tr>
            <th>Dia</th>

        </tr>
        <%for (String day : weekDays){%>
        <tr>
            <div id="<%=day%>Add">
            <td><%=day%></td>

                <%for (SessionObject sessionObject : sessionObjects){
                    if(sessionObject.getDia().equals(day)){

                    for (EjercicioObject ejercicioObject : sessionObject.getEjercicios()) {


                %>

                <%

                    System.out.println(ejercicioObject.getIdSessionEx());%>
                <td>
                    <jsp:include page="CreateEjercicioSession.jsp" >
                        <jsp:param name="dia" value="<%=day%>" />
                        <jsp:param name="ejercicioObject" value="<%=ejercicioObject%>" />

                        <jsp:param name="randomId" value="<%=ejercicioObject.getIdSessionEx()%>" />

                        <jsp:param name="edit" value="<%=edit%>"/>
                        <jsp:param name="rutinaId" value="<%=rutinaId%>"/>

                    </jsp:include>

                    <%}}}%>

                </td>
            </div>
            <td>
                <button type="button" class="add-button" onclick="increment('<%=day%>')">+</button>

            </td>

        </tr>
        <%}%>

    </table>
    <br>



<script>

    function increment(day) {
        let edit = '<%=edit%>';
        let rutinaId = '<%=rutinaId%>';
        let addUrl = '/createEjercicioToSession/null?dia=' + encodeURIComponent(day);
        if (edit === "true"){
            addUrl = '/createEjercicioToSession/'+rutinaId+'?dia=' + encodeURIComponent(day);
        }
        console.log("TESTSa");

        window.location.href = addUrl
        //PAREIL IL FAUT 2 CHEMIN EDIT ET NON POUR GARDER LES MEME PARAM



    }

</script>
</body>


</html>
