<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj zamówienie</title>
</head>
<body>

<%--<form action="/order/add" method="post">--%>
<%--    <select name="file">--%>
<%--        <c:forEach items="${fileList}" var="file">--%>
<%--            <option>${file}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
<%--    <button type="submit">Dodaj</button>--%>
<%--</form>--%>

<form action="/order/add" method="post">
    <select name="files" multiple="multiple" size="50">
        <c:forEach items="${fileList}" var="file">
            <option>${file}</option>
        </c:forEach>

    </select>
    <button type="submit">Dodaj</button>
</form>
</body>
</html>
