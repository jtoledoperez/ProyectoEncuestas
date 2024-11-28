<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h2>Iniciar sesi�n</h2>


<% if (request.getAttribute("error") != null) { %>
    <p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="LoginServlet" method="POST">
    <label for="email">Correo Electr�nico:</label>
    <input type="email" id="email" name="email" required><br><br>
    
    <label for="password">Contrase�a:</label>
    <input type="password" id="password" name="password" required><br><br>
    
    <button type="submit">Iniciar sesi�n</button>
</form>

</body>
</html>
