<%@ page session="false" import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: jackz
  Date: 2018/3/7
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%
    @SuppressWarnings("unchecked")
    Map<Integer,Ticket> ticketsDatabase =
            (Map<Integer, Ticket>) request.getAttribute("ticketDatabase");

%>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h2>Tickets</h2>
    <a href="<c:url value="/tickets">
        <c:param name="action" value="create" />
    </c:url> ">Create Tickets</a><br><br>
    <%
        if (ticketsDatabase.size() == 0) {
            %><i>There are no tickets in the system.</i><%
        } else {
            Ticket ticket;
            for (Integer id :
                    ticketsDatabase.keySet()) {
                String ticketId = String.valueOf(id);
                ticket = ticketsDatabase.get(id);
                %>Ticket #<%= id%>
                    <a href="<c:url value="/tickets">
                    <c:param name="action" value="view" />
                    <c:param name="ticketId" value="<%= ticketId%>" />
                    </c:url> ">
                    <%= ticket.getSubject()%></a>
                    (customer: <%= ticket.getCustomerName()%>)<br>
    <%
            }
        }
    %>
</body>
</html>