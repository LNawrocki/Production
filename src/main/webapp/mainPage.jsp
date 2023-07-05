<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista zamówień</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"/>
</head>
<body>
<header class="header">
    <div class="name"><h1>Łukasz Nawrocki</h1><br>
        <h1>UsersCRUD application</h1>
    </div>
    <div class="info-adress">
        <div class="info-contact">
            <h1>Adres:</h1>
            <h1>Telefon:</h1>
            <h1>E-mail:</h1>
        </div>

        <div class="info-contact">
            <h1>Bielsko-Biała</h1>
            <h1>.............</h1>
            <h1>.............</h1>
        </div>
    </div>

    <div class="info-contact">
        <div><h1><a href="#">Linkedin</a></h1></div>
        <div><h1><a href="#">Pracuj.pl</a></h1></div>
        <div><h1><a href="#">Github</a></h1></div>
    </div>

</header>


<table border="1">
    <tr>
        <td>Order</td>
        <td>ID</td>
        <td>Client:</td>
        <td>Agent:</td>
        <td>Quality:</td>
        <td>Country:</td>
        <td>Delivery:</td>
        <td>Final Dest.:</td>
        <td>Additional info</td>
    </tr>
    <c:forEach var="order" items="${ordersList}">
    <tr>
        <td>${order.getOrderNumber()}</td>
        <td>${order.getOrderId()}</td>
        <td>${order.getClient()}</td>
        <td>${order.getAgent()}</td>
        <td>${order.getQuality()}</td>
        <td>${order.getCountry()}</td>
        <td>${order.getDeliveryDate()}</td>
        <td>${order.getFinalDest()}</td>
        <td>${order.getAdditionalInformation()}</td>
    </tr>
    </c:forEach>
</table>


</body>
</html>