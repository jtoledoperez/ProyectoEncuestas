
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Encuestas Serbatic 2024</title>
    <!-- Enlazamos con Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f4;
        }
        header {
            background-color: #2c3e50;
            color: white;
        }
        header .login {
            margin: 0;
        }
        .crear-encuesta-btn {
            display: none;
            margin-top: 20px;
        }
        footer {
            margin-top: 40px;
            text-align: center;
            color: #7f8c8d;
        }
        .encuesta-item {
            border: 1px solid #ddd;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 15px;
            background-color: #fff;
        }
        .encuesta-item h5 {
            margin: 0;
        }
    </style>
</head>
<body>
    <!-- Barra de navegación -->
    <header class="py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <h1>Encuestas Serbatic 2024</h1>
            <div class="login">
                <!-- Enlace para iniciar sesión que redirige a login.jsp -->
                <a href="login.jsp" class="btn btn-primary">Iniciar Sesión</a>
            </div>
        </div>
    </header>

    <div class="container mt-4">
        <h2 class="mb-4">Lista de Encuestas</h2>

        <!-- Lista de encuestas -->
        <div class="row">
            <c:forEach var="encuesta" items="${encuestas}">
                <div class="col-md-4">
                    <div class="encuesta-item">
                        <h5>${encuesta.titulo}</h5>
                        <p>${encuesta.descripcion}</p>
                        <a href="verEncuesta.jsp?id=${encuesta.id}" class="btn btn-info">Ver Encuesta</a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Botón para crear encuesta -->
        <button class="btn btn-success crear-encuesta-btn" id="crearEncuestaBtn">Crear Encuesta</button>
        
        <!-- Sección Acerca de -->
        <div class="mt-5">
            <h3>Acerca de</h3>
            <p>Encuestas Serbatic 2024 es una plataforma para crear y gestionar encuestas de manera sencilla.</p>
        </div>
    </div>

    <!-- Pie de página -->
    <footer class="py-3">
        <p>© 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
    </footer>

    <!-- Scripts de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    
    <script>
        // Simulación de estado de inicio de sesión
        const isLoggedIn = <c:out value="${sessionScope.loggedIn}" default="false" />;

        // Mostrar el botón de crear encuesta si el usuario ha iniciado sesión
        if (isLoggedIn) {
            document.getElementById('crearEncuestaBtn').style.display = 'block';
        }
    </script>
</body>
</html>
