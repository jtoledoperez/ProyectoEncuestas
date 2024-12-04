<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Encuestas Serbatic 2024</title>
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
    </style>
</head>
<body>
    <!-- Barra de navegación -->
    <header class="py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <img src="assets/img/serbatic_logo_black.svg" class="logo" width="150px">
            <h1>Encuestas Serbatic 2024</h1>
            <div class="login">
                <!-- Verificar si el usuario está logueado -->
                <% 
                    HttpSession sesion = request.getSession(false); 
                    if (sesion == null || sesion.getAttribute("usuario") == null) { 
                %>
                    <!-- Si no está logueado, mostrar el botón de "Iniciar sesión" -->
                    <form action="login" method="POST">
                        <button type="submit" class="btn btn-primary">Iniciar sesi�n</button>
                    </form>
                <% 
                    } else { 
                %>
                    <!-- Si está logueado, mostrar botones de "Crear Encuesta" y "LogOut" -->
                    <form action="crear-encuesta" method="POST">
                        <button type="submit" class="btn btn-success">Crear Encuesta</button>
                    </form>
                    <form action="logout" method="POST">
                        <button type="submit" class="btn btn-danger">LogOut</button>
                    </form>
                <% 
                    } 
                %>
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

                        <a href="crear-encuesta" class="btn btn-info">Crear Encuesta</a>
                        <a href="listar-encuestas-disponibles" class="btn btn-info">Realizar Encuesta </a>                      
					</div>
            	</div>

            </c:forEach>
        </div>
    </div>
  
    <footer class="py-3">
        <p>� 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
    </footer>

    <!-- Scripts de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
