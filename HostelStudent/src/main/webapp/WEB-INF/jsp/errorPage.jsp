<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html len = "en">
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8"/>
    <link href="/css/errorPage  .css" type="text/css" rel="stylesheet"/>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <c:url value="monkey.jpg" var="img"/>
    <title>Hello World</title>
</head>
<body style="background-image: url('${img}');">
<div id = "header">
    <ul align="center">
        <li><a href = "/">Home</a></li>
        <li><a href = "/alluser">All Students</a></li>
        <li><a href="/searchuser">Search Student</a></li>
        <sec:authorize access="hasRole('SUPER_ADMIN')">
            <li><a href="/addnewuser">Add new Students</a></li>
        </sec:authorize>
        <div id = "button">
            <form:form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Sign Out">
            </form:form>
        </div>
    </ul>
</div>
<h1 align="center">You can't do it!!!!!</h1>
</body>