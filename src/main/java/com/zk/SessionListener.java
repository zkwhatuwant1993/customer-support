package com.zk;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MM yyyy HH:mm:ss");

	@Override
	public void sessionIdChanged(HttpSessionEvent httpSessionEvent, String oldSessionId) {
		System.out.println(date() + ": Session ID " + oldSessionId + " change to " + httpSessionEvent.getSession()
			.getId());
		SessionRepository.updateSession(httpSessionEvent.getSession(), oldSessionId);
	}

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println(date() + ": Session ID" + httpSessionEvent.getSession().getId() + " created.");
		SessionRepository.addSession(httpSessionEvent.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println(date() + ": Session ID" + httpSessionEvent.getSession().getId() + " Destroyed.");
		SessionRepository.removeSession(httpSessionEvent.getSession());
	}

	private String date() {
		return dateFormat.format(new Date());
	}
}
