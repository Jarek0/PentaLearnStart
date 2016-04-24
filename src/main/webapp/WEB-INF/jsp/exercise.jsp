<%--
  Created by IntelliJ IDEA.
  User: Wojciech
  Date: 2016-04-24
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <title>Sprawdzian</title>
    <script src="/resources/exercise.js"></script>

</head>
<body>
    <h2>${title}</h2> <br/>
    <input type="number" id="liczba" readonly>
    <button id="pokaz" onclick="pokaz();" >Pokaz liczbe</button> <br/>
    <button id="checkbox" onclick="hide();">Schowaj</button> <br/><br/><br/>

<form:form method="POST" action="/exerciseResult">
    <ol>
        <c:forEach var="task" items="${tasks}">
            <li>${task.question}</li>
                <c:forEach var="answer" items="${task.answers}">
                    <input type="checkbox" name="${task.id}" value="${answer.id}"> ${answer.text} <br/>
                </c:forEach>
        </c:forEach>
    </ol>
    <br/><br/>
    <input type="submit" value="sprawdź">
</form:form>
</body>
</html>
