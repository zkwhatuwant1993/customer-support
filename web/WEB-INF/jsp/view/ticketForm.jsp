<%--
  Created by IntelliJ IDEA.
  User: jackz
  Date: 2018/3/7
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
    <h2>Create a Ticket</h2><br>
    <form method="post" action="/tickets" enctype="multipart/form-data">
        <input type="hidden" name="action" value="create">
        YourName<br>
        <input type="text" name="customerName" /><br><br>
        Subject<br>
        <input type="text" name="subject" /><br><br>
        Body<br>
        <textarea name="body" rows="5" cols="30"></textarea><br><br>
        <b>Attachments</b><br>
        <input type="file" name="file1"><br><br>
        <input type="submit" value="Submit">
    </form>

</body>
</html>
