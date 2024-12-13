<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registro - Encuestas Serbatic 2024</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/styles/styles.css" rel="stylesheet">
</head>
<body>
	<header class="py-3">
		<div
			class="container d-flex justify-content-between align-items-center">
			<a href="index.jsp">
    <img src="assets/img/serbatic_logo_black.svg" class="logo" width="150px" alt="Logo Serbatic">
</a>
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
			<form action="registro" class="needs-validation" novalidate method="POST">
				<div class="mb-3">
					<label for="nombre" class="form-label">Nombre</label> <input
						type="text" class="form-control" pattern="[A-Za-z ]+" id="nombre" name="nombre"
						required>
						<div class="invalid-feedback">
						Utilice solo letras y espacios
						</div>
				</div>
				<div class="mb-3">
					<label for="apellidos" class="form-label">Apellidos</label> <input
						type="text" class="form-control" pattern="[A-Za-z ]+" id="apellidos" name="apellidos"
						required>
						<div class="invalid-feedback">
						Utilice solo letras y espacios
						</div>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Correo Electrónico</label> <input
						type="email" class="form-control" id="email" name="email" required>
						<div class="invalid-feedback">
						Correo invalido
						</div>
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Contraseña</label> <input
						type="password" class="form-control" id="password" name="password"
						required>
				</div>
				<button type="submit" class="btn btn-primary w-100">Registrarse</button>
			</form>
		</div>
	</div>
	<footer class="py-3">
		<p>© 2024 Encuestas Serbatic. Todos los derechos reservados.</p>
	</footer>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
		<script>
		(() => {
		  'use strict'
		  const forms = document.querySelectorAll('.needs-validation')
		  Array.from(forms).forEach(form => {
		    form.addEventListener('submit', event => {
		      if (!form.checkValidity()) {
		        event.preventDefault()
		        event.stopPropagation()
		      }
		      form.classList.add('was-validated')
		    }, false)
		  })
		})()

</script>

</body>
</html>