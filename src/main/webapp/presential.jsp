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
	<a href="/gogreen/home.jsp"><i class="fa fa-home"></i>  Tela Inicial > Chamado Presencial</a>
		<form action="presential" method="POST">
		<output class="title">Chamado Presencial</output>
			<input name="call_number" type="number" placeholder="Número do Chamado" required/>
			<input name="client_name" type="text" placeholder="Nome do Cliente"  required/>
			<output>Data da Atividade</output>
			<input name="activity_date" type="date" required/>
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>
	<table id="customers">
  <tr>
    <th>Número do Chamado</th>
    <th>Analista</th>
    <th>Cliente</th>
    <th>Data da Atividade</th>

  </tr>
  <tr>
    <td>044126</td>
    <td>Leandro Sobral</td>
    <td>Fama</td>
    <td>01/10/2019</td>
  </tr>
   <tr>
    <td>03226</td>
    <td>Jessika Ramalho</td>
    <td>Pague Menos</td>
    <td>02/10/2019</td>
  </tr>
 

  
</table>
</body>
</html>