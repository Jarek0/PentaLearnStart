<%--
  Created by IntelliJ IDEA.
  User: Wojciech
  Date: 2016-04-30
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>${courseName}</h1>
    <h2>${courseDescription}</h2>

    <c:forEach var="chapter" items="${chapters}">
        <h3> chapter: ---------------------</h3>
        <form:form method="POST" action="/lecture">
            <button name="chapterNumber" value='${chapter.lectureId}' type="submit">Nauka</button>
        </form:form>
        <form:form method="POST" action="/exercise">
            <button name="chapterNumber" value='${chapter.exerciseId}' type="submit">Rozpocznij Test</button>
        </form:form>
    </c:forEach>
</body>
</html>
