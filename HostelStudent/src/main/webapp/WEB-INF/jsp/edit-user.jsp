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
<div id="registrationForm">
    <div id="content">
        <h2>Edit Student:</h2>
        <form:form method="PUT" action="/editsave">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div><label><form:hidden  path="id" /></label></div>
        <div><label><form:hidden path="username"/></label></div>
        <div><label><form:hidden path="password"/></label></div>
        <div><label><form:hidden path="role"/></label></div>
        <div><label>Name:</label></div>
        <div><label><form:input type="text" path="name"/> </label></div>
        <div><label>Last Name:</label></div>
        <div><label><form:input type="text" path="lastname"/> </label></div>
        <div><label>Room Number:</label></div>
        <div><label> <form:input type="text" path="room"/> </label></div>
        <div><label>Date of born:</label></div>
        <div><label><form:input type="text" path="date"/> </label></div>
        <sec:authorize access="hasRole('SUPER_ADMIN')">
            <div id="message-style" style="color:red" > ${message} </div>
        <c:forEach var="role" items="${role}">
            <c:forEach var="roles" items="${roles}">
                <div><label><input type="checkbox" name="checkbox" value="${role}" <c:if test="${role == roles}"> checked="checked" </c:if>/> ${role}&nbsp;</label></div>
<%--            <c:forEach var="roles" items="${userRole}">    --%>
<%--            <c:if test="${role == roles}"> checked="checked" </c:if>--%>
<%--            </c:forEach>--%>
        </c:forEach>
        </c:forEach>
        </sec:authorize>
        <div><input type="submit" value="Sign In"/></div>
    </div>
</div>
</form:form>
</body>
</html>