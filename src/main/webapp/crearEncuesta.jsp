<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Encuesta</title>
    <link rel="stylesheet" href="styles.css"> <!-- Incluye un CSS opcional para diseño -->
</head>
<body>
    <div class="container">
        <h1>Crear Nueva Encuesta</h1>

        <!-- Mostrar mensaje de error si existe -->
        <% if (request.getAttribute("mensajeError") != null) { %>
            <div class="error">
                <p><%= request.getAttribute("mensajeError") %></p>
            </div>
        <% } %>

        <!-- Mostrar mensaje de éxito si existe -->
        <% if (request.getAttribute("mensajeExito") != null) { %>
            <div class="success">
                <p><%= request.getAttribute("mensajeExito") %></p>
            </div>
        <% } %>

        <!-- Formulario para crear encuesta -->
        <form action="crear-encuesta" method="post">
            <div class="form-group">
                <label for="nombreEncuesta">Nombre de la Encuesta:</label>
                <input type="text" id="nombreEncuesta" name="nombreEncuesta" required>
            </div>
            <button type="submit">Crear Encuesta</button>
        </form>
        
    </form>

        <!-- Opción para regresar -->
        <div class="back-link">
            <a href="index.jsp">Volver al Inicio</a>
        </div>
    </div>
</body>
</html>
