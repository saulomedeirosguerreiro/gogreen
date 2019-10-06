package br.com.g4flex.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.g4flex.entity.User;
import br.com.g4flex.service.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private HttpServletResponse response;
	private HttpSession session;

	public UserServlet() {
		super();
		userService = new UserService();
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		createNewUser(name, email, password);
		response.sendRedirect("/gogreen/register.jsp");
	}

	private void createNewUser(String name, String email, String password) {
		userService.create(new User(name, email, password));
	}
}
