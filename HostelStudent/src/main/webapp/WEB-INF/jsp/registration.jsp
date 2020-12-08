<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html len="en">
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8"/>
    <link href="/css/registration-style.css" type="text/css" rel="stylesheet"/>

    <title>Hello World</title>
</head>


<body>
<div id = "header">
    <ul align="center">
        <li><a href = "/">Home</a></li>
        <sec:authorize access="isAuthenticated()">
        <li><a href = "/alluser">All Students</a></li>
        <li><a href="/searchuser">Search Student</a></li>
        <div id = "button">
            <form:form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Sign Out">
            </form:form>
        </div>
        </sec:authorize>
    </ul>

</div>
<div id="registrationForm">
    <form:form action="/registration" method="post" modelAttribute="user">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div id="content">
        <h2>${message}</h2>
        <div> <label>User Login:</label></div>
        <div> <label><form:input type="text" path="username"/> </label></div>
        <div><label>User Password:</label></div>
        <div><label> <form:input type="password" path="password"/> </label></div>
        <div><label>Name:</label></div>
        <div><label> <form:input type="text" path="name"/> </label></div>
        <div><label>Last Name:</label></div>
        <div><label> <form:input type="text" path="lastname"/> </label></div>
        <div><label>Room Number:</label></div>
        <div><label> <form:input type="text" path="room"/> </label></div>
        <div><label>Date of born:</label></div>
        <div><label><form:input type="text" path="date"/> </label></div>
        <div><input type="submit" value="Sign In"/></div>
    </div>
    </form:form>
</div>

</body>
</html>