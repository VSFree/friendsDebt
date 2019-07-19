<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <button onClick="document.location.href='/addProduct?eventId=${event.id}'"
            class="btn btn-outline-primary">Add product
    </button>
    <button onClick="document.location.href='/addParticipants?eventId=${event.id}'"
            class="btn btn-outline-primary">Add participants
    </button>
    <br>

    <h3>Products I bought:</h3>
    <div class="widthform form-floater-left">
        <table class="table table-sm">
            <thead>
            <tr>
                <th>Product</th>
                <th>Price</th>
            </tr>
            </thead>
            <c:forEach items="${eventGroups}" var="eventGroup">
                <tr>
                    <c:forEach items="${eventGroup.products}" var="product">
                        <td>${product.getName()}</td>
                        <td>
                            <fmt:formatNumber maxFractionDigits="2"
                                              minFractionDigits="2">
                                ${product.getPrice()}
                            </fmt:formatNumber>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
    </div>
    <br>
    <div class="widthform">
        <table class="table table-sm">
            <thead>
            <tr>
                <th>User</th>
                <th>Debt</th>
            </tr>
            </thead>
            <c:forEach items="${debtorsMap}" var="debtor">
                <tr>
                    <td>${debtor.key}</td>
                    <td>
                        <fmt:formatNumber maxFractionDigits="2"
                                          minFractionDigits="2">
                            ${debtor.value}
                        </fmt:formatNumber>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <br>
    <div class="widthform">
        <form>
            <h5>Repayment:</h5><br>
            <div class="form-group">
                Nick:<input type="text" name="nickRepay" class="form-control widthform"><br>
            </div>
            <div class="form-group">
                How much:<input type="number" name="amountRepay" class="form-control widthform"><br>
            </div>
            <input type="submit" value="Update" class="btn btn-primary btn-lg btn-block">
        </form>
    </div>
    <br>
    <div class="widthform">
        <h3>My debts:</h3>
        <table class="table table-sm">
            <thead>
            <tr>
                <th>User</th>
                <th>Debt</th>
            </tr>
            </thead>
            <c:forEach items="${creditors}" var="creditor">
                <tr>
                    <td>${creditor.key}</td>
                    <td>
                        <fmt:formatNumber maxFractionDigits="2"
                                          minFractionDigits="2">
                            ${creditor.value}
                        </fmt:formatNumber>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>
