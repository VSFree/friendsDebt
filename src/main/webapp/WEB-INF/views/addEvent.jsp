<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: slawek
  Date: 16.07.19
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add event</title>
    <%@include file="./fragments/head.jspf" %>
</head>
<body>

<%@include file="./fragments/header.jspf" %>

<div class="cont">
    <h2>Add new event</h2>
    <form:form method="post" modelAttribute="event" action="addEvent">
        <div class="form-group">
            Event name: <form:input path="name" class="form-control widthform"/>
            <form:errors path="name"/> <br>
        </div>
        <form:hidden path="id"/>
        <div class="widthform">
            <input type="submit" value="Create" class="btn btn-primary btn-lg btn-block">
        </div>
    </form:form>

</div>

</body>
</html>
