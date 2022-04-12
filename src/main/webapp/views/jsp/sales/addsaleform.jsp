<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.04.2022
  Time: 1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Sale Form</title>
</head>
<body>

<a href="/listsales">View All Sales</a><br/>

<h1>Add New Sale</h1>
<form action="/insertsale" method="post">
    <table>
        <tr>
            <td>Id:</td>
            <td><input type="text" name="id"/></td>
        </tr>
        <tr>
            <td>Count:</td>
            <td><input type="text" name="count"/></td>
        </tr>
        <tr>
            <td>Good Id:</td>
            <td>
                <input type="text" name="goodId"/></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="Add Sale"/></td>
        </tr>
    </table>
</form>

</body>
</html>
