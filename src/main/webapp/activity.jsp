<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%@page import="br.com.g4flex.entity.Activity"%>
<%@page import="br.com.g4flex.service.ActivityService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
</head>
<body>
	<div id="content">
		<a href="/gogreen/home.jsp"><i class="fa fa-home"></i> Tela
			Inicial > Nova Atividade</a>
		<form action="activity" method="POST">
			<output class="title">Nova Atividade</output>
			<input name="name" type="text" placeholder="Nome da Atividade" maxlength="100"
				required /> <input name="value" type="number"
				placeholder="Valor da Atividade" min="0" max="20000" required />
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>
	<table id="customers">
		<thead>
			<tr>
				<th>Atividade</th>
				<th>Valor</th>

			</tr>
		</thead>
		<tbody>
			<%
				ActivityService activityService = new ActivityService();
				List<Activity> activityList = activityService.list();

				for (Activity activity : activityList) {
			%>

			<tr>
				<td><%=activity.getName() == null ? "N/A" : activity.getName()%></td>
				<td><%=activity.getValue() == null ? "N/A" : activity.getValue()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<a id="pdf"
		href="export?action=pdf&model=activity">
		<img src="resources/img/File_pdf.png">
	</a>

	<a id="xls" href="export?action=excel&model=activity">
		<img src="resources/img/File_xls.png">
	</a>
</body>
</html>