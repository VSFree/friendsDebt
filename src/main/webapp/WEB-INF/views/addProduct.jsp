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

        Product name: <form:input path="name"/>
        <form:errors path="name"/> <br>

        Price: <form:input path="price"/>
        <form:errors path="price"/><br>


        <form:checkboxes path="participantIds" items="${participants}" itemValue="id" itemLabel="nick"/><br>
<%--        <c:forEach items="${participants}" var="participant">--%>
<%--            ${participant.nick}: <input type="checkbox" name="participants" value="${participant.id}"><br>--%>
<%--        </c:forEach>--%>
        <input type="hidden" value="${event.id}" name="eventId">

        <form:hidden path="id"/>
        <input type="submit" value="Add product">
    </form:form>
</div>
</body>
</html>
