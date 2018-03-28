<%--@elvariable id="ticketDatabase" type="java.util.Map<java.lang.Integer,com.zk.Ticket>"--%>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <a href="<c:url value="login?logout" />">logout</a>
    <h2>Tickets</h2>
    <a href="<c:url value="/tickets">
        <c:param name="action" value="create" />
    </c:url> ">Create Tickets</a>
    <br><br>
    <c:choose>
        <c:when test="${fn:length(ticketDatabase) == 0}">
            <i>There are no tickets in the system.</i>
        </c:when>
        <c:otherwise>
            <c:forEach items="ticketsDatabase" var="entry">
                Ticket #${entry.key}<a href="<c:url value="/tickets">
                    <c:param name="action" value="view" />
                    <c:param name="ticketId" value="${entry.key}" />
                    </c:url> "><c:out value="${entry.value.subject}" /></a>
                    (customer: <c:out value="${entry.value.customerName}" />)
                <br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</body>
</html>