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
<link rel="stylesheet" type="text/css" href="css/pagination.css">
<link rel="stylesheet" type="text/css" href="css/page_with_pagination.css">
<link rel="stylesheet" type="text/css" href="css/filter.css">
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
	User user = (User) session.getAttribute("user");
	String title = user != null ? "Chamado Presencial" : "Atenção, Sua sessão expirou !";

	//pagination

	int quantityPerPage = 30;
	String textQuantity = String.valueOf(quantityPerPage);
	
	int total = 0;
	if(request.getAttribute("total") !=null)
		total = (Integer)request.getAttribute("total");
		
	int numberOfPage = request.getParameter("page") != null && !request.getParameter("page").isEmpty()
			? Integer.parseInt(request.getParameter("page"))
			: 1;
			
	String textPage = String.valueOf(numberOfPage);	
	
			
	List<PresentialCalled> presentialCalledList = (List<PresentialCalled>) request.getAttribute("presentialCalledList");
%>
<body>
	<div id="content">
		<a href="/gogreen/home.jsp"><i class="fa fa-home"></i> Tela
			Inicial > Chamado Presencial</a>
		<form action="presential" method="POST">
			<output class="title"><%=title%></output>
			<input name="call_number" type="number"
				placeholder="Número do Chamado" maxlength="100" required /> <input
				name="client_name" type="text" placeholder="Nome do Cliente"
				maxlength="100" required />
			<output>Data da Atividade</output>
			<input name="activity_date" type="date" required />
			<button class="btn" type="submit">Criar</button>
		</Form>
	</div>
	<div>
		<section class="filters">
			<h1>Filtros</h1>
			<form action="presential" method="GET">
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
		if(presentialCalledList!=null && !presentialCalledList.isEmpty()){
   %>
	<article>
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
				
					for (PresentialCalled presentialCalled : presentialCalledList) {
				%>

				<tr>
					<td><%=presentialCalled.getCallNumber() == null ? "N/A" : presentialCalled.getCallNumber()%></td>
					<td><%=presentialCalled.getUser() == null ? "N/A" : presentialCalled.getUser().getName()%></td>
					<td><%=presentialCalled.getClientName() == null ? "N/A" : presentialCalled.getClientName()%></td>
					<td><%=presentialCalled.getActivityDate() == null
						? "N/A"
						: presentialCalled.getActivityDateFormatted()%></td>
				</tr>
				<%
					}
				%>
			</tbody>

		</table>

		<div class="pagination">
			<%
			PresentialCalledService presentialCalledService = new PresentialCalledService();
				int quantityPage = presentialCalledService.getQuantityPage(quantityPerPage, total);
				if(quantityPage > 1){
					for (int i = 1; i <= quantityPage; i++) {
			%>
			<a id="page<%=i%>" onclick ="changeSelectedPage()" href="/gogreen/presential?username=<%=request.getParameter("username")%>&initialdate=<%=request.getParameter("initialdate")%>&finaldate=<%=request.getParameter("finaldate")%>&quantity=<%=quantityPerPage%>&page=<%=i%>"><%=i%></a>
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
		<a id="pdf" href="export?action=pdf&model=presential&username=<%=request.getParameter("username")%>&initialdate=<%=request.getParameter("initialdate")%>&finaldate=<%=request.getParameter("finaldate")%>"> <img
			src="resources/img/File_pdf.png">
		</a> <a id="xls" href="export?action=excel&model=presential&username=<%=request.getParameter("username")%>&initialdate=<%=request.getParameter("initialdate")%>&finaldate=<%=request.getParameter("finaldate")%>"> <img
			src="resources/img/File_xls.png">
		</a>

	</div>

	<script type="text/javascript">
		function changeVisibilyExports() {
			if (
	<%=presentialCalledList==null || presentialCalledList.isEmpty()%>
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