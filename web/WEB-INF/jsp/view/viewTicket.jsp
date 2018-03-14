<%--
  Created by IntelliJ IDEA.
  User: jackz
  Date: 2018/3/7
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false" %>
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
        if (ticket.getNumberOfAttachments() > 0) {
        	%>Attachments: <%
        int i = 0;
        for (Attachment attachment:
             ticket.getAttachments()) {
        	if (i ++ > 0) {
        		%>,<%
            }
            %><a href="<c:url value="/tickets">
            <c:param name="action" value="download" />
            <c:param name="ticketId" value="<%= id%>"/>
            <c:param name="attachment" value="<%= attachment.getName()%>"/>
            </c:url>"><%=attachment.getName()%></a>
        <%
        }
        %><br><br><%
    }
        %>
        <a href="<c:url value="/tickets" /> ">Return to list tickets</a>

</body>
</html>
