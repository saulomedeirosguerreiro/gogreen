<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoGreen</title>
<link rel="icon" href="resources/img/icon.png">
<link rel="stylesheet" type="text/css" href="css/layout.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
</head>
<body>
		<div id="content">
		<img src="resources/img/logo.png" alt="GoGreen" />
			<form action="auth?action=login" method="POST">
				<input name="email" type="email" placeholder="Seu e-mail" required/> 
				<input name="password" type="password" placeholder="Sua senha secreta" required />
				<button type="submit">Acessar</button>
				<a href="/gogreen/register.jsp">Criar conta</a>
			</Form>
		</div>
</body>
</html>