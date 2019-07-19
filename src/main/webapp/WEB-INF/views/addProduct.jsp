<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: slawek
  Date: 16.07.19
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
    <%@include file="./fragments/head.jspf" %>
</head>
<body>
<%@include file="./fragments/header.jspf" %>
<div class="cont">

    <h2>Add product to ${event.name}</h2> <br>

    <form:form method="post" modelAttribute="product" action="addProduct">
        <div class="form-group">
            Product name: <form:input path="name" class="form-control widthform"/>
            <form:errors path="name"/> <br>
        </div>
        <div class="form-group">
            Price: <form:input path="price" class="form-control widthform"/>
            <form:errors path="price"/><br>
        </div>

        <c:forEach items="${participants}" var="participant">
            <div class="form-check">
                <label style="cursor:pointer">
                    <form:checkbox path="participantIds" class="form-check-input" value="${participant.id}"/>
                        ${participant.nick}
                </label>
            </div>
        </c:forEach>
        <br>
        <%--        <div class="form-check">--%>
        <%--            <form:checkboxes path="participantIds" items="${participants}"--%>
        <%--                             itemValue="id" itemLabel="nick" class="form-check-input"/><br>--%>
        <%--        </div>--%>

        <input type="hidden" value="${event.id}" name="eventId">

        <form:hidden path="id"/>
        <div class="widthform">
            <input type="submit" value="Add product" class="btn btn-primary btn-lg btn-block">
        </div>
    </form:form>
    <button onClick="document.location.href='/eventManager?eventId=${event.id}'"
            class="btn btn-outline-primary">Go back
    </button>
</div>
</body>
</html>
