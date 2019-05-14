<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/header.css">
<div class="topnav">
	<a class="${pageContext.request.servletPath == '/home.jsp' ? 'active' : 'none' }" href="#" >Home</a> 
	<a class="${pageContext.request.servletPath == '/listagem.jsp' ? 'active' : 'none' }" href="PlantaListagem">Inventario</a> 
	<a class="${pageContext.request.servletPath == '/novo.jsp' ? 'active' : 'none' }" href="novo.jsp">Novo</a> 
	<a class="${pageContext.request.servletPath == '/contato.jsp' ? 'active' : 'none' }" href="#contact">Contact</a>
	<div class="topnav-right">
		<a href="logout.jsp">Sair</a> 
		<a href="#" class="about"><%=request.getRemoteUser()%></a>
	</div>
</div>

