<%--
  Created by IntelliJ IDEA.
  User: Wojciech
  Date: 2016-04-30
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Lista kursów</title>
</head>
<body>
    <h2>Dostępne kursy: </h2>
    <form:form method="POST" action="/course/showChapters/">
        <c:forEach var="course" items="${courses}">
            <h3>${course.name}</h3>
            ${course.description} <br/>
            <button name="courseNumber" value="${course.id}" type="submit">Przejdź do kursu</button>
        </c:forEach>
    </form:form>
</body>
</html>
