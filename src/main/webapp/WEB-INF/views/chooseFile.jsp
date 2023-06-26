<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="/showFile" method="GET">
  <select name="fileName">
    <c:forEach var="file" items="${fileList}">
      <option>${file}</option>
    </c:forEach>
  </select>

  <button type="submit">wybierz</button>
</form>

</body>
</html>