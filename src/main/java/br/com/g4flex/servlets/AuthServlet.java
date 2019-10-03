package br.com.g4flex.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.g4flex.service.UserService;
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	public AuthServlet() {
	        super();
	        userService = new UserService();
	        
	}
	private boolean login(String email, String password) {
		return userService.login(email, password);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if(login(email,password)) {
        	response.sendRedirect("/gogreen/home.jsp");
        }
        else {
        	response.sendRedirect("/gogreen");
        }
	}

}
