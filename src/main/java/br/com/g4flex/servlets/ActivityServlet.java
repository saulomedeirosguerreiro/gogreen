package br.com.g4flex.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.g4flex.entity.Activity;
import br.com.g4flex.service.ActivityService;

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
		String name = request.getParameter("name");
		Double value = Double.parseDouble(request.getParameter("value"));

		createNewActivity(name, value);
		response.sendRedirect("/gogreen/activity.jsp");
	}

	private void createNewActivity(String name, Double value) {
		activityService.create(new Activity(name, value));
	}
}
