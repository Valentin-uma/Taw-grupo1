<%@ page import="es.taw.grupo1.entity.SesionHasEjercicio" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    List<SesionHasEjercicio> sesionHasEjercicioList = (List<SesionHasEjercicio>) request.getAttribute("listaEjercicios");
    String textoFecha = (String) request.getAttribute("textoFecha");
    if(textoFecha == null){
        textoFecha = "";
    }
%>
<html>
    <head>
        <title>Feedback</title>
    </head>
    <body>
        <nav>
            <a href="/miRutina">Mi rutina</a>
            <a>Feedback</a>
            <a href="/historial">Historial</a>
            <a href="/salir">Cerrar Sesion</a>
        </nav>
        <h1>Feedback</h1>
        <h2>Cuentanos que tal te ha ido</h2><br>

        <form:form modelAttribute="formFecha" action="filtrarPorFecha" method="post">
            Fecha: <form:input path="fecha" type="date" value="<%= textoFecha %>"/>
            <form:button>Buscar</form:button>
        </form:form>

        <table style="border: 2px solid black; border-collapse: collapse; max-width: 200px">
            <tr>
                <%
                    if(sesionHasEjercicioList != null && !sesionHasEjercicioList.isEmpty()){
                        for(SesionHasEjercicio sesionHasEjercicio: sesionHasEjercicioList){
                %>
                <div style="background-color: lightblue; margin: 3px; padding: 3px; border-radius: 3px">
                    Ejercicio: <span style="font-weight: bold"><%= sesionHasEjercicio.getEjercicioIdejercicio().getNombre() %></span><br>
                    Series: <span style="font-weight: bold"><%= sesionHasEjercicio.getSeries() %></span><br>
                    Repeticiones: <span style="font-weight: bold"><%= sesionHasEjercicio.getRepeticiones() %></span><br>
                    Peso: <span style="font-weight: bold"><%= sesionHasEjercicio.getPeso()%></span><br>
                    <form:form modelAttribute="formSubmitFeedback" method="post" action="submitFeedback">
                        Series realizadas: <form:input type="number" path="seriesRealizadas"/><br>
                        Repeticiones realizadas: <form:input type="number" path="repeticionesRealizadas"/><br>
                        Peso utilizado: <form:input type="number" path="pesoUtilizado"/><br>
                        Comentarios: <br><form:textarea path="comentarios"/>
                        <form:hidden path="fechaSubmit" value="<%= textoFecha %>"/>
                        <form:hidden path="sesionId" value="<%= sesionHasEjercicio.getEjercicioIdejercicio().getId() %>"/>
                        <form:hidden path="ejercicioId" value="<%= sesionHasEjercicio.getSesionIdsesion().getId() %>"/><br>
                        <form:button>Guardar</form:button>
                    </form:form>
                </div>
                <%
                    }
                }else if(sesionHasEjercicioList != null && sesionHasEjercicioList.isEmpty()){
                %>
                No hay entrenamiento para este dia. Disfruta del descanso!
                <%
                }
                %>
            </tr>

        </table>

    </body>
</html>