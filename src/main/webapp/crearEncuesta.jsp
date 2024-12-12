<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Encuesta y Pregunta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/styles/styles.css" rel="stylesheet">
</head>
<body>
    <header class="py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <img src="assets/img/serbatic_logo_black.svg" class="logo" width="150px">
            <h1>Encuestas Serbatic 2024</h1>
            <div class="login">                           
            </div>
        </div>
    </header>
    <div class="container form-container">
        <h1>Crear Nueva Encuesta</h1>
        <% if (request.getAttribute("mensajeError") != null) { %>
            <div class="error">
                <p><%= request.getAttribute("mensajeError") %></p>
            </div>
        <% } %>
        <% if (request.getAttribute("mensajeExito") != null) { %>
            <div class="success">
                <p><%= request.getAttribute("mensajeExito") %></p>
            </div>
        <% } %>
        <form action="crear-encuesta" method="post">
            <div class="form-group">
                <label for="nombreEncuesta">Nombre de la Encuesta:</label>
                <input type="text" class="form-control" id="nombreEncuesta" name="nombreEncuesta" required>
            </div>
            <div class="form-group">
                <label for="fechaCaducidad">Fecha de Caducidad:</label>
                <input type="date" id="fechaCaducidad" name="fechaCaducidad" required>
            </div>
            <button type="submit">Crear Encuesta</button>
        </form>
    </div>
    <div class="container form-container">
        <h1>Crear Pregunta</h1>
        <form action="crear-pregunta" method="post">
            <div class="mb-3">
                <label for="textoPregunta" class="form-label">Texto de la pregunta:</label>
                <input type="text" class="form-control" id="pregunta" name="pregunta">
            </div>
            <h3>Respuestas:</h3>            
            <div class="mb-3">
                <label for="res1${formularioContador}" class="form-label">Respuesta 1:</label>
                <input type="text" class="form-control" name="respuesta1">
            </div>
            <div class="mb-3">
                <label for="respuesta2" class="form-label">Respuesta 2:</label>
                <input type="text" class="form-control" name="respuesta2">
            </div>
            <div class="mb-3">
                <label for="respuesta3" class="form-label">Respuesta 3:</label>
                <input type="text" class="form-control" name="respuesta3">
            </div>
            <div class="mb-3">
                <label for="respuesta4" class="form-label">Respuesta 4:</label>
                <input type="text" class="form-control" name="respuesta4">
            </div>
            <input type="hidden" name="idEncuesta" value="<%= session.getAttribute("idEncuesta") %>">
           <% 
			    String error = (String) request.getAttribute("error");
			    if (error != null) { 
			%>
			    <div>
			        <p><%= error %></p>
			    </div>
			<% } %>

            <button type="submit" class="btn btn-success">Crear Pregunta</button>
        </form>
    </div>
    <footer class="py-3">
        <p>&copy; 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
    </footer>

    <!-- Scripts de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>

