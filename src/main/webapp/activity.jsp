<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div id="content">
	<a href="/gogreen/home.jsp"><i class="fa fa-home"></i>  Tela Inicial > Nova Atividade</a>
		<form action="activity" method="POST">
		<output class="title">Nova Atividade</output>
			<input name="name" type="text" placeholder="Nome da Atividade" required/>
			<input name="value" type="number" placeholder="Valor da Atividade"  required/>
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>
	<table id="customers">
  <tr>
    <th>Nome da Atividade</th>
    <th>Valor</th>

  </tr>
  <tr>
    <td>Ativação</td>
    <td>150</td>
  </tr>
   <tr>
    <td>Plantão Semanal</td>
    <td>120.20</td>
  </tr>
 

  
</table>
</body>
</html>