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
    <title>Add User Form</title>
</head>
<body>

<a href="viewgoods.jsp">View All Goods</a><br/>

<h1>Add New Good</h1>
<form action="/insert" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td>
                <input type="text" name="price"/></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="Add Good"/></td>
        </tr>
    </table>
</form>

</body>
</html>
