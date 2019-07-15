<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: slawek
  Date: 15.07.19
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <%@include file="./fragments/head.jspf" %>
</head>
<body>

<%@include file="./fragments/header.jspf" %>

<div class="container">
    <form:form method="post" modelAttribute="user" action="register">

        Nick: <form:input path="nick"/>
        <form:errors path="nick"/> <br>

        Email: <form:input path="email"/>
        <form:errors path="email"/><br>

        Password: <form:password path="password"/>
        <form:errors path="password"/><br>

        Confirm password: <input type="password" name="confirmPassword"/><br>

        <form:hidden path="id"/>
        <input type="submit" value="Register">
    </form:form>
</div>
</body>
</html>
