package org.krugdev;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krugdev.domain.SearchPlayerResult;

public class WN8Servlet  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/view/wn8.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String query = request.getParameter("query");
		String platform = request.getParameter("platform");
		if (query.length() < 3) {
			request.setAttribute("query", query);
			request.getRequestDispatcher("/WEB-INF/jsp/view/searchQueryTooShort.jsp").forward(request, response);
			return;
		}
		WoTApiServiceReader wotApiServiceReader = new WoTApiServiceReader();
		List<SearchPlayerResult> playersSearch = wotApiServiceReader.searchForPlayers(platform, query);
		if (playersSearch.isEmpty()) {
			request.setAttribute("query", query);
			request.setAttribute("platform", platform);
			request.getRequestDispatcher("/WEB-INF/jsp/view/searchQueryNoResultsFound.jsp").forward(request, response);
			return;
		}			
		request.setAttribute("playersSearch", playersSearch);
		request.getRequestDispatcher("/WEB-INF/jsp/view/searchQueryResult.jsp").forward(request, response);		
	}	
}
