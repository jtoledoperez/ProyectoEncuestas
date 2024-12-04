<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, modelo.Encuesta"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Encuestas Disponibles</title>
</head>
<body>
    <h1>Encuestas Disponibles para Realizar</h1>

    <% 
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) { 
    %>
        <p><%= mensaje %></p>
    <% 
    } else { 
        List<modelo.Encuesta> encuestas = (List<modelo.Encuesta>) request.getAttribute("encuestas");
        if (encuestas != null && !encuestas.isEmpty()) {
    %>
        <ul>
            <% 
            for (modelo.Encuesta encuesta : encuestas) { 
            %>
                <li>
                    <p><strong><%= encuesta.getNombre() %></strong></p>
                    <form action="realizar-encuesta" method="post">
                        <input type="hidden" name="idEncuesta" value="<%= encuesta.getIdEncuesta() %>">
                        <button type="submit">Realizar Encuesta</button>
                    </form>
                </li>
            <% 
            } 
            %>
        </ul>
    <% 
        } else { 
    %>
        <p>No hay encuestas disponibles para realizar.</p>
    <% 
        } 
    } 
    %>
</body>
</html>
