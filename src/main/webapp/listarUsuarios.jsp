<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="modelo.Usuario"%>
<html>
<head>
<title>Lista de Usuarios</title>
<style>
.hidden {
	display: none;
}
</style>
</head>
<body>
	<h2>Usuarios Registrados</h2>

	<%
	// Obtenemos la lista de usuarios que pasamos desde el servlet
	List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

	if (usuarios != null && !usuarios.isEmpty()) {
	%>
	<table border="1">
		<thead>
			<tr>
				<th class="hidden">ID</th>
				<th>Nombre</th>
				<th>Correo</th>
				<th>Rol</th>
			</tr>
		</thead>
		<tbody>
			<%
			// Recorremos la lista de usuarios
			for (Usuario usuario : usuarios) {
			%>
			<tr>
				<td class="hidden"><%=usuario.getIdUsuario()%></td>
				<td><%=usuario.getNombre() + " " + usuario.getApellidos()%></td>
				<td><%=usuario.getEmail()%></td>
				<td><%=usuario.getRol()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<%
	} else {
	%>
	<p>No hay usuarios registrados.</p>
	<%
	}
	%>
</body>
</html>
