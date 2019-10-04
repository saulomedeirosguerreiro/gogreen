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
	<a href="/gogreen/home.jsp"><i class="fa fa-home"></i>  Tela Inicial > Bata seu ponto</a>
		<form action="point" method="POST">
		<output class="title">Olá, Bata o seu ponto</output>
			<select name="day_of_week" >
				<option value="">Dia da Semana</option>
				<option value="Segunda-feira">Segunda-feira</option>
				<option value="Terça-feira">Terça-feira</option>
				<option value="Quarta-feira">Quarta-feira</option>
				<option value="Quinta-feira">Quinta-feira</option>
				<option value="Sexta-feira">Sexta-feira</option>
			</select>
			<textarea name=justification rows="4" cols="50" placeholder="Justificativa"></textarea>
			<button class="btn" type="submit"><i class="fa fa-paw"></i>  Check-in/Check-out</button>
		</Form>
		<output>Check-in</output>
		<output>Check-out</output>
		<br/>
		<output>08:00:00</output>
		<output>--:--:--</output>
	</div>
		<table id="customers">
  <tr>
    <th>Data</th>
    <th>Dia da Semana</th>
    <th>Horário de Entrada</th>
    <th>Horário de Saída</th>
    <th>Horas Trabalahadas</th>
    <th>Analista</th>
    <th>Justificativa</th>

  </tr>
  <tr>
	<td>03/10/2019</td>
	<td>Quinta-feira</td>
	<td>08:00:00</td>
	<td>N/A</td>
	<td>N/A</td>
	<td>Felipe Soares</td>
	<td>Fui para aula, acabou não tendo, vim direto da UFC para o trabalho, cheguei ás 11h</td>
  </tr>
  <tr>
	<td>01/10/2019</td>
	<td>Terça-feira</td>
	<td>11:00:00</td>
	<td>18:30:00</td>
	<td>07:30:00</td>
	<td>Felipe Soares</td>
	<td>Fui para aula, acabou não tendo, vim direto da UFC para o trabalho, cheguei ás 11h</td>
  </tr>
   <tr>
	<td>02/10/2019</td>
	<td>Quarta-feira</td>
	<td>11:00:00</td>
	<td>18:30:00</td>
	<td>07:30:00</td>
	<td>Jessika Ramalho</td>
	<td>Fui para aula, acabou não tendo, vim direto da UFC para o trabalho, cheguei ás 11h</td>
  </tr>
  
</table>
</body>
</html>