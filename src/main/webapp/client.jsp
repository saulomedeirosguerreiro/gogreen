<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%@page import="br.com.g4flex.entity.User"%>
<%@page import="br.com.g4flex.entity.Client"%>
<%@page import="br.com.g4flex.service.ClientService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
</head>
<%
	User user = (User) session.getAttribute("user");
	String title = user != null ? "Acesso dos Clientes" : "Atenção, Sua sessão expirou !";

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
			
	List<Client> clientList = (List<Client>) request.getAttribute("clientList");
%>
<body>
	<div id="content">
		<a href="/gogreen/home.jsp"><i class="fa fa-home"></i> Tela
			Inicial > Acesso dos Clientes</a>
		<form action="client" method="POST">
			<output class="title"><%=title%></output>
			 <input name="client_name" type="text" placeholder="Nome do Cliente" maxlength="100" required/>
			 <input name="port" type="text" placeholder="Porta" maxlength="45"/>
			 <input name="external_ip" type="text" placeholder="IP Externo" maxlength="60"/>
			 <input name="internal_ip" type="text" placeholder="IP Interno" maxlength="60"/>
			 <input name="database_ip" type="text" placeholder="IP do Banco" maxlength="60"/>
			  <input name="version_flexuc" type="text" placeholder="Versão Flexuc"  maxlength="60"/>
			  <input name="version_linux" type="text" placeholder="Versão Linux"  maxlength="60"/>
			 <output>Quantidade de Filiais</output>
			  <input name="qty_subsidiary" type="number" value="0" required/>
			  <output>Têm Callback?</output>
			  <select name="has_callback" >
				<option value="false">Não</option>
				<option value="true">Sim</option>
			  </select>
			  <output>Têm Inquiry?</output>
			  <select name="has_inquiry" >
				<option value="false">Não</option>
				<option value="true">Sim</option>
			  </select>
			  <output>Têm FlexSMS?</output>
			  <select name="has_flexsms" >
				<option value="false">Não</option>
				<option value="true">Sim</option>
			  </select>
			  <output>Têm VPN?</output>
			  <select name="has_vpn" >
				<option value="false">Não</option>
				<option value="true">Sim</option>
			  </select>
			   <output>Têm Script Checa Disco?</output>
			   <select name="has_script_disk" >
				<option value="false">Não</option>
				<option value="true">Sim</option>
			  </select>
			  <output>Têm Script Checa Callcenter?</output>
			    <select name="has_script_callcenter" >
				<option value="false">Não</option>
				<option value="true">Sim</option>
			  </select>
			  <output>Contrato foi Encerrado?</output>
			     <select name="has_contract_terminated" >
				<option value="false">Não</option>
				<option value="true">Sim</option>
			  </select>
			  <input type="hidden" id="clientId" name="clientId" value="">
			<button id="submitFormButton" class="btn" type="submit">Criar</button>
		</Form>
	</div>
		<div>
		<section class="filters">
			<h1>Filtros</h1>
			<form action="client" method="GET">
				<input
					name="clientname" type="text" placeholder="Cliente"
					maxlength="100" />
				<input type="hidden" name="quantity" value="<%=textQuantity%>">
    			<input type="hidden" name="page" value="<%=textPage%>">
				<button class="btn-search" type="submit">Buscar</button>
			</form> 
		</section>
   <%
		if(clientList!=null && !clientList.isEmpty()){
   %>

	<article >
		<table id="customers">
			<thead>
				<tr>
					<th>Op</th>
					<th>Cliente</th>
					<th>Porta</th>
					<th>IP Externo</th>
					<th>IP Interno</th>
					<th>IP Banco</th>
					<th>Versão Flexuc</th>
					<th>Versão Linux</th>
					<th>Qtd Filiais</th>
					<th>Callback</th>
					<th>Inquiry</th>
					<th>FlexSMS</th>
					<th>VPN</th>
					<th>Script Disco</th>
					<th>Script Callcenter</th>
				    <th>Contrato Encerrado</th>
				</tr>


			</thead>
			<tbody>
				<%
					for (Client client : clientList) {
				%>

				<tr id=<%=client.getId()%>>
					<td><a onclick=editClient(<%=client.getId()%>)><i class="fa fa-edit"></i></a></td>
					<td><%=client.getClientName() == null ? "N/A" : client.getClientName()%></td>
					<td><%=client.getPort() == null ? "N/A" : client.getPort()%></td>
					<td><%=client.getExternalIp() == null ? "N/A" : client.getExternalIp()%></td>
					<td><%=client.getInternalIp() == null ? "N/A" : client.getInternalIp()%></td>
					<td><%=client.getDatabaseIp() == null ? "N/A" : client.getDatabaseIp()%></td>
					<td><%=client.getVersionFlexuc() == null ? "N/A" : client.getVersionFlexuc()%></td>
					<td><%=client.getVersionLinux() == null ? "N/A" : client.getVersionLinux()%></td>
					<td><%=client.getQtySubsidiary()%></td>
					<td><%=client.isHasCallback() == false ? "Não" : "Sim"%></td>
					<td><%=client.isHasInquiry() == false ? "Não" : "Sim"%></td>
					<td><%=client.isHasFlexsms() == false ? "Não" : "Sim"%></td>
					<td><%=client.isHasVpn() == false ? "Não" : "Sim"%></td>
					<td><%=client.isHasScriptDisk() == false ? "Não" : "Sim"%></td>
					<td><%=client.isHasScriptCallcenter() == false ? "Não" : "Sim"%></td>
					<td><%=client.isHasContractTerminated() == false ? "Não" : "Sim"%></td>
					
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	
		<div class="pagination">
				<%
				ClientService clientService = new ClientService();
				int quantityPage = clientService.getQuantityPage(quantityPerPage, total);
				if(quantityPage > 1){
					for (int i = 1; i <= quantityPage; i++) {
			%>
			<a id="page<%=i%>" onclick ="changeSelectedPage()" href="/gogreen/client?clientname=<%=request.getParameter("clientname")%>&quantity=<%=quantityPerPage%>&page=<%=i%>"><%=i%></a>
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
		<a id="pdf" href="export?action=pdf&model=client&clientname=<%=request.getParameter("clientname")%>"> <img
			src="resources/img/File_pdf.png">
		</a> <a id="xls" href="export?action=excel&model=client&clientname=<%=request.getParameter("clientname")%>"> <img
			src="resources/img/File_xls.png">
		</a>
	</div>
	
	<script type="text/javascript">
		function changeVisibilyExports() {
			if (
	<%=clientList==null || clientList.isEmpty()%>
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
		
		function editClient(id) {
			var cells = document.getElementById(id).getElementsByTagName('td');
			
			
			let inputElementClientName = document.querySelector('input[name="client_name"]');
			let inputElementPort = document.querySelector('input[name="port"]');
			let inputElementExternalIp = document.querySelector('input[name="external_ip"]');
			let inputElementInternalIp = document.querySelector('input[name="internal_ip"]');
			let inputElementDatabaseIp = document.querySelector('input[name="database_ip"]');
			let inputElementVersionFlexuc = document.querySelector('input[name="version_flexuc"]');
			let inputElementVersionLinux = document.querySelector('input[name="version_linux"]');
			let inputElementQtdSubsidiary = document.querySelector('input[name="qty_subsidiary"]');
			let selectElementHasCallback = document.querySelector('select[name="has_callback"]');
			let selectElementHasInquiry = document.querySelector('select[name="has_inquiry"]');
			let selectElementHasFlexSms = document.querySelector('select[name="has_flexsms"]');
			let selectElementHasVpn = document.querySelector('select[name="has_vpn"]');
			let selectElementHasScriptDisk = document.querySelector('select[name="has_script_disk"]');
			let selectElementHasScriptCallcenter = document.querySelector('select[name="has_script_callcenter"]');
			let selectElementHasContractTerminated = document.querySelector('select[name="has_contract_terminated"]');
			let inputElementClientId = document.querySelector('input[name="clientId"]');
			
			inputElementClientName.value = cells[1].innerHTML; 
			inputElementPort.value = cells[2].innerHTML;
			inputElementExternalIp.value = cells[3].innerHTML;
			inputElementInternalIp.value = cells[4].innerHTML;
			inputElementDatabaseIp.value = cells[5].innerHTML;
			inputElementVersionFlexuc.value = cells[6].innerHTML;
			inputElementVersionLinux.value = cells[7].innerHTML;
			inputElementQtdSubsidiary.value = cells[8].innerHTML;
		
			selectElementHasCallback.value = cells[9].innerHTML == 'Sim' ? true : false;
			selectElementHasInquiry.value = cells[10].innerHTML == 'Sim' ? true : false;
			selectElementHasFlexSms.value = cells[11].innerHTML == 'Sim' ? true : false;
			selectElementHasVpn.value = cells[12].innerHTML == 'Sim' ? true : false;
			selectElementHasScriptDisk.value = cells[13].innerHTML == 'Sim' ?  true : false;
			selectElementHasScriptCallcenter.value = cells[14].innerHTML == 'Sim' ? true : false;
			selectElementHasContractTerminated.value = cells[15].innerHTML == 'Sim' ? true : false;
			
			inputElementClientId.value = id;
			let submitButtonElement = document.querySelector('#submitFormButton');
			console.log(submitButtonElement);
			submitButtonElement.innerHTML = 'Editar';
			
		}

		
	</script>
</body>
</html>