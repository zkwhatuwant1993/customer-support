<%--@elvariable id="ticketDatabase" type="java.util.Map<java.lang.Integer,com.zk.Ticket>"--%>
<template:basic htmlTitle="Tickets" bodyTitle="Tickets">
    <c:choose>
        <c:when test="${fn:length(ticketDatabase) == 0}">
            <i>There are no tickets in the system.</i>
        </c:when>
        <c:otherwise>
            <c:forEach items="${ticketDatabase}" var="entry">
                Ticket #${entry.key}<a href="<c:url value="/tickets">
                    <c:param name="action" value="view" />
                    <c:param name="ticketId" value="${entry.key}" />
                    </c:url> ">
                <c:out value="${zyy:abbreviateString(entry.value.subject,60)}" /></a><br>
                (customer: <c:out value="${entry.value.customerName}" /> Created )
                <zyy:formatDate value="${entry.value.dateCreated}" dateStyle="medium" timeStyle="short"
                                        type="both" />
                <br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</template:basic>