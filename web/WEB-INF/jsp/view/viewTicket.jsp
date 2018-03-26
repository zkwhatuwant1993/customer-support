<%--@elvariable id="ticketId" type="java.lang.String"--%>
<%--@elvariable id="ticket" type="com.zk.Ticket"--%>
<%
    Ticket ticket = (Ticket) request.getAttribute("ticket");
%>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h2>Ticket #${ticket}: ${ticket.subject}</h2><br>
    <i>Customer Name - ${ticket.customerName}</i><br><br>
    ${ticket.body}<br><br>
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
            <c:param name="ticketId" value="${ticketId}"/>
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
