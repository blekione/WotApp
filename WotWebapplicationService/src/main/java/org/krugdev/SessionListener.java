package org.krugdev;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

import org.krugdev.domain.SessionWN8;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener{

	@Override
	public void sessionIdChanged(HttpSessionEvent e, String oldSessionId) {
		System.out.println("session id changed " + e.getSession().getId());
	}

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		System.out.println("session has been created" + e.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		System.out.println("session has been closed" + e.getSession().getId());
		String sessionId = e.getSession().getId();
		String playerId = (String)e.getSession().getAttribute("id");
		String platform = (String)e.getSession().getAttribute("platform");
		WN8ServiceReader wn8ServiceReader = WN8ServiceReader.getInstance(platform);
		wn8ServiceReader.closeRunningSessionWN8(playerId, sessionId);
	}

}
