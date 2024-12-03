<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Map, modelo.Encuesta"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Realizar Encuesta</title>
</head>
<body>
    <h1>Realizar Encuesta</h1>

    <%
    modelo.Encuesta encuesta = (modelo.Encuesta) request.getAttribute("encuesta");
    Map<modelo.Pregunta, List<modelo.Respuesta>> preguntaRespuestasMap =
            (Map<modelo.Pregunta, List<modelo.Respuesta>>) request.getAttribute("preguntaRespuestasMap");

    if (encuesta != null) {
    %>
        <h2>Encuesta: <%= encuesta.getNombre() %></h2>
        <form action="guardar-respuestas" method="post">
            <!-- Campo oculto para identificar la encuesta -->
            <input type="hidden" name="idEncuesta" value="<%= encuesta.getIdEncuesta() %>">

            <%
            if (preguntaRespuestasMap != null && !preguntaRespuestasMap.isEmpty()) {
                for (Map.Entry<modelo.Pregunta, List<modelo.Respuesta>> entry : preguntaRespuestasMap.entrySet()) {
                    modelo.Pregunta pregunta = entry.getKey();
                    List<modelo.Respuesta> respuestas = entry.getValue();
            %>
                <div>
                    <h3>Pregunta: <%= pregunta.getTexto() %></h3>
                    <%
                    if (respuestas != null && !respuestas.isEmpty()) {
                        for (modelo.Respuesta respuesta : respuestas) {
                    %>
                        <label>
                            <input type="radio" name="respuesta_<%= pregunta.getIdPregunta() %>" value="<%= respuesta.getIdRespuesta() %>" required>
                            <%= respuesta.getTexto() %>
                        </label><br>
                    <%
                        }
                    } else {
                    %>
                        <p>No hay respuestas para esta pregunta.</p>
                    <%
                    }
                    %>
                </div>
            <%
                }
            } else {
            %>
                <p>No hay preguntas disponibles para esta encuesta.</p>
            <%
            }
            %>
        </form>
    <%
    } else {
    %>
        <p>No se pudo cargar la encuesta.</p>
    <%
    }
    %>
</body>
</html>
