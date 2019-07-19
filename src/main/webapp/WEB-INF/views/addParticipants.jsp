<%--
  Created by IntelliJ IDEA.
  User: slawek
  Date: 16.07.19
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add participants</title>
    <%@include file="./fragments/head.jspf" %>
</head>
<body>
<%@include file="./fragments/header.jspf" %>
<div class="cont">

    <h2>Add participants to ${event.name}</h2>

    <c:if test="${success == false}">
        <p>User does not exits or has been added before.</p>
    </c:if>

    <form method="post" action="addParticipants">
        <div class="form-group">
            Nick: <input type="text" name="participantNick" class="form-control widthform"><br>
        </div>
        <input type="hidden" value="${event.id}" name="eventId">
        <div class="widthform">
            <input type="submit" value="Add" class="btn btn-primary btn-lg btn-block">
        </div>
    </form>
    <br>
    <button onClick="document.location.href='/eventManager?eventId=${event.id}'"
            class="btn btn-outline-primary">Go back</button>

</div>
</body>
</html>
