package br.com.g4flex.servlets;

import java.io.IOException;
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

	public String init(HttpServletRequest request, HttpServletResponse response) {
		this.response = response;
		this.session = request.getSession();
		return request.getParameter("action");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Activity activity = new Activity(Long.parseLong(request.getParameter("activity")));
		String description = request.getParameter("description");
		String clientName = request.getParameter("client_name");
		String protocolNumber = request.getParameter("protocol_number");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");
		Date activityDate = null;
		try {
			activityDate = sdf.parse(request.getParameter("activity_date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date initialHour = null;
		System.out.println(request.getParameter("initial_hour"));
		try {
			initialHour = sdfHour.parse(request.getParameter("initial_hour"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date finalHour = null;
		try {
			finalHour = sdfHour.parse(request.getParameter("final_hour"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		init(request, response);

		createNewExtraActivity( description,  clientName,  activityDate,  initialHour,  finalHour,protocolNumber,  activity);
		response.sendRedirect("/gogreen/extra.jsp");
	}

	private void createNewExtraActivity(String description, String clientName, Date activityDate, Date initialHour, Date finalHour,
			String protocolNumber, Activity activity) {
		User user = (User) session.getAttribute("user");
		extraActivityService.create(new ExtraActivity( description,  clientName,  activityDate,  initialHour,  finalHour,
				 protocolNumber,  user,  activity));
	}
}
