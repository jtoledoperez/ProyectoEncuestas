<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
</head>
<body>

<h2>Bienvenido, <%= ((modelo.Usuario) session.getAttribute("usuario")).getNombre() %>!</h2>
<p>Has iniciado sesión correctamente.</p>

<a href="logout.jsp">Cerrar sesión</a>

</body>
</html>
