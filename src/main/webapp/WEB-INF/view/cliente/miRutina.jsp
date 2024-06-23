<%@ page import="es.taw.grupo1.entity.Rutina" %>
<%@ page import="es.taw.grupo1.entity.Sesion" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo1.entity.SesionHasEjercicio" %>
<%@ page import="es.taw.grupo1.entity.Usuario" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    Rutina rutina = (Rutina) request.getAttribute("rutina");
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    Map<String, List<SesionHasEjercicio>> sesionHasEjercicioMap = (Map<String, List<SesionHasEjercicio>>) request.getAttribute("sesionHasEjerciciosMap");

%>
<html>
    <head>
        <title>Mi Rutina</title>
    </head>
    <body>
        <nav>
            <a>Mi rutina</a>
            <a href="/feedback">Feedback</a>
            <a href="/historial">Historial</a>
            <a href="/salir">Cerrar Sesion</a>
        </nav>
        <h1>Bienvenido, <%= usuario.getNombre() %> </h1>
        <%= rutina.getNombre() %>, de tipo <%= rutina.getTipo() %>: <%= rutina.getDescripcion() %>
        <h2>Ejericios: </h2>
        <table style="border: 2px solid black; border-collapse: collapse;">
            <tr style="border: 2px solid black;">
                <th>Dia</th>
                <th>Ejercicios</th>
            </tr>
            <%
                for(String dia: List.of("L","M","X","J","V","S","D")){
                    List<SesionHasEjercicio> sesionHasEjercicioList;
                    try{
                        sesionHasEjercicioList = sesionHasEjercicioMap.get(dia);
                        if(sesionHasEjercicioList == null){
                            continue;
                        }
                    }catch(Exception e){
                        continue;
                    }

            %>
                <tr style="border: 2px solid black;">
                    <td><b><%= dia %></b></td>
                    <td>
                        <%
                            for(SesionHasEjercicio sesionHasEjercicio: sesionHasEjercicioList){
                        %>
                                <div style="background-color: lightblue; margin: 3px; padding: 3px; border-radius: 3px">
                                    Ejercicio: <span style="font-weight: bold"><%= sesionHasEjercicio.getEjercicioIdejercicio().getNombre() %></span><br><br>
                                    Grupo muscular: <span style="font-weight: bold"><%= sesionHasEjercicio.getEjercicioIdejercicio().getGrupoMuscular() %></span><br>
                                    Dificultad: <span style="font-weight: bold"><%= sesionHasEjercicio.getEjercicioIdejercicio().getDificultad() %></span><br>
                                    Calorias: <span style="font-weight: bold"><%= sesionHasEjercicio.getEjercicioIdejercicio().getCalorias() %></span><br><br>
                                    Series: <span style="font-weight: bold"><%= sesionHasEjercicio.getSeries() %></span><br>
                                    Repeticiones: <span style="font-weight: bold"><%= sesionHasEjercicio.getRepeticiones() %></span><br>
                                    Peso: <span style="font-weight: bold"><%= sesionHasEjercicio.getPeso()%></span><br>
                                    <a href="<%= sesionHasEjercicio.getEjercicioIdejercicio().getUrlDemo()%>">Video</a>
                                </div>
                        <%
                            }
                        %>
                    </td>
                </tr>
            <%
                }
            %>
        </table>
    </body>
</html>