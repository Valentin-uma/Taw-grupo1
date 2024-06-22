<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo1.entity.Feedback" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%
    Map<String, List<Feedback>> feedbackMap = (Map<String, List<Feedback>>) request.getAttribute("feedbackMap");
%>
<html>
    <head>
        <title>Historial</title>
    </head>
    <body>
    <h1>Historial de entrenamientos</h1>
        <form:form>
            Desde el dia: <form:input path="fechaMin"/><br>
            Hasta el dia: <form:input path="fechaMax"/><br>
            <%
                // Hay que hacer un select
            %>
        </form:form>

    </body>
</html>