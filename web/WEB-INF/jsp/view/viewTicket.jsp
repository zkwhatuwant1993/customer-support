<%--@elvariable id="ticketId" type="java.lang.String"--%>
<%--@elvariable id="ticket" type="com.zk.Ticket"--%>

<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h2>Ticket #${ticketId}: ${ticket.subject}</h2><br>
    <i>Customer Name - ${ticket.customerName}</i><br><br>
    ${ticket.body}<br><br>
    <c:if test="${ticket.numberOfAttachments > 0}">
        Attachments:
        <c:forEach items="${ticket.attachments}" var="attachment" varStatus="status">
            <c:if test="${!status.first}">,</c:if>
            <a href="<c:url value="/tickets">
            <c:param name="action" value="download" />
            <c:param name="ticketId" value="${ticketId}"/>
            <c:param name="attachment" value="${attachment.name}"/>
            </c:url>">${attachment.name}</a>
        </c:forEach>
        <br><br>
    </c:if>
    <a href="<c:url value="/tickets" /> ">Return to list tickets</a>
</body>
</html>
