package br.com.g4flex.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.g4flex.entity.User;
import br.com.g4flex.service.UserService;
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private HttpServletResponse response;
	private  HttpSession session ;
	
	public AuthServlet() {
	        super();
	        userService = new UserService();
	        
	}

	public String init(HttpServletRequest request, HttpServletResponse response) {
		this.response = response;
		this.session =  request.getSession();
		return request.getParameter("action");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String action = init(request,response);
        
        switch (action.toLowerCase()) {
		case "login":
			login(email,password);
			break;
		}
        
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       String action = init(request,response);
		   switch (action.toLowerCase()) {
			case "logout":
				logout();
				break;
			}
	}
	
	private void login(String email, String password) throws IOException {
		User user = userService.login(email, password);
		if(user!=null) {
			session.setAttribute("user", user);
			response.sendRedirect("/gogreen/home.jsp");
		}
		else {
			response.sendRedirect("/gogreen");
		}
	}
	
	private void logout() throws IOException{
			session.removeAttribute("user");
			response.sendRedirect("/gogreen");
	}
}
