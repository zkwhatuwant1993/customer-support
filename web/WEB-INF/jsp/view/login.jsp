<%--@elvariable id="loginFailed" type="java.lang.Boolean"--%>
<template:loggedOut htmlTitle="Log In" bodyTitle="Log In">
    You must log in to access the customer support site.<br><br>
    <c:if test="${loginFailed}">
        <b>The username or password you entered are not correct.pls try again.</b><br><br>
    </c:if>
    <form method="post" action="<c:url value="/login" />">
        username<br>
        <input type="text" name="username"><br><br>
        password<br>
        <input type="password" name="password"><br><br>
        <input type="submit" value="login">
    </form>
</template:loggedOut>