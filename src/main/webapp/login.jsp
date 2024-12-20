<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Encuestas Serbatic 2024</title>
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

.login-form {
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

.register-link {
	text-align: center;
	margin-top: 20px;
}
</style>
</head>
<body>
	<!-- Barra de navegaciÃ³n -->
	<header class="py-3">
		<div
			class="container d-flex justify-content-between align-items-center">
			<img src="assets/img/serbatic_logo_white.svg" class="logo"
				width="150px">
			<h1>Encuestas Serbatic 2024</h1>
		</div>
	</header>

	<div class="container mt-4">
		<div class="login-form">
			<h2 class="mb-4 text-center">Iniciar SesiÃ³n</h2>
			<%
			if (request.getAttribute("error") != null) {
			%>
			<p style="color: red;"><%=request.getAttribute("error")%></p>
			<%
			}
			%>
			<!-- Formulario de login -->
			<form action="LoginServlet" method="POST">
				<div class="mb-3">
					<label for="email" class="form-label">Correo ElectrÃ³nico</label>
					<input type="email" class="form-control" id="email" name="email"
						required>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">ContraseÃ±a</label> <input
						type="password" class="form-control" id="password" name="password"
						required>
				</div>
				<button type="submit" class="btn btn-primary w-100">Iniciar
					SesiÃ³n</button>
			</form>

			<!-- Enlace para redirigir a la pÃ¡gina de registro -->
			<div class="register-link">
				<p>
					Â¿No tienes cuenta? <a href="registro.jsp">RegÃ­strate</a>
				</p>
			</div>
		</div>
	</div>

	<!-- Pie de pÃ¡gina -->
	<footer class="py-3">
		<p>Â© 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
	</footer>

	<!-- Scripts de Bootstrap -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>