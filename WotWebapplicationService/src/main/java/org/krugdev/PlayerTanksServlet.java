package org.krugdev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.krugdev.domain.TankDescription;
import org.krugdev.domain.TankWN8;
import org.krugdev.util.TankDescriptionRepository;

public class PlayerTanksServlet extends HttpServlet {

	private static final int NUMBER_OF_ITEMS_PER_PAGE = 16;
	
	@PersistenceContext(unitName="servicewn8")
	EntityManager em;
	private static final long serialVersionUID = 5906927860726896306L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer pageNumber = Integer.parseInt(request.getParameter("page"));
		String playerId = request.getParameter("id");
		String platform = request.getParameter("platform");
		WN8ServiceReader wn8ServiceReader = new WN8ServiceReader();
		List<TankWN8> tanksWN8 = wn8ServiceReader.getTanksWN8(playerId, platform);
		int numberOfPages = getNumberOfPages(tanksWN8);
		List<TankWN8> tanksWN8ForPage = getTanksWN8ForPage(pageNumber, numberOfPages, tanksWN8);
		Map<Integer, TankDescription> tankDescriptions = getTankDescriptions(tanksWN8ForPage);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("playerId", playerId);
		request.setAttribute("platform", platform);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("tanksWN8", tanksWN8ForPage);
		request.setAttribute("tankDescriptions", tankDescriptions);
		request.getRequestDispatcher("/WEB-INF/jsp/view/playerTanks.jsp").forward(request, response);
	}

	private  int getNumberOfPages(List<TankWN8> tanksWN8) {
		return (int)Math.ceil(((double)tanksWN8.size()) / NUMBER_OF_ITEMS_PER_PAGE);
	}
	
	private List<TankWN8> getTanksWN8ForPage(Integer pageNumber, int numberOfPages, List<TankWN8> tanksWN8) {
		List<TankWN8> tanksWN8ForPage = new ArrayList<>();
		if (lastPage(pageNumber, numberOfPages)) {
			for(int i = (pageNumber - 1) * NUMBER_OF_ITEMS_PER_PAGE ; i < tanksWN8.size() - 1 ; i++) {
				tanksWN8ForPage.add(tanksWN8.get(i));
			}
		} else {
			for(int i = (pageNumber - 1) * NUMBER_OF_ITEMS_PER_PAGE ; i < pageNumber * NUMBER_OF_ITEMS_PER_PAGE ; i++) {
				tanksWN8ForPage.add(tanksWN8.get(i));
			}
		}
		return tanksWN8ForPage;
	}

	private boolean lastPage(Integer pageNumber, int numberOfPages) {
		return pageNumber == numberOfPages;
	}

	private Map<Integer, TankDescription> getTankDescriptions(List<TankWN8> tanksWN8) {
		TankDescriptionRepository tankDescriptionRepository = new TankDescriptionRepository(em);
		List<Integer> tankIds = tanksWN8.stream().map(TankWN8::getTankId).collect(Collectors.toList());
		return tankDescriptionRepository.getTankDescriptions(tankIds);
	}
}
