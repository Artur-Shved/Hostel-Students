<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html len = "en">
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8"/>
    <link href="/css/allstudents-style.css" type="text/css" rel="stylesheet"/>
    <title>Hello World</title>
</head>


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

<div id = "myUsers">
    <table id = "table">
        <tr><th>Id</th><th>Name</th><th>lastname</th><th>room</th><th>Born day</th><sec:authorize access="hasAnyRole('ADMIN','SUPER_ADMIN')"><th>Role</th><th>Edit</th></sec:authorize><sec:authorize access="hasRole('SUPER_ADMIN')"> <th>Delete</th></sec:authorize></tr>
        <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.lastname}</td>
            <td>${user.room}</td>
            <td>${user.date}</td>
            <sec:authorize access="hasAnyRole('ADMIN', 'SUPER_ADMIN')">
                <td>${user.role}</td>
                <td><a href="editemp/${user.id}">Edit</a></td>
            </sec:authorize>
            <sec:authorize access="hasRole('SUPER_ADMIN')">
                <td><a href="delete/${user.id}">Delete</a></td>
            </sec:authorize>

        </tr>

        </c:forEach>
    </table>
    <div id="searchuser">
        <table>
            <tr>

                <form:form method="get" action="/finduser" modelAttribute="dates">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <td>Search user</td>
                    <td><input name="name" type="text" placeholder="name"/></td>
                    <td><input name="lastName" type="text" placeholder="lastname"/></td>
                    <td><form:input path="room" type="text" placeholder="room"/></td>
                    <td><input name="dateBorns" type="date" placeholder="date_born"/></td>
                    <td><input type="submit" value="Find" /></td>

                </form:form>


            </tr>
        </table>
    </div>
    <br/>
</div>
</html>