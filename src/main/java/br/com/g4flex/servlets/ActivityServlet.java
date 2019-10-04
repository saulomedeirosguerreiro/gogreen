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
import br.com.g4flex.entity.User;
import br.com.g4flex.service.ActivityService;
import br.com.g4flex.service.ExtraActivityService;

@WebServlet("/activity")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActivityService activityService;
	private HttpServletResponse response;
	private HttpSession session;

	public ActivityServlet() {
		super();
		activityService = new ActivityService();

	}

	public String init(HttpServletRequest request, HttpServletResponse response) {
		this.response = response;
		this.session = request.getSession();
		return request.getParameter("action");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		Double value = Double.parseDouble(request.getParameter("value"));
	
		init(request, response);

		createNewActivity(name,value);
		response.sendRedirect("/gogreen/activity.jsp");
	}

	private void createNewActivity(String name, Double value) {
		activityService.create(new Activity(name, value));
	}
}
