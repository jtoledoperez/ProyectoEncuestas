<!DOCTYPE html>
<html>
<head>
    <title>Crear Pregunta</title>
</head>
<body>
    <h1>Crear Pregunta</h1>
    <form action="CrearPreguntasServlet" method="post">
        <!-- un boton que crea una encuesta -->
        
        <!-- un boton que crea pregunta asociada a la encuesta -->
        <label for="textoPregunta">Texto de la pregunta:</label><br>
        <input type="text" id="textoPregunta" name="textoPregunta${formularioContador}" required><br><br>

        <h3>Respuestas:</h3>
        <!-- un boton que crea respuestas asociadas a la pregunta -->
         <!-- input con contador -->
        <label for="res1${formularioContador}">Respuesta 1:</label><br>
        <input type="text" id="res1" name="pre${formularioContador}res1" required><br><br>
        
        <label for="respuesta1">Respuesta 1:</label><br>
        <input type="text" name="res1" required><br><br>

        <label for="respuesta2">Respuesta 2:</label><br>
        <input type="text" name="res2" required><br><br>

        <label for="respuesta3">Respuesta 3:</label><br>
        <input type="text" name="res3" required><br><br>

        <label for="respuesta4">Respuesta 4:</label><br>
        <input type="text" name="res4" required><br><br>

        <!-- Campo oculto para el id de la encuesta -->
        <input type="hidden" name="idEncuesta" value="${idEncuesta}">
        
        <!-- Mensaje error al procesar los datos -->
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
