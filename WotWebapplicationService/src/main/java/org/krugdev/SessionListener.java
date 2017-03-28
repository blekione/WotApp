package org.krugdev;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener{

	@Override
	public void sessionIdChanged(HttpSessionEvent arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("session has been created");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("session has been closed");
	}

}
