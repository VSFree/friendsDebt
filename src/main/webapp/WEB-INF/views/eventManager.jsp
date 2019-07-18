<%--
  Created by IntelliJ IDEA.
  User: slawek
  Date: 16.07.19
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event manager</title>
    <%@include file="./fragments/head.jspf" %>
</head>
<body>
<%@include file="./fragments/header.jspf" %>
<div class="cont">
    <h2>${event.name}</h2><br>
    <button onClick="document.location.href='/addProduct?eventId=${event.id}'">Add product</button>
    <button onClick="document.location.href='/addParticipants?eventId=${event.id}'">Add participants</button>
    <br>

    <h3>Products I bought:</h3>

    <table border="1px">

        <tr>
            <th>Product</th>
            <th>Price</th>
        </tr>

        <c:forEach items="${eventGroups}" var="eventGroup">
            <tr>
                <c:forEach items="${eventGroup.products}" var="product">
                    <td>${product.getName()}</td>
                    <td>${product.getPrice()}</td>
                </c:forEach>
            </tr>
        </c:forEach>

    </table>

    <br>

    <table border="1px">
        <tr>
            <th>User</th>
            <th>Debt</th>
        </tr>
        <c:forEach items="${debtorsMap}" var="debtor">
            <tr>
            <td>${debtor.key}</td>
            <td>${debtor.value}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <div>
        <form>
            <h6>Repayment:</h6><br>
            Nick:<input type="text" name="nickRepay"><br>
            How much:<input type="number" name="amountRepay"><br>
            <input type="submit" value="Update">
        </form>
    </div>
    <br>
    <div>
        <h3>My debts:</h3>
        <table border="1px">
            <tr>
                <th>User</th>
                <th>Debt</th>
            </tr>
            <c:forEach items="${creditors}" var="creditor">
                <tr>
                    <td>${creditor.key}</td>
                    <td>${creditor.value}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>
