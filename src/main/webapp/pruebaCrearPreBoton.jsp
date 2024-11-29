<!DOCTYPE html>
<html>
<head>
    <title>Bienvenido</title>
</head>
<body>
    <h1>Vista con el botón Crear Pregunta</h1>
    <form action="BotonCrearPreguntaServlet" method="get">
        <!-- Campo oculto para el id de la encuesta -->
         <input type="hidden" name="idEncuesta" value="1">
        <!--  <input type="hidden" name="idEncuesta" value="${idEncuesta}">    -->      
        <button type="submit">Crear Pregunta</button>
    </form>
</body>
</html>
