<!DOCTYPE html>
<html>
<head>
    <title>Crear Pregunta</title>
</head>
<body>
    <h1>Crear Pregunta</h1>
    <form action="CrearPreguntasServlet" method="post">
        <label for="textoPregunta">Texto de la pregunta:</label><br>
        <input type="text" id="textoPregunta" name="textoPregunta" required><br><br>

        <h3>Respuestas:</h3>
        <label for="respuesta1">Respuesta 1:</label><br>
        <input type="text" id="respuesta1" name="respuesta1" required><br><br>

        <label for="respuesta2">Respuesta 2:</label><br>
        <input type="text" id="respuesta2" name="respuesta2" required><br><br>

        <label for="respuesta3">Respuesta 3:</label><br>
        <input type="text" id="respuesta3" name="respuesta3" required><br><br>

        <label for="respuesta4">Respuesta 4:</label><br>
        <input type="text" id="respuesta4" name="respuesta4" required><br><br>

        <!-- Campo oculto para el ID de la encuesta -->
        <input type="hidden" name="idEncuesta" value="${idEncuesta}">
        
        <% 
    String error = request.getParameter("error");
    if (error != null) { 
%>
    <p style="color: red;">Error: <%= error %></p>
<% 
    } 
%>

        <button type="submit">Crear Pregunta</button>
    </form>
</body>
</html>
