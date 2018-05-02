package com.zk.servlet;

import com.zk.SessionRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
	name = "sessionListServlet",
	urlPatterns = "/sessions"
)
public class SessionListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("countOfSessions", SessionRepository.getCountOfSessions());
		req.setAttribute("sessionList", SessionRepository.getAllSessions());
		req.getRequestDispatcher("/WEB-INF/jsp/view/sessions.jsp").forward(req, resp);
	}
}
