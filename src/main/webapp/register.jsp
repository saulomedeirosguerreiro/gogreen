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
			<form action="user" method="POST">
				<input name="name" type="text" placeholder="Nome completo" /> <input
					name="email" type="email" placeholder="Seu e-mail" /> <input
					name="password" type="password" placeholder="Sua senha secreta" />
				<button type="submit">Criar Conta</button>
				<a href="/gogreen">Já tenho login</a>
			</Form>
		</div>
</body>
</html>