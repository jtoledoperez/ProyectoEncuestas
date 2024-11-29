<!DOCTYPE html>
<html>
<head>
<title>Encuestas Disponibles</title>
</head>
<body>
	<h1>Encuestas Disponibles</h1>


	<%
	String mensaje = (String) request.getAttribute("mensaje");
	if (mensaje != null) {
	%>
	<p><%=mensaje%></p>
	<%
	}
	%>


	<%
	java.util.List<modelo.Encuesta> encuestas = (java.util.List<modelo.Encuesta>) request.getAttribute("encuestas");
	if (encuestas != null && !encuestas.isEmpty()) {
	%>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nombre Encuesta</th>
				<th>Nombre usuario</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (modelo.Encuesta encuesta : encuestas) {
			%>
			<tr>
				<td><%=encuesta.getIdEncuesta()%></td>
				<td><%=encuesta.getNombre()%></td>
			 <td><%=encuesta.getUsuario() != null ? encuesta.getUsuario().getNombre() : "Sin usuario"%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<%
	} else {
	%>
	<p>No hay encuestas disponibles.</p>
	<%
	}
	%>
</body>
</html>
