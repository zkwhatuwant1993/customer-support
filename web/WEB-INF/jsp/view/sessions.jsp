<%--@elvariable id="countOfSession" type="java.lang.Integer"--%>
<%--@elvariable id="timestamp" type="java.lang.Long"--%>
<%--@elvariable id="sessionList" type="java.util.List<javax.servlet.http.HttpSession>"--%>

<template:basic htmlTitle="Active Sessions" bodyTitle="Active Sessions">
    There are a total of ${countOfSession} active sessions in this.
    application.<br /><br />
    <c:forEach items="${sessionList}" var="session">
        <c:out value="${session.id}-${session.getAttribute('username')}"/>
        <c:if test="${session.id == pageContext.session.id}">&nbsp;(you)</c:if>
        &nbsp;- last active ${zyy:timeIntervalToString(session.lastAccessedTime)} ago<br>
    </c:forEach>
</template:basic>