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
import br.com.g4flex.entity.Point;
import br.com.g4flex.entity.User;
import br.com.g4flex.service.ActivityService;
import br.com.g4flex.service.ExtraActivityService;
import br.com.g4flex.service.PointService;

@WebServlet("/point")
public class PointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PointService pointService;
	private HttpServletResponse response;
	private HttpSession session;

	public PointServlet() {
		super();
		pointService = new PointService();

	}

	public String init(HttpServletRequest request, HttpServletResponse response) {
		this.response = response;
		this.session = request.getSession();
		return request.getParameter("action");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String justification = request.getParameter("justification");
		String dayOfWeek = request.getParameter("day_of_week");
	
		init(request, response);

		punchTheClock(justification,  dayOfWeek);
		response.sendRedirect("/gogreen/point.jsp");
	}

	private void punchTheClock(String justification, String dayOfWeek) {
		User user = (User) session.getAttribute("user");
		Point point = pointService.findTodayPoint(user);
		if(point==null) {
			pointService.create(new Point( justification,  dayOfWeek, user));
		}else if(point.getCheckOutHour()==null) {
			point.setCheckOutHour(new Date());
			point.setJustification(justification);
			point.setDayOfWeek(dayOfWeek);
			pointService.update(point);
		}
	}
}
