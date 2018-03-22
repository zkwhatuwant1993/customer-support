<html>
<head>
    <title>Login for getting support</title>
</head>
<body>
    <h2>Login</h2>
    <%
        if ((Boolean) request.getAttribute("loginFailed")) {
            %><b>The username or password you entered are not correct.pls try again.</b><br><%
        }
    %>
    <form method="post" action="<c:url value="/login" />">
        username<br>
        <input type="text" name="username"><br><br>
        password<br>
        <input type="password" name="password"><br><br>
        <input type="submit" value="login">
    </form>
</body>
</html>
