<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/layout.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
</head>
<body>
		<div id="content">
		<img src="resources/img/logo.png" alt="GoGreen" />
			<form action="auth" method="POST">
				<input name="email" type="email" placeholder="Seu e-mail" /> <input
					name="password" type="password" placeholder="Sua senha secreta" />
				<button type="submit">Acessar</button>
				<a href="/gogreen/register.jsp">Criar conta</a>
			</Form>
		</div>
</body>
</html>