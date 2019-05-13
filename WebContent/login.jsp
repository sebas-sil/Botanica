<%@page import="br.com.botanica.object.Planta"%>
<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<fmt:setBundle basename="resources.application" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Autentique-se</title>
<link rel="icon" href="imagens/favicon.jpg" />
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container">
		<form action="j_security_check" method="POST">
			<div>
				<img class="mb-4" src="imagens/login_flower.png" alt="" width="250" height="159">
			</div>
			<div>
				<label for="login"><fmt:message key="login.label.login"/></label>
				<input type="text" name="j_username" placeholder="login">
			</div>
			<div>
				<label for="senha"><fmt:message key="login.label.senha"/></label>
				<input type="password" name="j_password" placeholder="senha">
			</div>
			<button type="submit"><fmt:message key="login.label.submit"/></button>
		</form>
		<p><c:out value="${error_mgn}"/></p>
	</div>
</body>
</html>