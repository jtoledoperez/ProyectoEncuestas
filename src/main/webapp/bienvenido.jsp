<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
</head>
<body>

<h2>Bienvenido, <%= ((modelo.Usuario) session.getAttribute("usuario")).getNombre() %>!</h2>
<p>Has iniciado sesi�n correctamente.</p>

<a href="logout.jsp">Cerrar sesi�n</a>

</body>
</html>
