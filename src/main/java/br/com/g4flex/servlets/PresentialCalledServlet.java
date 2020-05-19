package br.com.g4flex.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		init(request, response);
		int quantity =  Integer.valueOf(request.getParameter("quantity"));
		int numberOfPage =  Integer.valueOf(request.getParameter("page"));
		String userName = request.getParameter("username");
		Date initialDate = DateUtil.stringToDate(request.getParameter("initialdate"), DateUtil.PATTERN_DATE);
		Date finalDate = DateUtil.stringToDate(request.getParameter("finaldate"), DateUtil.PATTERN_DATE);
		//System.out.println(initialDate + ", " + finalDate + ", " + userName + ", quantity = " + quantity + ", page = " + numberOfPage);
		PresentialCalledService presentialCalledService = new PresentialCalledService();
		FiltersDTO filters  = new FiltersDTO (userName, initialDate,finalDate);
		List<PresentialCalled> presentialCalledListWithPagination = presentialCalledService.findWithPagination(filters,quantity, numberOfPage);
		List<PresentialCalled> presentialCalledList = presentialCalledService.find(filters);
		request.setAttribute("presentialCalledList", presentialCalledListWithPagination);
		request.setAttribute("total", presentialCalledList!=null ? presentialCalledList.size() : 0);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/presential.jsp");
			    dispatcher.forward(request, response);
	}

	private void createNewPresentialCalled(String callNumber, String clientName, Date activityDate) {
		User user = (User) session.getAttribute("user");
		presentialCalledService.create(new PresentialCalled(callNumber, clientName, activityDate, user));
	}
}
