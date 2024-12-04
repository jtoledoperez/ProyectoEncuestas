<%@ page import="java.util.List" %>
<%@ page import="modelo.Encuesta" %>

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
        List<Encuesta> encuestas = (List<Encuesta>) request.getAttribute("encuestas");
        if (encuestas != null && !encuestas.isEmpty()) {
    %>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre Encuesta</th>
                <th>Nombre Usuario</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Encuesta encuesta : encuestas) {
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
