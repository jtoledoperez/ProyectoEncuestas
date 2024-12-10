<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Encuesta y Pregunta</title>
    <!-- Enlace a Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f4;
        }
        header {
            background-color: #78C0E0;
            color: black;
        }
        header .login {
            margin: 0;
        }
        footer {
            margin-top: 40px;
            text-align: center;
            color: #78C0E0;
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
        }
        .form-container h1 {
            margin-bottom: 20px;
        }
        .form-container input {
            margin-bottom: 10px;
        }
        .form-container button {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <!-- Barra de navegación -->
    <header class="py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <img src="assets/img/serbatic_logo_black.svg" class="logo" width="150px">
            <h1>Encuestas Serbatic 2024</h1>
            <div class="login">
                <!-- Enlace para iniciar sesión que redirige a login.jsp -->
                <form action="login" method="POST">
                    <button type="submit" class="btn btn-primary">Inicia sesión</button>
                </form>
            </div>
        </div>
    </header>

    <!-- Contenedor del formulario de creación de encuesta -->
    <div class="container form-container">
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
            <div class="form-group mb-3">
                <label for="nombreEncuesta">Nombre de la Encuesta:</label>
                <input type="text" class="form-control" id="nombreEncuesta" name="nombreEncuesta" required>
            </div>          
              <label for="fechaCaducidad">Fecha de Caducidad:</label>
   			  <input type="date" id="fechaCaducidad" name="fechaCaducidad" required>
   			  <input type="hidden" name="idEncuesta" value="<%= session.getAttribute("idEncuesta") %>">
            <button type="submit" class="btn btn-success">Crear Encuesta</button>
        </form>
    </div>

    <!-- Formulario de creación de pregunta -->
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

            <!-- Campo oculto para el id de la encuesta -->
            <input type="hidden" name="idEncuesta" value="<%= session.getAttribute("idEncuesta") %>">

            <!-- Mensajes de error -->
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

    <!-- Pie de página -->
    <footer class="py-3">
        <p>&copy; 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
    </footer>

    <!-- Scripts de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>

