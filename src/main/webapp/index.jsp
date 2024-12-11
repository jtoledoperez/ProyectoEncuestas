<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Encuestas Serbatic 2024</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/styles/styles.css" rel="stylesheet">
  
</head>
<body>    
    <header class="py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <img src="assets/img/serbatic_logo_black.svg" class="logo" width="150px">
            <h1>Encuestas Serbatic 2024</h1>
            <div class="login">                
                <% 
                    HttpSession sesion = request.getSession(false); 
                    if (sesion == null || sesion.getAttribute("usuario") == null) { 
                %>                   
                    <form action="login" method="POST">
                        <button type="submit" class="btn btn-primary">Iniciar sesión</button>
                    </form>
                <% 
                    } else { 
                %>                   
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
        <div class="row">
            <c:forEach var="encuesta" items="${encuestas}">
                <div class="col-md-4">
                    <div class="encuesta-item">
                        <h5>${encuesta.titulo}</h5>
                        <p>${encuesta.descripcion}</p>                   
                    </div>
                </div>                        
                        <a href="listar-encuestas-disponibles" class="btn btn-info">Realizar Encuesta </a>                      
					</div>
            	</div>

            </c:forEach>
        </div>
    </div>
  
    <footer class="py-3">
        <p>© 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
    </footer>   
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
