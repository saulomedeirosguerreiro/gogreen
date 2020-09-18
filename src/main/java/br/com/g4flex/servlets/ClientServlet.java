package br.com.g4flex.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.Client;
import br.com.g4flex.service.ClientService;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientService clientService;
	private HttpServletResponse response;
	private HttpSession session;

	public ClientServlet() {
		super();
		clientService = new ClientService();

	}

	public String init(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		this.response = response;
		this.session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return request.getParameter("action");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		init(request, response);
		String clientName = request.getParameter("client_name");
		String port = request.getParameter("port");
		String externalIp = request.getParameter("external_ip");
		String internalIp = request.getParameter("internal_ip");
		String databaseIp = request.getParameter("database_ip");
		int qtySubsidiary = Integer.parseInt(request.getParameter("qty_subsidiary"));
		String versionFlexuc = request.getParameter("version_flexuc");
		String versionLinux = request.getParameter("version_linux");
		boolean hasCallback = Boolean.parseBoolean(request.getParameter("has_callback"));
		boolean hasInquiry = Boolean.parseBoolean(request.getParameter("has_inquiry"));
		boolean hasFlexsms = Boolean.parseBoolean(request.getParameter("has_flexsms"));
		boolean hasVpn = Boolean.parseBoolean(request.getParameter("has_vpn"));
		boolean hasScriptDisk = Boolean.parseBoolean(request.getParameter("has_script_disk"));
		boolean hasScriptCallcenter = Boolean.parseBoolean(request.getParameter("has_script_callcenter"));
		boolean hasContractTerminated = Boolean.parseBoolean(request.getParameter("has_contract_terminated"));
		if(!request.getParameter("clientId").isEmpty()) {
			Long clientId = Long.parseLong(request.getParameter("clientId"));
			updateClient(clientId,clientName,  port,  externalIp,  internalIp, databaseIp,  qtySubsidiary,  versionFlexuc,  versionLinux,  hasCallback,
					hasInquiry,  hasFlexsms,  hasVpn,
					hasScriptDisk,  hasScriptCallcenter,  hasContractTerminated);	
			
		}else {
			createNewClient(clientName,  port,  externalIp,  internalIp, databaseIp,  qtySubsidiary,  versionFlexuc,  versionLinux,  hasCallback,
					hasInquiry,  hasFlexsms,  hasVpn,
					hasScriptDisk,  hasScriptCallcenter,  hasContractTerminated);			
		}
		response.sendRedirect("/gogreen/client.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		init(request, response);
		int quantity =  Integer.valueOf(request.getParameter("quantity"));
		int numberOfPage =  Integer.valueOf(request.getParameter("page"));
		String clientName = request.getParameter("clientname");
		//System.out.println(clientName+ ", quantity = " + quantity + ", page = " + numberOfPage);
		ClientService clientService = new ClientService();
		FiltersDTO filters  = new FiltersDTO (clientName);
		List<Client> clientListWithPagination = clientService.findWithPagination(filters,quantity, numberOfPage);
		List<Client> clientList = clientService.find(filters);
		request.setAttribute("clientList", clientListWithPagination);
		request.setAttribute("total", clientList!=null ? clientList.size() : 0);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/client.jsp");
			    dispatcher.forward(request, response);
	}
	

	private void createNewClient(String clientName, String port, String externalIp, String internalIp,String databaseIp, int qtySubsidiary, String versionFlexuc, String versionLinux, boolean hasCallback,
			boolean hasInquiry, boolean hasFlexsms, boolean hasVpn,
			boolean hasScriptDisk, boolean hasScriptCallcenter, boolean hasContractTerminated) {
		clientService.create(new Client(clientName,  port,  externalIp,  internalIp, databaseIp,  qtySubsidiary,  versionFlexuc,  versionLinux,  hasCallback,
				 hasInquiry,  hasFlexsms,  hasVpn,
				 hasScriptDisk,  hasScriptCallcenter,  hasContractTerminated));
	}
	
	private void updateClient(Long id, String clientName, String port, String externalIp, String internalIp,String databaseIp, int qtySubsidiary, String versionFlexuc, String versionLinux, boolean hasCallback,
			boolean hasInquiry, boolean hasFlexsms, boolean hasVpn,
			boolean hasScriptDisk, boolean hasScriptCallcenter, boolean hasContractTerminated) {
		clientService.update(new Client(id,clientName,  port,  externalIp,  internalIp, databaseIp,  qtySubsidiary,  versionFlexuc,  versionLinux,  hasCallback,
				 hasInquiry,  hasFlexsms,  hasVpn,
				 hasScriptDisk,  hasScriptCallcenter,  hasContractTerminated)); 
	}
}
