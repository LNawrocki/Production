<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index.jsp</title>
</head>
<body>
<table border="1">
    <tr><td>Order</td><td>${order.getOrderNumber()}</td><td>ID</td><td>${order.getOrderId()}</td></tr>
    <tr><td>Client:</td><td>${order.getClient()}</td></tr>
    <tr><td>Agent:</td>${order.getAgent()}<td></td><td>Del.-Date:</td><td>${order.getDeliveryDate()}</td></tr>
    <tr><td>Quality:</td><td>${order.getQuality()}</td><td>Country:</td><td>${order.getCountry()}</td></tr>
    <tr><td>Delivery:</td><td>${order.getDeliveryDate()}</td><td>Final Dest.:</td><td>${order.getFinalDest()}</td></tr>
    <tr><td>Additional info</td><td>${order.getAdditionalInfo()}</td></tr>
    <tr></tr>
    <tr><td>Pos.</td><td>article code</td><td>pcs</td><td>unit</td><td>Additional information</td><td>Barcode</td></tr>
    <tr><td>${pos.getPos}</td><td>${pos.getArticleCode()}</td><td>${pos.getPcs()}</td><td>${pos.getUnit()}</td><td>${pos.getAdditionalInformation()}</td></tr>



</table>



</body>
</html>