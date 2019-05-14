<%@page import="br.com.botanica.object.Planta"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Novo registro</title>
<link rel="stylesheet" href="css/novo.css">
<link rel="icon" href="imagens/favicon.jpg" />
</head>
<body>
	<jsp:include page="/header.jsp" />
	<form method="post" action="PlantaNovo">
		<div>
			<label for="nome"><fmt:message key="novo.label.nome" /></label>
			<input type="text" name="nome" placeholder="nome comum daplanta">
		</div>
		<div>
			<label for="preco"><fmt:message key="novo.label.preco" /></label>
			<input type="number"  min="0" value="0" step=".01" name="preco" placeholder="preco em reais">
		</div>
		<div>
			<label for="localizacao"><fmt:message key="novo.label.localizacao" /></label>
			<input type="text" name="local" placeholder="localizacao na loja">
		</div>
		<div>
			<label for="imagem"><fmt:message key="novo.label.imagem" /></label>
			<input type="text" name="imagem" placeholder="data:image/webp;base64 da imagem">
		</div>
		<button type="submit">
			<fmt:message key="novo.label.salvar" />
		</button>
		<p><c:out value="${error_mgn}"/></p>
	</form>
</body>
</html>