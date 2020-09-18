package br.com.g4flex.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.g4flex.client.FlexExportClient;
import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.service.ActivityService;
import br.com.g4flex.service.ClientService;
import br.com.g4flex.service.ControlOnDutyService;
import br.com.g4flex.service.ExtraActivityService;
import br.com.g4flex.service.PointService;
import br.com.g4flex.service.PresentialCalledService;
import br.com.g4flex.utils.DateUtil;
import br.com.g4flex.utils.JsonUtil;

@WebServlet("/export")
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private HttpSession session;
	private ActivityService activityService;
	private ExtraActivityService extraActivityService;
	private PointService pointService;
	private PresentialCalledService presentialCalledService;
	private ControlOnDutyService controlOnDutyService;
	private ClientService clientService;

	public ExportServlet() {
		super();
		activityService = new ActivityService();
		extraActivityService = new ExtraActivityService();
		pointService = new PointService();
		presentialCalledService = new PresentialCalledService();
		controlOnDutyService = new ControlOnDutyService();
		clientService = new ClientService();
	}

	public String init(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		this.response = response;
		this.request = request;
		this.session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return request.getParameter("action");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = init(request, response);
		String model = request.getParameter("model");
		
		FiltersDTO filters = null;
		FiltersDTO filters2 = null;
		if(request.getParameter("username")!=null && request.getParameter("initialdate")!=null && request.getParameter("finaldate")!=null ) {
			String userName = request.getParameter("username");
			Date initialDate = DateUtil.stringToDate(request.getParameter("initialdate"), DateUtil.PATTERN_DATE);
			Date finalDate = DateUtil.stringToDate(request.getParameter("finaldate"), DateUtil.PATTERN_DATE);
			filters  = new FiltersDTO (userName, initialDate,finalDate);
		}else if(request.getParameter("clientname")!=null) {			
			String clientName = request.getParameter("clientname");
			filters2  = new FiltersDTO (clientName);
		}

		String json = "";
		if (model.equals("presential")) {
			String[] header = { "Número do Chamado", "Analista", "Cliente", "Data da Atividade" };
			json = JsonUtil.getJson(header, presentialCalledService.getArrayOfArrayObject(filters), "Chamado Presencial");
		} else if (model.equals("extra")) {
			String[] header = { "Número do Protocolo", "Atividade", "Analista", "Descrição", "Cliente",
					"Data da Atividade", "Hora de Início", "Hora de Finalização", "Valor" };
			json = JsonUtil.getJson(header, extraActivityService.getArrayOfArrayObject(filters), "Atividade Extra");
		} else if (model.equals("point")) {
			String[] header = { "Data", "Dia da Semana", "Horário de Entrada", "Horário de Saída", "Horas Trabalhadas",
					"Analista", "Justificativa" };
			json = JsonUtil.getJson(header, pointService.getArrayOfArrayObject(filters), "Ponto");
		} else if (model.equals("activity")) {
			String[] header = { "Atividade", "Valor" };
			json = JsonUtil.getJson(header, activityService.getArrayOfArrayObject(), "Atividade");
		} else if (model.equals("control")) {
			String[] header = { "Data", "Dia da Semana", "Número do Chamado", "Hora de Início", "Hora de Finalização",
					"Horas Trabalhadas", "Analista", "Cliente", "Justificativa" };
			json = JsonUtil.getJson(header, controlOnDutyService.getArrayOfArrayObject(filters), "Controle do Plantão");
		}else if (model.equals("client")) {
			String[] header = { "Cliente", "Porta", "IP Externo", "IP Interno", "IP Banco",
					"Qtd Filiais", "Versão Flexuc", "Versão Linux", "Callback", "Inquiry", "FlexSMS", "VPN"
					, "Script Checa Disco", "Script Checa Callcenter", "Contrato Encerrado"};
			json = JsonUtil.getJson(header, clientService.getArrayOfArrayObject(filters2), "Acesso dos Clientes");
		}

		try {
			if (action.equals("pdf"))
				FlexExportClient.pdf(response, json, model + ".pdf");
			else
				FlexExportClient.excel(response, json, model + ".xlsx");
		} catch (Exception e) {
			e.printStackTrace();
			if(!response.isCommitted())
				response.sendRedirect("/gogreen/" + model + ".jsp");
		}

	}

}
