<%--
  Created by IntelliJ IDEA.
  User: jackz
  Date: 2018/3/7
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.zk.Ticket" %>
<%@ page import="com.zk.Attachment" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Ticket ticket = (Ticket) request.getAttribute("ticket");
    String id = (String) request.getAttribute("id");
%>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h2>Ticket #<%= id%>: <%= ticket.getSubject()%></h2><br>
    <i>Customer Name - <%= ticket.getCustomerName()%></i><br><br>
    <%= ticket.getBody()%><br><br>
    <%
        if (ticket.getNumberOfAttachments() > 0)
        	%>Attachments: <%
        int i = 0;
        for (Attachment attachment:
             ticket.getAttachments()) {
        	if (i ++ > 0) {
        		%>,<%
            }
            %><a href="tickets?action=download&ticketId=<%= id%>&attachment=<%= attachment.getName()%>"><%=attachment.getName()%></a>
            <br><br><%
        }
        %><a href="tickets">Return to list tickets</a><%
    %>

</body>
</html>
