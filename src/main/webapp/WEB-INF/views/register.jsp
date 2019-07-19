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

<div class="cont">
    <h2>Register</h2>
    <form:form method="post" modelAttribute="user" action="register">
        <div class="form-group">
            Nick: <form:input path="nick" class="form-control widthform"/>
            <form:errors path="nick"/> <br>
        </div>
        <div class="form-group">
            Email: <form:input path="email" class="form-control widthform"/>
            <form:errors path="email"/><br>
        </div>
        <div class="form-group">
            Password: <form:password path="password" class="form-control widthform"/>
            <form:errors path="password"/><br>
        </div>
        <div class="form-group">
            Confirm password: <input type="password" name="confirmPassword" class="form-control widthform"/><br>
        </div>
        <form:hidden path="id"/>
        <div class="widthform">
            <input type="submit" value="Register" class="btn btn-primary btn-lg btn-block">
        </div>
    </form:form>
</div>
</body>
</html>
