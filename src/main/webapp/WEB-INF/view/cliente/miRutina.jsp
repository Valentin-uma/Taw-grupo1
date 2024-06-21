<%@ page import="es.taw.grupo1.entity.Rutina" %>
<%@ page import="es.taw.grupo1.entity.Sesion" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo1.entity.SesionHasEjercicio" %><%
    Rutina rutina = (Rutina) request.getAttribute("rutina");
    List<SesionHasEjercicio> sesionHasEjercicioList = (List<SesionHasEjercicio>) request.getAttribute("sesionHasEjerciciosList");
%>
<html>
    <head>
        <title>Mi Rutina</title>
    </head>
    <body>
        <h1>Mi rutina: </h1>
        <%= rutina.getNombre() %> - <%= rutina.getTipo() %> - <%= rutina.getDescripcion() %>
        <h2>Sesiones: </h2>
        <%
            for(SesionHasEjercicio sesionHasEjercicio: sesionHasEjercicioList){
        %>
                <div><%= sesionHasEjercicio.getSesionIdsesion().getDia() %> - <%= sesionHasEjercicio.getEjercicioIdejercicio().getNombre() %></div>
        <%
            }
        %>
        <br><a href="/salir">Salir</a>
    </body>
</html>