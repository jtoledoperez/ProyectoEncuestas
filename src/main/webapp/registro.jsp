<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registro - Encuestas Serbatic 2024</title>
<!-- Enlazamos con Bootstrap CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f4f4f4;
}

header {
	background-color: #78C0E0;
	color: white;
}

header .login {
	margin: 0;
}

.registro-form {
	max-width: 500px;
	margin: 50px auto;
	padding: 30px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

footer {
	margin-top: 40px;
	text-align: center;
	color: #78C0E0;
}
</style>
</head>
<body>
	<!-- Barra de navegaciÃƒÂ³n -->
	<header class="py-3">
		<div
			class="container d-flex justify-content-between align-items-center">
			<img src="assets/img/serbatic_logo_white.svg" class="logo"
				width="150px">
			<h1>Encuestas Serbatic 2024</h1>
		</div>
	</header>

	<div class="container mt-4">
		<div class="registro-form">
			<h2 class="mb-4 text-center">Crear Cuenta</h2>
			<%
			if (request.getAttribute("error") != null) {
			%>
			<p style="color: red;"><%=request.getAttribute("error")%></p>
			<%
			}
			%>


			<%
			if (request.getAttribute("success") != null) {
			%>
			<p style="color: green;"><%=request.getAttribute("success")%></p>
			<%
			}
			%>		
			<form action="registro" method="POST">
				<div class="mb-3">
					<label for="nombre" class="form-label">Nombre</label> <input
						type="text" class="form-control" id="nombre" name="nombre"
						required>
				</div>
				<div class="mb-3">
					<label for="apellidos" class="form-label">Apellidos</label> <input
						type="text" class="form-control" id="apellidos" name="apellidos"
						required>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Correo Electrónico</label>
					<input type="email" class="form-control" id="email" name="email"
						required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Contraseña</label> <input
						type="password" class="form-control" id="password" name="password"
						required>
				</div>
				<!--<div class="mb-3">
					<label for="confirmPassword" class="form-label">Confirmar
						ContraseÃƒÂ±a</label> <input type="password" class="form-control"
						id="confirmPassword" name="confirmPassword" required>
				</div> -->
				
				<button type="submit" class="btn btn-primary w-100">Registrarse</button>
			</form>
		</div>
	</div>	
	<footer class="py-3">
		<p>© 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
	</footer>

	<!-- Scripts de Bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>