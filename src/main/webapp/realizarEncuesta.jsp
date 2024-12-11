<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Map, modelo.Encuesta"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Realizar Encuesta</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/styles/styles.css" rel="stylesheet">
</head>
<body>    
    <header class="py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <img src="assets/img/serbatic_logo_black.svg" class="logo" width="150px">
            <h1>Encuestas Serbatic 2024</h1>
        </div>
    </header>

    <div class="container mt-4">
        <h1 class="mb-4">Realizar Encuesta</h1>

        <%
        modelo.Encuesta encuesta = (modelo.Encuesta) request.getAttribute("encuesta");
        Map<modelo.Pregunta, List<modelo.Respuesta>> preguntaRespuestasMap =
                (Map<modelo.Pregunta, List<modelo.Respuesta>>) request.getAttribute("preguntaRespuestasMap");

        if (encuesta != null) {
        %>
            <h2 class="mb-3">Encuesta: <%= encuesta.getNombre() %></h2>            
            <div class="mb-3">
                <strong>Respuestas contestadas: <span id="contadorRespuestas">0</span> de <%= preguntaRespuestasMap.size() %></strong>
                <div class="progress">
                    <div class="progress-bar" role="progressbar" id="barraProgreso" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="<%= preguntaRespuestasMap.size() %>"></div>
                </div>
            </div>

            <form onsubmit="return redirigirAIndex()" method="post">
                <input type="hidden" name="idEncuesta" value="<%= encuesta.getIdEncuesta() %>">

                <%
                if (preguntaRespuestasMap != null && !preguntaRespuestasMap.isEmpty()) {
                    for (Map.Entry<modelo.Pregunta, List<modelo.Respuesta>> entry : preguntaRespuestasMap.entrySet()) {
                        modelo.Pregunta pregunta = entry.getKey();
                        List<modelo.Respuesta> respuestas = entry.getValue();
                %>
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Pregunta: <%= pregunta.getTexto() %></h5>
                        <%
                        if (respuestas != null && !respuestas.isEmpty()) {
                            for (modelo.Respuesta respuesta : respuestas) {
                        %>
                        <div class="form-check">
                            <input class="form-check-input" 
                                   type="radio" 
                                   name="respuesta_<%= pregunta.getIdPregunta() %>" 
                                   value="<%= respuesta.getIdRespuesta() %>"
                                   onchange="actualizarContador()">
                            <label class="form-check-label">
                                <%= respuesta.getTexto() %>
                            </label>
                        </div>
                        <%
                            }
                        } else {
                        %>
                        <p class="text-muted">No hay respuestas disponibles para esta pregunta.</p>
                        <%
                        }
                        %>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <div class="alert alert-warning" role="alert">
                    No hay preguntas disponibles para esta encuesta.
                </div>
                <%
                }
                %>
                <div class="text-center mt-3">
                    <button type="submit" class="btn btn-success">Enviar Respuestas</button>
                </div>
            </form>
        <%
        } else {
        %>
            <div class="alert alert-danger" role="alert">
                No se pudo cargar la encuesta.
            </div>
        <%
        }
        %>
    </div>
    <footer class="py-3">
        <p>© 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
    </footer>    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>    
    <script>
        let preguntasContestadas = 0;
        const totalPreguntas = <%= preguntaRespuestasMap != null ? preguntaRespuestasMap.size() : 0 %>;

        function actualizarContador() {
            const respuestasSeleccionadas = new Set();
            const radios = document.querySelectorAll('input[type="radio"]');

            radios.forEach(radio => {
                if (radio.checked) {
                    respuestasSeleccionadas.add(radio.name);
                }
            });

            preguntasContestadas = respuestasSeleccionadas.size;

            document.getElementById('contadorRespuestas').innerText = preguntasContestadas;

            const porcentajeProgreso = (preguntasContestadas / totalPreguntas) * 100; 
            document.getElementById('barraProgreso').style.width = porcentajeProgreso + "%";
            document.getElementById('barraProgreso').setAttribute('aria-valuenow', preguntasContestadas);
        }

        function redirigirAIndex() {
            window.location.href = "index.jsp";
            return false;
        }
    </script>
</body>
</html>

