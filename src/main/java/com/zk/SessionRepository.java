package com.zk;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionRepository {

	private static final Map<String, HttpSession> SESSIONS = new HashMap<>();

	private SessionRepository() {

	}

	public static void addSession(HttpSession session) {
		SESSIONS.put(session.getId(), session);
	}

	public static void removeSession(HttpSession session) {
		SESSIONS.remove(session.getId());
	}

	public static void updateSession(HttpSession newSession, String oldSessionId) {
		synchronized (SESSIONS) {
			SESSIONS.remove(oldSessionId);
			SESSIONS.put(newSession.getId(), newSession);
		}
	}

	public static List<HttpSession> getAllSessions() {
		return new ArrayList<>(SESSIONS.values());
	};

	public static int getCountOfSessions() {
		return SESSIONS.size();
	}
}
