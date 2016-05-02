<%--
  Created by IntelliJ IDEA.
  User: Wojciech
  Date: 2016-05-02
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Wykład</title>
</head>
<body>
    <h3>${lecture.content}</h3>
    <form:form method="POST" action="/exercise">
    <button name="courseNumber" value="${lecture.courseId}" type="submit">Przejdź do testu</button>
    </form:form>
</body>
</html>
