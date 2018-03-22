package com.zk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
	name = "loginServlet",
	urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {

	private static final Map<String, String> userDatabase = new HashMap<>();

	public LoginServlet() {
		userDatabase.put("zk", "123");
		userDatabase.put("wy", "123");
		userDatabase.put("zm", "123");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		HttpSession session = req.getSession();
		if (req.getParameter("logout") != null) {
			session.invalidate();
			resp.sendRedirect("login");
			return;
		}

		//查看用户是否已经登陆(用户登陆数据存储在Session中)
		if (session.getAttribute("username") != null) {
			//如果已经登陆(Session未过期且有用户名)，重定向至tickets
			resp.sendRedirect("tickets");
			return;
		}

		req.setAttribute("loginFailed", false);
		req.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查看用户是否已经登陆(用户登陆数据存储在Session中)
		HttpSession session = req.getSession();
		if (session.getAttribute("username") != null) {
			//如果已经登陆(Session未过期且有用户名)，重定向至tickets
			resp.sendRedirect("tickets");
			return;
		}

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		//用户名密码验证
		if (username == null || password == null || !userDatabase.containsKey(username) || !password.equals
			(userDatabase.get(username))) {
			//验证失败
			req.setAttribute("loginFailed", true);
			req.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(req, resp);

		} else {
			session.setAttribute("username", username);
			req.changeSessionId();
			resp.sendRedirect("tickets");
		}
	}
}
