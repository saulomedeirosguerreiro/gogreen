<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%@page import="br.com.g4flex.entity.User"%>
<%@page import="br.com.g4flex.entity.ControlOnDuty"%>
<%@page import="br.com.g4flex.service.ControlOnDutyService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoGreen</title>
<link rel="icon" href="resources/img/icon.png">
<link rel="stylesheet" type="text/css" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/pagination.css">
<link rel="stylesheet" type="text/css" href="css/page_with_pagination.css">
<link
	href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<%
	User user = (User) session.getAttribute("user");
	String title = user != null ? "Controle do Plantão" : "Atenção, Sua sessão expirou !";

	//pagination

	int quantity = request.getParameter("quantity") != null && !request.getParameter("quantity").isEmpty()
			? Integer.parseInt(request.getParameter("quantity"))
			: 15;
	int numberOfPage = request.getParameter("page") != null && !request.getParameter("page").isEmpty()
			? Integer.parseInt(request.getParameter("page"))
			: 1;
%>
<body>
	<div id="content">
		<a href="/gogreen/home.jsp"><i class="fa fa-home"></i> Tela
			Inicial > Controle do Plantão</a>
		<form action="control" method="POST">
			<output class="title"><%=title%></output>
			<select name="day_of_week" required>
				<option value="">Dia da Semana</option>
				<option value="Segunda-feira">Segunda-feira</option>
				<option value="Terça-feira">Terça-feira</option>
				<option value="Quarta-feira">Quarta-feira</option>
				<option value="Quinta-feira">Quinta-feira</option>
				<option value="Sexta-feira">Sexta-feira</option>
				<option value="Sábado">Sábado</option>
				<option value="Domingo">Domingo</option>
			</select>
			<textarea name="justification" rows="4" cols="50"
				placeholder="Justificativa" maxlength="150"></textarea>
			<output>Data</output>
			<input name="date" type="date" required />
			<output>Hora de Início</output>
			plus <input name="initial_hour" type="time" required />
			<output>Hora de Finalização</output>
			<input name="final_hour" type="time" required /> <input
				name="client_name" type="text" placeholder="Nome do Cliente"
				maxlength="100" required /> <input name="call_number" type="number"
				placeholder="Número do Chamado" maxlength="100" required />
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>

	<article>
		<table id="customers">
			<thead>
				<tr>
					<th>Data</th>
					<th>Dia da Semana</th>
					<th>Número do Chamado</th>
					<th>Hora de Início</th>
					<th>Hora de Finalização</th>
					<th>Horas Trabalhadas</th>
					<th>Analista</th>
					<th>Cliente</th>
					<th>Justificativa</th>
				</tr>


			</thead>
			<tbody>
				<%
					ControlOnDutyService controlOnDutyService = new ControlOnDutyService();
					List<ControlOnDuty> controlOnDutyList = controlOnDutyService.listWithPagination(quantity, numberOfPage);

					for (ControlOnDuty controlOnDuty : controlOnDutyList) {
				%>

				<tr>
					<td><%=controlOnDuty.getDate() == null ? "N/A" : controlOnDuty.getDateFormatted()%></td>
					<td><%=controlOnDuty.getDayOfWeek() == null ? "N/A" : controlOnDuty.getDayOfWeek()%></td>
					<td><%=controlOnDuty.getCallNumber() == null ? "N/A" : controlOnDuty.getCallNumber()%></td>
					<td><%=controlOnDuty.getInitialHour() == null ? "N/A" : controlOnDuty.getInitialHour()%></td>
					<td><%=controlOnDuty.getFinalHour() == null ? "N/A" : controlOnDuty.getFinalHour()%></td>
					<td><%=controlOnDuty.getWorkedHour() == null ? "N/A" : controlOnDuty.getWorkedHour()%></td>
					<td><%=controlOnDuty.getUser() == null ? "N/A" : controlOnDuty.getUser().getName()%></td>
					<td><%=controlOnDuty.getClientName() == null ? "N/A" : controlOnDuty.getClientName()%></td>
					<td><%=controlOnDuty.getJustification() == null ? "N/A" : controlOnDuty.getJustification()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	
		<div class="pagination">
			<%
				int quantityPage = controlOnDutyService.getQuantityPage(quantity);
				if(quantityPage > 1){
					for (int i = 1; i <= quantityPage; i++) {
			%>
					<a id="page<%=i%>" onclick ="changeSelectedPage()" href="/gogreen/control.jsp?quantity=<%=quantity%>&page=<%=i%>"><%=i%></a>
			<%
					}
				}
			%>
		</div>
	</article>


	<div id="export">
		<a id="pdf" href="export?action=pdf&model=control"> <img
			src="resources/img/File_pdf.png">
		</a> <a id="xls" href="export?action=excel&model=control"> <img
			src="resources/img/File_xls.png">
		</a>
	</div>
	
	<script type="text/javascript">
		function changeVisibilyExports() {
			if (
	<%=controlOnDutyList.isEmpty()%>
		) {
				console.log("lista vazia")
				document.getElementById('pdf').style.visibility = 'hidden';
				document.getElementById('xls').style.visibility = 'hidden';
			} else {
				console.log("lista cheia")
				document.getElementById('pdf').style.visibility = 'visible';
				document.getElementById('xls').style.visibility = 'visible';
			}
		}

		function changeSelectedPage() {

			var elems = document.querySelectorAll(".pagination");

			[].forEach.call(elems, function(el) {
			    el.classList.remove("active");
			});
				
			document.getElementById('page' + <%=numberOfPage%>).classList.add("active");
		}

		window.onload = function() {
			changeVisibilyExports();
			changeSelectedPage();
		}
	</script>
</body>
</html>