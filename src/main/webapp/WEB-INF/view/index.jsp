<%--
  Created by IntelliJ IDEA.
  User: Edu
  Date: 07-06-2019
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<form action="/hello" method="post">
    login<input type="text" name="username" id="username"/>
    password<input type="password" name="password" id="password"/>
    <input type="submit" name="submit" id="submit"/>
</form>
</body>
</html>
