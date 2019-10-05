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

import br.com.g4flex.entity.PresentialCalled;
import br.com.g4flex.entity.User;
import br.com.g4flex.service.PresentialCalledService;
import br.com.g4flex.utils.DateUtil;

@WebServlet("/presential")
public class PresentialCalledServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PresentialCalledService presentialCalledService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	public PresentialCalledServlet() {
		super();
		presentialCalledService = new PresentialCalledService();

	}

	public String init(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		this.request = request;
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
		String clientName = request.getParameter("client_name");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date activityDate = DateUtil.stringToDate(request.getParameter("activity_date"), DateUtil.PATTERN_DATE);

		createNewPresentialCalled(callNumber, clientName, activityDate);
		response.sendRedirect("/gogreen/presential.jsp");
	}

	private void createNewPresentialCalled(String callNumber, String clientName, Date activityDate) {
		User user = (User) session.getAttribute("user");
		presentialCalledService.create(new PresentialCalled(callNumber, clientName, activityDate, user));
	}
}
