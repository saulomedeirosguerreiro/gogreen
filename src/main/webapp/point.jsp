<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%@page import="br.com.g4flex.entity.Point"%>
<%@page import="br.com.g4flex.entity.User"%>
<%@page import="br.com.g4flex.service.PointService"%>
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
	PointService pointService = new PointService();
	User user = (User) session.getAttribute("user");
	String title = user != null ? user.getName() + ", Bata o seu ponto" : "Atenção, Sua sessão expirou !";

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
			Inicial > Bata seu ponto</a>
		<form action="point" method="POST">
			<output class="title"><%=title%></output>
			<select name="day_of_week">
				<option value="">Dia da Semana</option>
				<option value="Segunda-feira">Segunda-feira</option>
				<option value="Terça-feira">Terça-feira</option>
				<option value="Quarta-feira">Quarta-feira</option>
				<option value="Quinta-feira">Quinta-feira</option>
				<option value="Sexta-feira">Sexta-feira</option>
				<option value="Sábado">Sábado</option>
				<option value="Domingo">Domingo</option>
			</select>
			<textarea name=justification rows="4" cols="50"
				placeholder="Justificativa" maxlength="150"></textarea>
			<button class="btn" type="submit">
				<i class="fa fa-paw"></i> Check-in/Check-out
			</button>
		</Form>
		<output>Check-in</output>
		<output>Check-out</output>
		<br />
		<%
			Point pointToday = pointService.findTodayPoint(user);
			if (pointToday != null) {
		%>
		<output><%=pointToday.getCheckInHour() != null ? pointToday.getCheckInHour() : "--:--:--"%></output>
		<output style="margin-left: 5px;"><%=pointToday.getCheckOutHour() != null ? pointToday.getCheckOutHour() : "--:--:--"%></output>
		<%
			} else {
		%>
		<output>--:--:--</output>
		<output style="margin-left: 15px;">--:--:--</output>
		<%
			}
		%>
	</div>
	<article>
		<table id="customers">
			<thead>
				<tr>
					<th>Data</th>
					<th>Dia da Semana</th>
					<th>Horário de Entrada</th>
					<th>Horário de Saída</th>
					<th>Horas Trabalhadas</th>
					<th>Analista</th>
					<th>Justificativa</th>
				</tr>

			</thead>
			<tbody>
				<%
					List<Point> pointList = pointService.listWithPagination(quantity, numberOfPage);

					for (Point point : pointList) {
				%>

				<tr>
					<td><%=point.getDate() == null ? "N/A" : point.getDateFormatted()%></td>
					<td><%=point.getDayOfWeek() == null ? "N/A" : point.getDayOfWeek()%></td>
					<td><%=point.getCheckInHour() == null ? "N/A" : point.getCheckInHour()%></td>
					<td><%=point.getCheckOutHour() == null ? "N/A" : point.getCheckOutHour()%></td>
					<td><%=point.getWorkedHour() == null ? "N/A" : point.getWorkedHour()%></td>
					<td><%=point.getUser() == null ? "N/A" : point.getUser().getName()%></td>
					<td><%=point.getJustification() == null ? "N/A" : point.getJustification()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

		<div class="pagination">
			<%
				int quantityPage = pointService.getQuantityPage(quantity);
				if(quantityPage > 1){
					for (int i = 1; i <= quantityPage; i++) {
			%>
			<a id="page<%=i%>" onclick ="changeSelectedPage()" href="/gogreen/point.jsp?quantity=<%=quantity%>&page=<%=i%>"><%=i%></a>
			<%
					}
				}
			%>
		</div>
	</article>

	<div id="export">
		<a id="pdf" href="export?action=pdf&model=point"> <img
			src="resources/img/File_pdf.png">
		</a> <a id="xls" href="export?action=excel&model=point"> <img
			src="resources/img/File_xls.png">
		</a>
	</div>
	
	<script type="text/javascript">
		function changeVisibilyExports() {
			if (
	<%=pointList.isEmpty()%>
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