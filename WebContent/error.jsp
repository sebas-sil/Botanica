<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Algo errado aconteceu</title>
<link rel="icon" href="imagens/favicon.jpg" />
</head>
<body>
	<jsp:include page="/header.jsp" />
	<p><c:out value="${error_mgn}"/></p>
</body>
</html>