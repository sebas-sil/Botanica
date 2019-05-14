<%@page import="br.com.botanica.object.Planta"%>
<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<fmt:setBundle basename="resources.application" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Inventário</title>
<link rel="stylesheet" href="css/listagem.css">
<link rel="icon" href="imagens/favicon.jpg" />
</head>
<body>
	<jsp:include page="/header.jsp"  />
	<table>
		<tr>
			<th><fmt:message key="listagem.table.id"/></th>
			<th><fmt:message key="listagem.table.nome"/></th>
			<th><fmt:message key="listagem.table.local"/></th>
			<th><fmt:message key="listagem.table.preco"/></th>
			<th><fmt:message key="listagem.table.imagem"/></th>
		</tr>
		<c:forEach var="planta" items="${plantas}">
		<tr>
			<td><c:out value="${planta.id}"/></td>
			<td><c:out value="${planta.nome}"/></td>
			<td><c:out value="${planta.localizacao}"/></td>
			<td><fmt:formatNumber currencySymbol="R$" value="${planta.preco}" type="currency" /></td>
			<td><img alt="<c:out value="${planta.nome}"/>" src="<c:out value="${planta.imagem}"/>"></td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>