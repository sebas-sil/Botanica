<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<fmt:setBundle basename="resources.application" /> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>
<% session.invalidate(); %>
<fmt:message key="logout.logout.msg"><fmt:param value="${pageContext.request.userPrincipal.name}"/></fmt:message>
</body>
</html>