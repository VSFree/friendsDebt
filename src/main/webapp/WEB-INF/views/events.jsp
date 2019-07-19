<%--
  Created by IntelliJ IDEA.
  User: slawek
  Date: 15.07.19
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Events</title>
    <%@include file="./fragments/head.jspf" %>
</head>
<body>
<%@include file="./fragments/header.jspf" %>
<div class="cont">
    <h2>Events</h2>

    <div class="widthform">
        <button onClick="document.location.href='/addEvent'" class="btn btn-outline-primary">Add new event</button>
    </div>
    <h3>Your events:</h3>
    <c:forEach items="${eventList}" var="event">
        <button onClick="document.location.href='/eventManager?eventId=${event.id}'" class="btn btn-outline-primary">${event.name}</button><br>
    </c:forEach>
</div>
</body>
</html>
