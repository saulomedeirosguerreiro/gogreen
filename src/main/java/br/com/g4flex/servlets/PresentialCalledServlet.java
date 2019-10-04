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

import br.com.g4flex.entity.PresentialCalled;
import br.com.g4flex.entity.User;
import br.com.g4flex.service.PresentialCalledService;

@WebServlet("/presential")
public class PresentialCalledServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PresentialCalledService presentialCalledService;
	private HttpServletResponse response;
	private HttpSession session;

	public PresentialCalledServlet() {
		super();
		presentialCalledService = new PresentialCalledService();

	}

	public String init(HttpServletRequest request, HttpServletResponse response) {
		this.response = response;
		this.session = request.getSession();
		return request.getParameter("action");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String callNumber = request.getParameter("call_number");
		String clientName = request.getParameter("client_name");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date activityDate = null;
		try {
			activityDate = sdf.parse(request.getParameter("activity_date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		init(request, response);

		createNewPresentialCalled(callNumber, clientName, activityDate);
		response.sendRedirect("/gogreen/presential.jsp");
	}

	private void createNewPresentialCalled(String callNumber, String clientName, Date activityDate) {
		User user = (User) session.getAttribute("user");
		presentialCalledService.create(new PresentialCalled(callNumber, clientName, activityDate, user));
	}
}
