<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="br.com.g4flex.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoGreen</title>
<link rel="icon" href="resources/img/icon.png">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<%
	User user = (User)session.getAttribute("user");
	String title = user != null? "Seja Bem-Vindo, " + user.getName() : "Atenção, Sua sessão expirou !"; 
%>
<body>
	<div id="content">
		<output class="title"><%=title%></output>
			<a class="btn" href="/gogreen/presential.jsp"><i class="fa fa-wrench "></i> Chamado Presencial</a>
			<a class="btn" href="/gogreen/point.jsp"><i class="fa fa-paw"></i>  Bata seu ponto</a>
			<a class="btn" href="/gogreen/extra.jsp"><i class="fa fa-plus"></i>  Atividade Extra</a>
			<a class="btn" href="/gogreen/activity.jsp"><i class="fa fa-database"></i>  Nova Atividade</a>
			<a class="btn" href="auth?action=logout"><i class="fa fa-sign-out"></i>  Sair</a>
	</div>
</body>
</html>