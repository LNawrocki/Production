<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wyświetl użytkowników</title>
    <link rel="stylesheet" href="/css/style.css"/>
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
    </div>
    </div>
</header>

<section class="info">
    <section class="info-block" style="text-align: center">
        <p>Lista użytkowników</p>
    </section>
    <section class="info-block">
        <TABLE style="text-align: center; align-self: center" cellpadding="5"
               cellspacing="10" >
            <TR>
                <TD>ID</TD>
                <TD>Nazwa użytkownika</TD>
                <TD>Email</TD>
                <TD>Akcja</TD>
            </TR>
            <c:forEach var="user" items="${users}">
                <TR>
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="/user/delete?userID=${user.id}">Usuń</a>
                        <a href="/user/edit?userID=${user.id}">Edytuj</a>
                        <a href="/user/show?userID=${user.id}">Pokaż</a>
                    </td>
                </TR>
            </c:forEach>

        </TABLE>
    </section>
    <section class="info-block" style="text-align: center">
        <p><a href="/user/add">Dodaj użytkownika</a></p>
    </section>
</section>

</body>
</html>
