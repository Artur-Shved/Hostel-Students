<!DOCTYPE html>
<html len = "en">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8"/>
    <link href="/css/addnewstudents.css" type="text/css" rel="stylesheet"/>
    <title>Hello World</title>
</head>
<body>
<div id = "header">
    <ul align="center">
        <li><a href = "/">Home</a></li>
        <li><a href = "/alluser">All Students</a></li>
        <li><a href = "/addnewuser">Add new students</a></li>
        <li><a href="/searchuser">Search user</a></li>
    </ul>
</div>

<p>Please try again(<span class = "bar">User with this name and last name alredy created</span>)</p>
<div class="content">
<form:form method="post" action="save">
    <table >
        <tr>
            <td class="text">Name : </td>
            <td><form:input path="name"  /></td>
        </tr>
        <tr>
            <td class="text">Last Name :</td>
            <td><form:input path="lastname" /></td>
        </tr>
        <tr>
            <td class="text">Number of Room :</td>
            <td><form:input path="room" /></td>
        </tr>
        <tr>
            <td class="text">Date of Born :</td>
            <td><form:input path="date" /></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form:form>
</div>
</body>
</html>