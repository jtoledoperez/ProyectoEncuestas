<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h2>Iniciar sesión</h2>


<% if (request.getAttribute("error") != null) { %>
    <p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="LoginServlet" method="POST">
    <label for="email">Correo Electrónico:</label>
    <input type="email" id="email" name="email" required><br><br>
    
    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required><br><br>
    
    <button type="submit">Iniciar sesión</button>
</form>

</body>
</html>
