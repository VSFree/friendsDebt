<%--
  Created by IntelliJ IDEA.
  User: slawek
  Date: 15.07.19
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%@include file="./fragments/head.jspf" %>
</head>
<body>

<%@include file="./fragments/header.jspf" %>
<div class="cont">
<h2>Login</h2>
<c:if test="${success == false}">
    <p>Login failed. Email or password is incorrect.</p>
</c:if>
<form method="post">
    Email: <input type="email" name="email" required /><br />
    Password: <input type="password" name="password" required /><br />
    <input type="submit" value="Login" />
</form>
</div>
</body>
</html>
