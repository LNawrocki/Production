<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista zamówień</title>
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>

</head>
<body>

<table>
    <tr>
        <td style="width: fit-content">ID</td>
        <td style="width: fit-content">Order</td>
        <td style="width: fit-content">Client:</td>
        <td style="width: fit-content">Agent:</td>
        <td style="width: fit-content">Del.-Date:</td>
        <td style="width: fit-content">Quality:</td>
        <td style="width: fit-content">Country:</td>
        <td style="width: fit-content">Delivery:</td>
        <td style="width: fit-content">Final Dest.:</td>
        <td style="width: fit-content">Additional info</td>
        <td style="width: fit-content">Pos table name</td>
        <td style="width: fit-content">Order date</td>
        <td style="width: fit-content">Order number</td>
    </tr>
    <c:forEach var="order" items="${ordersList}">
    <tr>
        <td>${order.getOrderId()}</td>
        <td>${order.getOrderNumber()}</td>
        <td>${order.getClient()}</td>
        <td>${order.getAgent()}</td>
        <td>${order.getDeliveryDate()}</td>
        <td>${order.getQuality()}</td>
        <td>${order.getCountry()}</td>
        <td>${order.getDeliveryType()}</td>
        <td>${order.getFinalDest()}</td>
        <td>${order.getAdditionalInfo()}</td>
        <td>${order.getPos()}</td>
        <td>${order.getOrderDate()}</td>
        <td>${order.getOrderNo()}</td>
    </tr>
    </c:forEach>
</table>

<p><a href="/order/add">Dodaj zamówienie</a></p>
<p><a href="/main">Menu główne</a></p>
<p><a href="/order/clear">Wyczyść tablice</a></p>
</body>
</html>