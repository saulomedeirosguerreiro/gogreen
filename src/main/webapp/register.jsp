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
				<input name="name" type="text" placeholder="Nome completo" maxlength="100" required/> <input
					name="email" type="email" placeholder="Seu e-mail" maxlength="150" required/> <input
					name="password" type="password" placeholder="Sua senha secreta" maxlength="100" required />
				<button type="submit">Criar Conta</button>
				<a href="/gogreen">Já tenho login</a>
			</Form>
		</div>
</body>
</html>