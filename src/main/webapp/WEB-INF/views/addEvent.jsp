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

        Event name: <form:input path="name"/>
        <form:errors path="name"/> <br>

        <form:hidden path="id"/>
        <input type="submit" value="Create">
    </form:form>

</div>

</body>
</html>
