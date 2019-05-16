<%@page import="br.com.botanica.object.Planta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<fmt:setBundle basename="resources.application" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventário</title>
<link rel="stylesheet" href="css/listagem.css">
</head>
<body>
	Forma 3: Escrita utilizando jstl (Esse sim, utilize!!!)
	<table>
		<tr>
			<th><fmt:message key="listagem.table.id"/></th>
			<th><fmt:message key="listagem.table.nome"/></th>
			<th><fmt:message key="listagem.table.local"/></th>
			<th><fmt:message key="listagem.table.preco"/></th>
		</tr>
		<c:forEach var="planta" items="${plantas}">
		<tr>
			<td><c:out value="${planta.id}"/></td>
			<td><c:out value="${planta.nome}"/></td>
			<td><c:out value="${planta.localizacao}"/></td>
			<td><fmt:formatNumber currencySymbol="R$" value="${planta.preco}" type="currency" /></td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>