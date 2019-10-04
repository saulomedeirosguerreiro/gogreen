package br.com.g4flex.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.g4flex.entity.User;
import br.com.g4flex.service.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public UserServlet() {
		super();
		userService = new UserService();

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
