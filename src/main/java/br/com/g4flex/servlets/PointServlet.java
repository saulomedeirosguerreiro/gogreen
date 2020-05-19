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
import br.com.g4flex.entity.Point;
import br.com.g4flex.entity.User;
import br.com.g4flex.service.PointService;
import br.com.g4flex.utils.DateUtil;

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
		String justification = request.getParameter("justification");
		String dayOfWeek = request.getParameter("day_of_week");

		punchTheClock(justification, dayOfWeek);
		response.sendRedirect("/gogreen/point.jsp");
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
		PointService pointService = new PointService();
		FiltersDTO filters  = new FiltersDTO (userName, initialDate,finalDate);
		List<Point> pointListWithPagination = pointService.findWithPagination(filters,quantity, numberOfPage);
		List<Point> pointList = pointService.find(filters);
		request.setAttribute("pointList", pointListWithPagination);
		request.setAttribute("total", pointList!=null ? pointList.size() : 0);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/point.jsp");
			    dispatcher.forward(request, response);
	}

	private void punchTheClock(String justification, String dayOfWeek) {
		User user = (User) session.getAttribute("user");
		Point point = pointService.findTodayPoint(user);
		if (point == null) {
			pointService.create(new Point(justification, dayOfWeek, user));
		} else if (point.getCheckOutHour() == null) {
			point.setCheckOutHour(new Date());
			point.setJustification(justification);
			point.setDayOfWeek(dayOfWeek);
			pointService.update(point);
		}
	}
}
