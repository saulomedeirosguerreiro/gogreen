package br.com.g4flex.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.g4flex.entity.Activity;
import br.com.g4flex.entity.ExtraActivity;
import br.com.g4flex.entity.PresentialCalled;
import br.com.g4flex.entity.User;
import br.com.g4flex.service.ExtraActivityService;
import br.com.g4flex.service.PresentialCalledService;
import br.com.g4flex.utils.DateUtil;

@WebServlet("/extra")
public class ExtraActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExtraActivityService extraActivityService;
	private HttpServletResponse response;
	private HttpSession session;

	public ExtraActivityServlet() {
		super();
		extraActivityService = new ExtraActivityService();

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
		Activity activity = new Activity(Long.parseLong(request.getParameter("activity")));
		String description = request.getParameter("description");
		String clientName = request.getParameter("client_name");
		String protocolNumber = request.getParameter("protocol_number");
		protocolNumber = protocolNumber.isEmpty() ? null : protocolNumber;  
		Date activityDate = DateUtil.stringToDate(request.getParameter("activity_date"), DateUtil.PATTERN_DATE);
		Date initialHour = DateUtil.stringToDate(request.getParameter("initial_hour"), DateUtil.PATTERN_HOUR);
		Date finalHour = DateUtil.stringToDate(request.getParameter("final_hour"), DateUtil.PATTERN_HOUR);
		createNewExtraActivity(description, clientName, activityDate, initialHour, finalHour, protocolNumber, activity);
		response.sendRedirect("/gogreen/extra.jsp");
	}

	private void createNewExtraActivity(String description, String clientName, Date activityDate, Date initialHour,
			Date finalHour, String protocolNumber, Activity activity) {
		User user = (User) session.getAttribute("user");
		extraActivityService.create(new ExtraActivity(description, clientName, activityDate, initialHour, finalHour,
				protocolNumber, user, activity));
	}
}
