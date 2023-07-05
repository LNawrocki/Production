<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista zamówień</title>
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
</head>
<body>
<header class="header">
    <div class="name"><h1>Lightnet</h1><br>
        <h1>Production plan</h1>
    </div>
    <div class="info-adress">
        <div class="info-contact">
            <h1>Adres:</h1>
            <h1>Telefon:</h1>
            <h1>E-mail:</h1>
        </div>

        <div class="info-contact">
            <h1>Kobiernice</h1>
            <h1>.............</h1>
            <h1>.............</h1>
        </div>
    </div>

    <div class="info-contact">
        <div><h1><a href="#">####</a></h1></div>
        <div><h1><a href="#">####</a></h1></div>
        <div><h1><a href="#">####</a></h1></div>
    </div>
</header>
<div class="main-menu-block">
    <div class="menu-links">
        <h1><a href="">Lista zamówień</a></h1>
    </div>

    <div class="menu-links">
        <h1><a href="">Dodaj zamówienie</a></h1>
    </div>

    <div class="menu-links">
        <h1><a href="">Edytuj zamówienie</a></h1>
    </div>

</div>


<table border="1px">
    <tr>
        <td>ID</td>
        <td>Order</td>
        <td>Client:</td>
        <td>Agent:</td>
        <td>Del.-Date:</td>
        <td>Quality:</td>
        <td>Country:</td>
        <td>Delivery:</td>
        <td>Final Dest.:</td>
        <td>Additional info</td>
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
    </tr>
    </c:forEach>
</table>




</body>
</html>