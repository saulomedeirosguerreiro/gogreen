<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%@page import="br.com.g4flex.entity.PresentialCalled"%>
<%@page import="br.com.g4flex.entity.User"%>
<%@page import="br.com.g4flex.service.PresentialCalledService"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoGreen</title>
<link rel="icon" href="resources/img/icon.png">
<link rel="stylesheet" type="text/css" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link
	href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<jsp:useBean id="presential"
	class="br.com.g4flex.entity.PresentialCalled" />
<jsp:useBean id="service"
	class="br.com.g4flex.service.PresentialCalledService" />
</head>
<%	
User user = (User)session.getAttribute("user"); 
String title = user != null?  "Chamado Presencial" : "Atenção, Sua sessão expirou !"; 
%>
<body>
	<div id="content">
		<a href="/gogreen/home.jsp"><i class="fa fa-home"></i> Tela
			Inicial > Chamado Presencial</a>
		<form action="presential" method="POST">
			<output class="title"><%=title%></output>
			<input name="call_number" type="number"
				placeholder="Número do Chamado" maxlength="100" required /> <input
				name="client_name" type="text" placeholder="Nome do Cliente" maxlength="100"
				required />
			<output>Data da Atividade</output>
			<input name="activity_date" type="date" required />
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>
	<table id="customers">
		<thead>
			<tr>
				<th>Número do Chamado</th>
				<th>Analista</th>
				<th>Cliente</th>
				<th>Data da Atividade</th>

			</tr>
		</thead>
		<tbody>
			<%
				PresentialCalledService presentialCalledService = new PresentialCalledService();
				List<PresentialCalled> presentialCalledList = presentialCalledService.list();

				for (PresentialCalled presentialCalled : presentialCalledList) {
			%>

			<tr>
				<td><%=presentialCalled.getCallNumber() == null ? "N/A" : presentialCalled.getCallNumber()%></td>
				<td><%=presentialCalled.getUser() == null ? "N/A" : presentialCalled.getUser().getName()%></td>
				<td><%=presentialCalled.getClientName() == null ? "N/A" : presentialCalled.getClientName()%></td>
				<td><%=presentialCalled.getActivityDate() == null ? "N/A" : presentialCalled.getActivityDateFormatted()%></td>
			</tr>
			<%
				}
			%>
		</tbody>

	</table>

	<a id="pdf"
		href="export?action=pdf&model=presential">
		<img src="resources/img/File_pdf.png">
	</a>

	<a id="xls" href="export?action=excel&model=presential">
		<img src="resources/img/File_xls.png">
	</a>
	
		<script type="text/javascript">
		function changeVisibilyExports() {
			if(<%=presentialCalledList.isEmpty()%>){
				console.log("lista vazia")
				  document.getElementById('pdf').style.visibility = 'hidden';
				  document.getElementById('xls').style.visibility = 'hidden';
			}else{
				console.log("lista cheia")
				 document.getElementById('pdf').style.visibility = 'visible';
				 document.getElementById('xls').style.visibility = 'visible';
			}
		}
		
		window.onload = function() {
			changeVisibilyExports();
		}
     </script>
</body>
</html>