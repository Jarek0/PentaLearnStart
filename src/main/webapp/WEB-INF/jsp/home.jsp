<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pentalearn strona główna</title>
</head>

<body>
<h1>Witamy w PentaLearn</h1> <br/>
<form:form method="POST" action="/addPerson">
    <table>
        <tr>
            <td> <form:label path="name">Podaj swoje imię</form:label> </td>
            <td> <form:input path="name"/> </td>
        </tr>
        <tr>
            <td> <form:label path="id">Podaj swoje ID</form:label> </td>
            <td> <form:input path="id"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Wyślij"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
