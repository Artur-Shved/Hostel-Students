<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html len="en">
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8"/>
    <link href="css/searchuser-style.css" type="text/css" rel="stylesheet"/>
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

<div class ="content">
<%--    <form:form method="post" action="/finderUser">--%>
<%--        <h2>Find Users by name or last name or room</h2>--%>
<%--        <table>--%>
<%--            <tr>--%>
<%--                <td class="text">Write there :</td>--%>
<%--                <td><input name="string" type="text" placeholder="Write there"  /></td>--%>
<%--            </tr>--%>

<%--            <tr>--%>
<%--                <td> </td>--%>
<%--                <td><input type="submit" value="Search" /></td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--        <h2>Find Users betweens room</h2>--%>
<%--        <table>--%>
<%--            <tr>--%>
<%--                <td class="text">Room from:</td>--%>
<%--                <td><input name="numberOne" type="number" placeholder="number from" />0</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td class="text">Room to:</td>--%>
<%--                <td><input name="numberTwo" type="number" placeholder="number to:" />0</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td> </td>--%>
<%--                <td><input type="submit" value="Search" /></td>--%>
<%--            </tr>--%>
<%--        </table>--%>

<%--        <h2>Find Users betweens date born</h2>--%>
<%--        <table>--%>
<%--            <tr>--%>
<%--                <td class="text">Write date from:</td>--%>
<%--                <td><input name="dateBorns" type="text" placeholder="date from:" /></td>--%>
<%--            </tr>--%>

<%--            <tr>--%>
<%--                <td class="text">Write date to:</td>--%>
<%--                <td><input name="dateBorns2" type="text" placeholder="Date to:" /></td>--%>
<%--            </tr>--%>


<%--            <tr>--%>
<%--                <td> </td>--%>
<%--                <td><input type="submit" value="Search" /></td>--%>
<%--            </tr>--%>
<%--        </table>--%>

<%--    </form:form>--%>

    <form:form method="get" action="/finderUser">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h2>Find Users by name or last name or room</h2>
        <table>
            <tr>
                <td class="text">Write there :</td>
                <td><form:input path="string"  /></td>
            </tr>

            <tr>
                <td> </td>
                <td><input type="submit" value="Search" /></td>
            </tr>
        </table>
        <h2>Find Users betweens room</h2>
        <table>
            <tr>
                <td class="text">Write number one</td>
                <td><form:input path="numberOne"/></td>
            </tr>
            <tr>
                <td class="text">Write number Two</td>
                <td><form:input path="numberTwo"/></td>
            </tr>
            <tr>
                <td> </td>
                <td><input type="submit" value="Search" /></td>
            </tr>
        </table>

        <h2>Find Users betweens date born</h2>
        <table>
            <tr>
                <td class="text">Write date from:</td>
                <td><form:input path="dateBorns" /></td>
            </tr>

            <tr>
                <td class="text">Write date to:</td>
                <td><form:input path="dateBorns2" /></td>
            </tr>


            <tr>
                <td> </td>
                <td><input type="submit" value="Search" /></td>
            </tr>
        </table>

    </form:form>
</div>
</body>
</html>