
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Encuesta" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Encuestas Disponibles</title>
</head>

<body>
    <div class="container">
        <h1>Encuestas Activas</h1>

    
        <% if (request.getAttribute("mensaje") != null) { %>
            <div class="info">
                <p><%= request.getAttribute("mensaje") %></p>
            </div>
        <% } %>

     
        <% if (request.getAttribute("encuestas") != null) { %>
            <ul>
                <% List<Encuesta> encuestas = (List<Encuesta>) request.getAttribute("encuestas"); %>
                <% for (Encuesta encuesta : encuestas) { %>
                    <li>
                        <strong><%= encuesta.getNombre() %></strong> 
                        (Caduca: <%= encuesta.getCaducidad() %>)
                    </li>
                <% } %>
            </ul>
        <% } %>

       
        <div class="back-link">
            <a href="index.jsp">Volver al Inicio</a>
        </div>
    </div>

</body>
</html>
