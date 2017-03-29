package org.krugdev;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

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
	}

}
