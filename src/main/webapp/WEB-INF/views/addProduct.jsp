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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        var buttonAdd = $("section.creditorAdd button")

        buttonAdd.on("click", function () {
            var newInput = $("<input type='text' name='creditors'/><br>")

            $("section.appender").append(newInput)
        })
    </script>
</head>
<body>
<%@include file="./fragments/header.jspf" %>
<div class="cont">

    <h2>Add product to ${event.name}</h2> <br>
    <section class="creditorAdd">
        <button>Add creditor</button>
    </section>
    <form:form method="post" modelAttribute="product" action="addProduct">

        Product name: <form:input path="name"/>
        <form:errors path="name"/> <br>

        Price: <form:input path="price"/>
        <form:errors path="price"/><br>

        <section class="appender">Creditors: </section><input type="text" name="creditors"/><br>

        <form:hidden path="id"/>
        <input type="submit" value="Add product">
    </form:form>
</div>
</body>
</html>
