<%--
  Created by IntelliJ IDEA.
  User: pglg
  Date: 25-04-2016
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h1><spring:message code="question.category.list"/></h1>
<ul>
    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach items="${qcategories}" var="qcategory">
        <li>
            <c:out value="${qcategory.getName()}"/>
        </li>
    </c:forEach>
</ul>

<a href="<spring:url value="/question_category_create.html" />"><spring:message code="question.category.create"/></a>
</body>
</html>