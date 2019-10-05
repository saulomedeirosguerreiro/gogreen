package br.com.g4flex.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.g4flex.entity.User;

/**
 * Servlet Filter implementation class SessionFIlter
 */
public class SessionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("user");
		String uri = req.getRequestURI().toString();
		System.out.println(uri);

		try {
			if (user==null && !uri.equals("/gogreen/") && !uri.equals("/gogreen/auth") 
					&& !uri.equals("/gogreen/user")  && !uri.equals("/gogreen/register.jsp")
					&& !uri.contains("/css") && !uri.contains("/resources")) { 
				res.sendRedirect("/gogreen");
				return;
			}
		} catch (Exception e) {
			RequestDispatcher rq = req.getRequestDispatcher("/gogreen");
			rq.forward(req, res);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
