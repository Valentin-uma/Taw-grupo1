<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo1.entity.Feedback" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    Map<String, List<Feedback>> feedbackMap = (Map<String, List<Feedback>>) request.getAttribute("feedbackMap");
    String fechaMax = (String) request.getAttribute("fechaMax");
    String fechaMin = (String) request.getAttribute("fechaMin");
%>
<html>
    <head>
        <title>Historial</title>
    </head>
    <body>
    <nav>
        <a href="/miRutina">Mi rutina</a>
        <a href="/feedback">Feedback</a>
        <a>Historial</a>
        <a href="/salir">Cerrar Sesion</a>
    </nav>
    <h1>Historial de entrenamientos</h1>
        <form:form modelAttribute="filtroHistorial" action="filtrarHistorial" method="post">
            Desde el dia: <form:input path="fechaMin" type="date" value="<%= fechaMin %>"/><br>
            Hasta el dia: <form:input path="fechaMax" type="date" value="<%= fechaMax %>"/><br>
            <form:radiobutton path="completo" value="si" label="Completo"/>
            <form:radiobutton path="completo" value="no" label="No Completo"/><br>
            <form:button>Filtrar</form:button>
        </form:form>
        <table>
            <%
                if(!feedbackMap.keySet().isEmpty()){
            %>
                <tr>
                    <th>Fecha</th>
                    <th>Feedback</th>
                </tr>
            <%
                for(String fecha: feedbackMap.keySet()) {
            %>
                    <tr style="background-color: lightblue; margin: 3px; padding: 3px; border-radius: 3px; border: 2px solid black">
                        <td><%= fecha.split(" ")[0] %></td>
                        <td>
                            <%
                                for(Feedback feedback: feedbackMap.get(fecha)) {
                            %>
                            Ejercicio: <b><%= feedback.getSesionHasEjercicio().getEjercicioIdejercicio().getNombre() %></b><br>
                            Tenias que hacer <b><%= feedback.getSesionHasEjercicio().getRepeticiones() %></b> repeticiones y <b><%= feedback.getSesionHasEjercicio().getSeries() %></b> series con peso <b><%= feedback.getSesionHasEjercicio().getPeso() %></b><br>
                            Has hecho <b><%= feedback.getRepeticiones() %></b> repeticiones y <b><%= feedback.getSeries() %></b> series con peso <b><%= feedback.getPeso() %></b><br>
                            Comentarios: <%= feedback.getDescripcion() %><br>
                            <a href="/eliminarFeedback?feedbackId=<%= feedback.getId() %>">Eliminar</a>
                            <%
                                }
                            %>
                        </td>
                    </tr>
            <%
                    }
                }else{
            %>
                    No hemos encontrado feedbacks
            <%
                }
            %>
        </table>
    </body>
</html>