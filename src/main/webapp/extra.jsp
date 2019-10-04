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
	<a href="/gogreen/home.jsp"><i class="fa fa-home"></i>  Tela Inicial > Atividade Extra</a>
		<form action="extra" method="POST">
		<output class="title">Atividade Extra</output>
			<select name="activity"  required>
				<option value="7">Plantão Semanal</option>
				<option value="2">Atvidade Extra</option>
				<option value="3">Ressarcimento</option>
				<option value="4">Plantão</option>
				<option value="5">Ativação</option>
			</select>
			<textarea name="description" rows="4" cols="50" placeholder="Descrição" required></textarea>
			<output>Data da Atividade</output>
			<input name="activity_date" type="date" required/>
			<output>Hora de Início</output>plus
			<input name="initial_hour" type="time" required/>
			<output>Hora de Finalização</output>
			<input name="final_hour" type="time" required/>
			<input name="client_name" type="text" placeholder="Nome do Cliente" required />
			<input name="protocol_number" type="number" placeholder="Número do Protocolo" />
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>
		<table id="customers">
  <tr>
    <th>Número do Protocol</th>
    <th>Atividade</th>
    <th>Analista</th>
    <th>Descrição</th>
    <th>Cliente</th>
    <th>Data da Atividade</th>
    <th>Hora de Início</th>
    <th>Hora de Finalização</th>
    <th>Valor</th>
    

  </tr>
  <tr>
	<td>042836</td>
	<td>Ativação</td>
	<td>Douglas Linhares</td>
	<td>Ativação e acompanhamento de Portabilidade com Operadora.</td>
	<td>Instituto Volta a Vida</td>
	<td>01/10/2019</td>
	<td>16:00:00</td>
	<td>20:00:00</td>
	<td>150</td>    
  </tr>
    <tr>
	<td>042836</td>
	<td>Ativação</td>
	<td>Leandro Sobral </td>
	<td>Ativação e acompanhamento de Portabilidade com Operadora.</td>
	<td>G4flex</td>
	<td>01/10/2019</td>
	<td>16:00:00</td>
	<td>20:00:00</td>
	<td>150</td>    
  </tr>
    <tr>
	<td>042836</td>
	<td>Ativação</td>
	<td>Cristiano Sobrinho</td>
	<td>Ativação e acompanhamento de Portabilidade com Operadora.</td>
	<td>M. Dias Branco</td>
	<td>01/10/2019</td>
	<td>16:00:00</td>
	<td>20:00:00</td>
	<td>150</td>    
  </tr>
    <tr>
	<td>042836</td>
	<td>Ativação</td>
	<td>Felipe Soares </td>
	<td>Ativação e acompanhamento de Portabilidade com Operadora.</td>
	<td>Nacional Gás</td>
	<td>01/10/2019</td>
	<td>16:00:00</td>
	<td>20:00:00</td>
	<td>150</td>    
  </tr>
    <tr>
	<td>042836</td>
	<td>Ativação</td>
	<td>Leandro Sobral </td>
	<td>Ativação e acompanhamento de Portabilidade com Operadora.</td>
	<td>Pague Menos</td>
	<td>01/10/2019</td>
	<td>16:00:00</td>
	<td>20:00:00</td>
	<td>150</td>    
  </tr>

  
</table>
</body>
</html>