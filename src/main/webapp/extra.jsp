<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%@page import="br.com.g4flex.entity.ExtraActivity"%>
<%@page import="br.com.g4flex.entity.User"%>
<%@page import="br.com.g4flex.entity.Activity"%>
<%@page import="br.com.g4flex.service.ExtraActivityService"%>
<%@page import="br.com.g4flex.service.ActivityService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link
	href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<%	
User user = (User)session.getAttribute("user"); 
String title = user != null?  "Atividade Extra" : "Atenção, Sua sessão expirou !"; 
%>
<body>
	<div id="content">
		<a href="/gogreen/home.jsp"><i class="fa fa-home"></i> Tela
			Inicial > Atividade Extra</a>
		<form action="extra" method="POST">
			<output class="title"><%=title%></output>
			<select name="activity" required>
				<%
					ActivityService activityService = new ActivityService();
					List<Activity> activityList = activityService.list();

					for (Activity activity : activityList) {
				%>
				<option value="<%=activity.getId()%>"><%=activity.getName()%></option>
				<%
					}
				%>

			</select> 
			<textarea name="description" rows="4" cols="50"
				placeholder="Descrição" required></textarea>
			<output>Data da Atividade</output>
			<input name="activity_date" type="date" required />
			<output>Hora de Início</output>
			plus <input name="initial_hour" type="time" required />
			<output>Hora de Finalização</output>
			<input name="final_hour" type="time" required /> <input
				name="client_name" type="text" placeholder="Nome do Cliente"
				required /> <input name="protocol_number" type="number"
				placeholder="Número do Protocolo" />
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>
	<table id="customers">
		<thead>
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


		</thead>
		<tbody>
			<%
				ExtraActivityService extraActivityService = new ExtraActivityService();
				List<ExtraActivity> extraActivityList = extraActivityService.list();

				for (ExtraActivity extraActivity : extraActivityList) {
			%>

			<tr>
				<td><%=extraActivity.getProtocolNumber() == null ? "N/A" : extraActivity.getProtocolNumber()%></td>
				<td><%=extraActivity.getActivity() == null ? "N/A" : extraActivity.getActivity().getName()%></td>
				<td><%=extraActivity.getUser() == null ? "N/A" : extraActivity.getUser().getName()%></td>
				<td><%=extraActivity.getDescription() == null ? "N/A" : extraActivity.getDescription()%></td>
				<td><%=extraActivity.getClientName() == null ? "N/A" : extraActivity.getClientName()%></td>
				<td><%=extraActivity.getActivityDate() == null ? "N/A" : extraActivity.getActivityDateFormatted()%></td>
				<td><%=extraActivity.getInitialHour() == null ? "N/A" : extraActivity.getInitialHour()%></td>
				<td><%=extraActivity.getFinalHour() == null ? "N/A" : extraActivity.getFinalHour()%></td>
				<td><%=extraActivity.getActivity() == null ? "N/A" : extraActivity.getActivity().getValue()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

	<a id="pdf"
		href="export?action=pdf&model=extra">
		<img src="resources/img/File_pdf.png">
	</a>

	<a id="xls" href="export?action=excel&model=extra">
		<img src="resources/img/File_xls.png">
	</a>
</body>
</html>