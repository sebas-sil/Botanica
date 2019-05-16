<%@page import="br.com.botanica.object.Planta"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventário</title>
</head>
<body>
	Forma 2: Escrita utilizando scriptless (não utilize esta maneira)

	<%
	List<Planta> plantas = (List<Planta>) request.getAttribute("plantas");
	%>

	<table>

		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Local</th>
			<th>Preço</th>
		</tr>
		<%for(Planta planta : plantas){ %>
		<tr>
			<td><%=planta.getId() %></td>
			<td><%=planta.getNome() %></td>
			<td><%=planta.getLocalizacao() %></td>
			<td><%=planta.getPreco() %></td>
		</tr>
		<%} %>
	</table>

</body>
</html>