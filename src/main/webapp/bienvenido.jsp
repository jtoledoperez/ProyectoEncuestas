<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
</head>
<body>

<h2>Bienvenido, <%= ((modelo.Usuario) session.getAttribute("usuario")).getNombre() %>!</h2>
<p>Has iniciado sesi�n correctamente.</p>




	<form action="crear-encuesta" method="POST">
	<button type="submit" >Crear Encuesta</button>

	
	<form action="listar-encuesta" method="POST">
	<button type="submit" >Ver encuestas</button>
	
	
	<form action="logout" method="POST">
	<button type="submit" >LogOut</button>
</form>
</body>
</html>
