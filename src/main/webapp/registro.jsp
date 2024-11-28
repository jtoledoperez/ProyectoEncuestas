<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
</head>
<body>

<h2>Registro de Usuario</h2>


<% if (request.getAttribute("error") != null) { %>
    <p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>


<% if (request.getAttribute("success") != null) { %>
    <p style="color: green;"><%= request.getAttribute("success") %></p>
<% } %>

<form action="RegistroServlet" method="POST">
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required><br><br>
    
    <label for="apellidos">Apellidos:</label>
    <input type="text" id="apellidos" name="apellidos" required><br><br>
    
    <label for="email">Correo Electrónico:</label>
    <input type="email" id="email" name="email" required><br><br>
    
    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required><br><br>
    
    <button type="submit">Registrar</button>
</form>

</body>
</html>
