package br.com.g4flex.servlets;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import br.com.g4flex.client.FlexExportClient;

@WebServlet("/export")
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private HttpSession session;

	public ExportServlet() {
		super();
	}

	public String init(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		this.response = response;
		this.session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return request.getParameter("action");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = init(request, response);
		String model = request.getParameter("model");
		
		String json = "{ \"reportname\" : \"Pausa\", \"header\" : [\"teste1\",\"teste2\"] , \"data\" : [[1,2]] } ";

		try {
			if(action.equals("pdf"))
				FlexExportClient.pdf(response,json,model+".pdf");
			else
				FlexExportClient.excel(response,json,model+".xlsx");
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/gogreen/"+model+".jsp");
		}
		
	}
	
}
