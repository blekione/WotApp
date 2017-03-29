package org.krugdev;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.krugdev.domain.SessionWN8;
import org.krugdev.domain.WN8;

public class SessionWN8Servlet extends HttpServlet {

	private static final long serialVersionUID = -2173514291399304530L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch(request.getParameter("action")) {
		case "start": startSessionWN8(request, response);
		break;
		case "get": getSessionWN8(request, response);
		break;
		case "end": endSessionWN8(request, response);
		break;
		case "none": 
		default:
			request.setAttribute("platform", request.getParameter("platform"));
			request.setAttribute("playerId", request.getParameter("id"));
			goToPage(request, response);
		}
	}

	private void startSessionWN8(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String platform = request.getParameter("platform");
		String playerId = request.getParameter("id");
		
		String sessionId = request.changeSessionId();
		
		WN8ServiceReader wn8ServiceReader = WN8ServiceReader.getInstance(platform);
		SessionWN8 sessionWN8 = wn8ServiceReader.startSessionWN8(playerId, sessionId);
		
		request.getSession().setAttribute("sessionStarted", true);
		request.setAttribute("sessionWN8", sessionWN8);
		request.setAttribute("playerId", playerId);
		request.setAttribute("platform", platform);
		
		goToPage(request, response);
	}

	private void endSessionWN8(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String platform = request.getParameter("platform");
		String playerId = request.getParameter("id");
		HttpSession httpSession = request.getSession();
		String sessionId = httpSession.getId();		

		WN8ServiceReader wn8ServiceReader = WN8ServiceReader.getInstance(platform);
		SessionWN8 sessionWN8 = wn8ServiceReader.closeRunningSessionWN8(playerId, sessionId);

		request.getSession().setAttribute("sessionStarted", false);
		request.setAttribute("sessionStarted", false);
		request.setAttribute("sessionWN8color", WN8.getColor(sessionWN8.getWn8Value()));
		request.setAttribute("sessionWN8rank", WN8.getRank(sessionWN8.getWn8Value()));
		request.setAttribute("sessionWN8", sessionWN8);
		request.setAttribute("playerId", playerId);
		request.setAttribute("platform", platform);
		
		goToPage(request, response);
	}

	private void getSessionWN8(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String platform = request.getParameter("platform");
		String playerId = request.getParameter("id");
		HttpSession httpSession = request.getSession();
		String sessionId = httpSession.getId();		
		boolean sessionStarted = (boolean)httpSession.getAttribute("sessionStarted");
		
		WN8ServiceReader wn8ServiceReader = WN8ServiceReader.getInstance(platform);
		SessionWN8 sessionWN8 = wn8ServiceReader.getRunningSessionWN8(playerId, sessionId);
		
		request.setAttribute("sessionStarted", sessionStarted);
		request.setAttribute("sessionWN8color", WN8.getColor(sessionWN8.getWn8Value()));
		request.setAttribute("sessionWN8rank", WN8.getRank(sessionWN8.getWn8Value()));
		request.setAttribute("sessionWN8", sessionWN8);
		request.setAttribute("playerId", playerId);
		request.setAttribute("platform", platform);
		
		goToPage(request, response);
	}
	
	private void goToPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		request.getRequestDispatcher("/WEB-INF/jsp/view/sessionWN8.jsp").forward(request, response);		
	}

}
