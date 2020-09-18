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
<link
	href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<%
	User user = (User) session.getAttribute("user");
	String title = user != null ? "Bem-Vindo, \n" + user.getName() : "Atenção, Sua sessão expirou !";
%>
<body>
	<output class="title welcome"><%=title%></output>
	<div class="w3-sidebar w3-bar-block w3-black w3-xxlarge" style="width:70px">
	  <a href="/gogreen/presential.jsp" class="w3-bar-item w3-button"><i class="fa fa-street-view"></i></a> 
	  <a href="/gogreen/control.jsp" class="w3-bar-item w3-button"><i class="fa fa-spinner"></i></a> 
	  <a href="/gogreen/extra.jsp" class="w3-bar-item w3-button"><i class="fa fa-plus"></i></a> 
	  <a href="/gogreen/activity.jsp" class="w3-bar-item w3-button"><i class="fa fa-database"></i></a>
	   <a href="/gogreen/client.jsp" class="w3-bar-item w3-button"><i class="fa fa-users"></i></a> 
	  <a href="/gogreen/point.jsp" class="w3-bar-item w3-button"><i class="fa fa-paw"></i></a> 
	  <a href="auth?action=logout" class="w3-bar-item w3-button"><i class="fa fa-sign-out"></i></a> 
	  
	</div>
</body>
</html>