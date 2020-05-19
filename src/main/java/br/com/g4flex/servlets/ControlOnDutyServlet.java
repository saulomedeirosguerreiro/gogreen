package br.com.g4flex.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.g4flex.dto.FiltersDTO;
import br.com.g4flex.entity.ControlOnDuty;
import br.com.g4flex.entity.User;
import br.com.g4flex.service.ControlOnDutyService;
import br.com.g4flex.utils.DateUtil;

@WebServlet("/control")
public class ControlOnDutyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControlOnDutyService controlOnDutyService;
	private HttpServletResponse response;
	private HttpSession session;

	public ControlOnDutyServlet() {
		super();
		controlOnDutyService = new ControlOnDutyService();

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
		String callNumber = request.getParameter("call_number");
		String justification = request.getParameter("justification");
		String clientName = request.getParameter("client_name");
		String dayOfWeek = request.getParameter("day_of_week");
		Date date = DateUtil.stringToDate(request.getParameter("date"), DateUtil.PATTERN_DATE);
		Date initialHour = DateUtil.stringToDate(request.getParameter("initial_hour"), DateUtil.PATTERN_HOUR);
		Date finalHour = DateUtil.stringToDate(request.getParameter("final_hour"), DateUtil.PATTERN_HOUR);
		createNewControlOnDuty(callNumber,  justification,  clientName,  dayOfWeek,  date,
				 initialHour,  finalHour);
		response.sendRedirect("/gogreen/control.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		init(request, response);
		int quantity =  Integer.valueOf(request.getParameter("quantity"));
		int numberOfPage =  Integer.valueOf(request.getParameter("page"));
		String userName = request.getParameter("username");
		Date initialDate = DateUtil.stringToDate(request.getParameter("initialdate"), DateUtil.PATTERN_DATE);
		Date finalDate = DateUtil.stringToDate(request.getParameter("finaldate"), DateUtil.PATTERN_DATE);
		//System.out.println(initialDate + ", " + finalDate + ", " + userName + ", quantity = " + quantity + ", page = " + numberOfPage);
		ControlOnDutyService controlOnDutyService = new ControlOnDutyService();
		FiltersDTO filters  = new FiltersDTO (userName, initialDate,finalDate);
		List<ControlOnDuty> controlOnDutyListWithPagination = controlOnDutyService.findWithPagination(filters,quantity, numberOfPage);
		List<ControlOnDuty> controlOnDutyList = controlOnDutyService.find(filters);
		request.setAttribute("controlOnDutyList", controlOnDutyListWithPagination);
		request.setAttribute("total", controlOnDutyList!=null ? controlOnDutyList.size() : 0);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/control.jsp");
			    dispatcher.forward(request, response);
	}

	private void createNewControlOnDuty(String callNumber, String justification, String clientName, String dayOfWeek, Date date,
			Date initialHour, Date finalHour) {
		User user = (User) session.getAttribute("user");
		controlOnDutyService.create(new ControlOnDuty(callNumber,  justification,  clientName,  dayOfWeek,  date,
				 initialHour,  finalHour,  user));
	}
}
