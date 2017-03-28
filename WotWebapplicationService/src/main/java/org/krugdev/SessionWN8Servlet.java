package org.krugdev;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionWN8Servlet extends HttpServlet {

	private static final long serialVersionUID = -2173514291399304530L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch(request.getParameter("action")) {
		case "start": startSessionWN8();
		break;
		case "get": getSessionWN8();
		break;
		case "end": endSessionWN8();
		break;
		case "none": 
		default:
			request.getRequestDispatcher("/WEB-INF/jsp/view/sessionWN8.jsp").forward(request, response);
		}
	}

	private void endSessionWN8() {
		// TODO Auto-generated method stub
		
	}

	private void getSessionWN8() {
		// TODO Auto-generated method stub
		
	}

	private void startSessionWN8() {
		// TODO Auto-generated method stub
		
	}
}
