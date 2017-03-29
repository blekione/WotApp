package org.krugdev;

import java.io.IOException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krugdev.domain.playerProfile.PlayerProfile;

public class PlayerServlet extends HttpServlet{ 
	
	private static final long serialVersionUID = 3738927450544665371L;
	@PersistenceContext(unitName="servicewn8")
	EntityManager em;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String playerId = request.getParameter("id");
		String platform = request.getParameter("platform");
		System.out.println(playerId + " " + platform);
		WoTApiServiceReader wotApiServiceReader = new WoTApiServiceReader();
		Optional<PlayerProfile> playerProfile = wotApiServiceReader.getPlayerProfile(playerId, platform);
		if (!playerProfile.isPresent()) {
			request.setAttribute("playerId", playerId);
			request.setAttribute("platform", platform);
			request.getRequestDispatcher("/WEB-INF/jsp/view/playerProfileNotFound.jsp").forward(request, response);
			return;
		}
		WN8ServiceReader wn8ServiceReader = WN8ServiceReader.getInstance(platform);
		request.setAttribute("playerWN8", wn8ServiceReader.getPlayerWN8(playerId));
		request.setAttribute("playerProfile", playerProfile.get());
		request.getRequestDispatcher("/WEB-INF/jsp/view/playerProfile.jsp").forward(request, response);	
	}

}
