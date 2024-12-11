<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, modelo.Encuesta" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Encuestas Disponibles</title>    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f4;
        }
 
        header {
            background-color: #78C0E0;
            color: black;
            padding: 10px 0;
        }
 
        footer {
            margin-top: 40px;
            text-align: center;
            color: #78C0E0;
            padding: 10px 0;
        }
    </style>
</head>
<body>   
    <!-- Header -->
    <header class="py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <img src="assets/img/serbatic_logo_black.svg" class="logo" width="150px">
            <h1>Encuestas Serbatic 2024</h1>
        </div>
    </header>
 
    <!-- Contenido principal -->
    <div class="container mt-4">
        <h1 class="mb-4">Encuestas Disponibles para Realizar</h1>
 
        <%
            // Mostrar un mensaje si existe
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <div class="alert alert-info" role="alert">
                <%= mensaje %>
            </div>
        <%
            } else {
                // Listar encuestas si están disponibles
                List<modelo.Encuesta> encuestas = (List<modelo.Encuesta>) request.getAttribute("encuestas");
                if (encuestas != null && !encuestas.isEmpty()) {
        %>
            <div class="list-group">
                <%
                    for (modelo.Encuesta encuesta : encuestas) {
                %>
                    <div class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <p><strong><%= encuesta.getNombre() %></strong></p>
                            <form action="realizar-encuesta" method="post">
                                <input type="hidden" name="idEncuesta" value="<%= encuesta.getIdEncuesta() %>">
                                
                                <!-- Botón habilitado/deshabilitado según estado de la encuesta -->
                                <button type="submit" class="btn btn-primary" <%= encuesta.isCaducada() ? "disabled" : "" %>>
                                    <%= encuesta.isCaducada() ? "Esta encuesta está caducada" : "Realizar Encuesta" %>
                                </button>
                            </form>
                        </div>
                    </div>
                <%
                    }
                %>
            </div>
        <%
                } else {
        %>
            <!-- Mensaje cuando no hay encuestas disponibles -->
            <div class="alert alert-warning" role="alert">
                No hay encuestas disponibles para realizar.
            </div>
        <%
                }
            }
        %>
    </div>    
 
    <!-- Footer -->
    <footer class="py-3">
        <p>© 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
    </footer>
 
    <!-- Scripts de Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
 
 