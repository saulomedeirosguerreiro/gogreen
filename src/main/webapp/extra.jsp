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
<title>GoGreen</title>
<link rel="icon" href="resources/img/icon.png">
<link rel="stylesheet" type="text/css" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<link rel="stylesheet" type="text/css" href="css/pagination.css">
<link rel="stylesheet" type="text/css" href="css/page_with_pagination.css">
<link rel="stylesheet" type="text/css" href="css/filter.css">
<link
	href="https://fonts.googleapis.com/css?family=Fredoka+One&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">
	function changeVisibilyRepaymentInput() {
		let activitySelectElement = document.getElementById('activity');
		let activitySelectedValue = ""
				+ activitySelectElement.options[activitySelectElement.selectedIndex].innerHTML;
		if (activitySelectedValue.toUpperCase() === 'REEMBOLSO'
				|| activitySelectedValue.toUpperCase() === 'RESSARCIMENTO') {
			document.getElementById('repayment').style.visibility = 'visible';
		} else {
			document.getElementById('repayment').style.visibility = 'hidden';
		}
	}

	window.onload = function() {
		changeVisibilyRepaymentInput();
	}
</script>
</head>
<%
	User user = (User) session.getAttribute("user");
	String title = user != null ? "Atividade Extra" : "Atenção, Sua sessão expirou !";

	//pagination

	int quantityPerPage = 10;
	String textQuantity = String.valueOf(quantityPerPage);
	
	int total = 0;
	if(request.getAttribute("total") !=null)
		total = (Integer)request.getAttribute("total");
		
	int numberOfPage = request.getParameter("page") != null && !request.getParameter("page").isEmpty()
			? Integer.parseInt(request.getParameter("page"))
			: 1;
			
	String textPage = String.valueOf(numberOfPage);	
	
			
	List<ExtraActivity> extraActivityList = (List<ExtraActivity>) request.getAttribute("extraActivityList");
%>


<body>
	<div id="content">
		<a href="/gogreen/home.jsp"><i class="fa fa-home"></i> Tela
			Inicial > Atividade Extra</a>
		<form action="extra" method="POST">
			<output class="title"><%=title%></output>
			<select name="activity" id="activity"
				onclick="changeVisibilyRepaymentInput()" required>
				<%
					ActivityService activityService = new ActivityService();
					List<Activity> activityList = activityService.list();

					for (Activity activity : activityList) {
				%>
				<option value="<%=activity.getId()%>"><%=activity.getName()%></option>
				<%
					}
				%>

			</select> <input name="repayment" id="repayment" type="number"
				placeholder="Valor do Ressarcimento" min="0" max="20000" step="0.01" />
			<textarea name="description" rows="4" cols="50"
				placeholder="Descrição" maxlength="150" required></textarea>
			<output>Data da Atividade</output>
			<input name="activity_date" type="date" required />
			<output>Hora de Início</output>
			plus <input name="initial_hour" type="time" required />
			<output>Hora de Finalização</output>
			<input name="final_hour" type="time" required /> <input
				name="client_name" type="text" placeholder="Nome do Cliente"
				maxlength="100" required /> <input name="protocol_number"
				type="number" placeholder="Número do Protocolo" maxlength="100" />
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>
	<div>
		<section class="filters">
			<h1>Filtros</h1>
			<form action="extra" method="GET">
				<input
					name="username" type="text" placeholder="Nome do Analista"
					maxlength="100" />
				<output>Dt Inicial</output>
				<input name="initialdate" type="date" value=<%=request.getParameter("initialdate")%>  required/>
				<div>
					<output>Dt Final</output>
					<input name="finaldate" type="date" value=<%=request.getParameter("finaldate")%> required/>
				</div>
				<input type="hidden" name="quantity" value="<%=textQuantity%>">
    			<input type="hidden" name="page" value="<%=textPage%>">
				<button class="btn-search" type="submit">Buscar</button>
			</form> 
		</section>
   <%
		if(extraActivityList!=null && !extraActivityList.isEmpty()){
   %>
	<article>
		<table id="customers">
			<thead>
				<tr>
					<th>Número do Protocolo</th>
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
					<td><%=extraActivity.getActivity() == null
						? "N/A"
						: extraActivity.getRepayment() == null
								? extraActivity.getActivity().getValue()
								: extraActivity.getRepayment()%></td>
				</tr>
				<%
						}
				%>
			</tbody>
		</table>
		<div class="pagination">
			<%
				ExtraActivityService extraActivityService = new ExtraActivityService();
				int quantityPage = extraActivityService.getQuantityPage(quantityPerPage, total);
				if(quantityPage > 1){
					for (int i = 1; i <= quantityPage; i++) {
			%>
			<a id="page<%=i%>" onclick ="changeSelectedPage()" href="/gogreen/extra?username=<%=request.getParameter("username")%>&initialdate=<%=request.getParameter("initialdate")%>&finaldate=<%=request.getParameter("finaldate")%>&quantity=<%=quantityPerPage%>&page=<%=i%>"><%=i%></a>
			<%
					}
				}
			%>
		</div>
	</article>
	<%
		}else {
	%>
			<p class="message">
				Nenhum resultado encontrado
			</p>
	<% 
		}
	%>	
	</div>
	<div id="export">
		<a id="pdf" href="export?action=pdf&model=extra&username=<%=request.getParameter("username")%>&initialdate=<%=request.getParameter("initialdate")%>&finaldate=<%=request.getParameter("finaldate")%>"> <img
			src="resources/img/File_pdf.png">
		</a> <a id="xls" href="export?action=excel&model=extra&username=<%=request.getParameter("username")%>&initialdate=<%=request.getParameter("initialdate")%>&finaldate=<%=request.getParameter("finaldate")%>"> <img
			src="resources/img/File_xls.png">
		</a>
	</div>
	<script type="text/javascript">
		function changeVisibilyExports() {
			if (
		<%=extraActivityList==null || extraActivityList.isEmpty()%>
		) {
				document.getElementById('pdf').style.visibility = 'hidden';
				document.getElementById('xls').style.visibility = 'hidden';
			} else {
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