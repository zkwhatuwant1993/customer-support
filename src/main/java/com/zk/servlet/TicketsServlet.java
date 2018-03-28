package com.zk.servlet;

import com.zk.Attachment;
import com.zk.Ticket;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
	name = "ticketsServlet",
	urlPatterns = "/tickets",
	loadOnStartup = 1
)
@MultipartConfig(
	fileSizeThreshold = 5_242_880, //5MB
	maxFileSize = 20_971_520L, //20MB
	maxRequestSize = 41_943_040L //40MB
)
public class TicketsServlet extends HttpServlet {
	private volatile int TICKET_ID_SEQUENCE = 0;
	private Map<Integer, Ticket> ticketDatabase = new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("username") == null) {
			resp.sendRedirect("login");
			return;
		}
		String action = req.getParameter("action");
		if (action == null) {
			listTickets(req, resp);
		} else {
			switch (action) {
				case "create":
					showTicketForm(req, resp);
					break;
				case "view":
					viewTicket(req, resp);
					break;
				case "download":
					downloadAttachment(req, resp);
					break;
			}
		}
	}

	private void downloadAttachment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//根据用户提交的请求参数在数据库里查出对应的信息
		String id = req.getParameter("ticketId");
		Ticket ticket = ticketDatabase.get(Integer.valueOf(id));
		if (ticket == null) {
			return;
		}

		String attachmentName = req.getParameter("attachment");
		if (attachmentName == null) {
			resp.sendRedirect("tickets?action=view&ticketId=" + id);
			return;
		}
		Attachment attachment = ticket.getAttachment(attachmentName);
		if (attachment == null) {
			resp.sendRedirect("tickets?action=view&ticketId=" + id);
			return;
		}

		//将附件返回给客户端
		//设置响应头
		resp.setHeader("Content-Disposition",
			"attachment; filename=" + attachment.getName());
		//以流输出内容
		ServletOutputStream servletOutputStream = resp.getOutputStream();
		servletOutputStream.write(attachment.getContent());
	}

	private void viewTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ticketId = req.getParameter("ticketId");
		Ticket ticket = ticketDatabase.get(Integer.valueOf(ticketId));

		req.setAttribute("ticket", ticket);
		req.setAttribute("ticketId", ticketId);
		req.getRequestDispatcher("WEB-INF/jsp/view/viewTicket.jsp").forward(req, resp);
	}

	private void showTicketForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
		IOException {
		req.getRequestDispatcher("WEB-INF/jsp/view/ticketForm.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("username") == null) {
			resp.sendRedirect("login");
			return;
		}
		String action = req.getParameter("action");
		if (action == null) {
			action = "list";
		}
		switch (action) {
			case "create":
				createTicket(req, resp);
				break;
			case "download":
			default:
				resp.sendRedirect("tickets");
				break;
		}

	}

	private void createTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 根据用户填写信息实例化Ticket数据并保存到数据库中
		//实例化Ticket
		Ticket ticket = new Ticket();
		ticket.setCustomerName((String) req.getSession().getAttribute("username"));
		ticket.setSubject(req.getParameter("subject"));
		ticket.setBody(req.getParameter("body"));

		//实例化附件
		Part filePart = req.getPart("file1");
		if (filePart != null && filePart.getSize() > 0) {
			Attachment attachment = processAttachment(filePart);
			if (attachment != null) {
				ticket.addAttachment(attachment);
			}
		}

		//将票据数据存入数据库中
		int id;
		synchronized (this) {
			id = ++TICKET_ID_SEQUENCE;
			ticketDatabase.put(id, ticket);
		}

		//重定向到TicketList页面
		resp.sendRedirect("tickets?action=view&ticketId=" + id);
	}

	private Attachment processAttachment(Part filePart) throws IOException {
		//获取流
		InputStream inputStream = filePart.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int read;
		byte[] buffer = new byte[2048];
		while ((read = inputStream.read(buffer)) != -1) {
			baos.write(buffer, 0, read);
		}
		Attachment attachment = new Attachment();
		attachment.setName(filePart.getSubmittedFileName());
		attachment.setContent(baos.toByteArray());
		return attachment;
	}

	private void listTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("ticketDatabase", ticketDatabase);

		//请求转换交给页面处理
		req.getRequestDispatcher("/WEB-INF/jsp/view/listTickets.jsp").forward(req, resp);
	}
}
