<%@ page import="java.util.List" %>
<%@ page import="model.Good" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>View Users</title>
</head>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h1>Goods List</h1>


<table border="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <%
        List<Good> goodList = (ArrayList<Good>) request.getAttribute("list");
        for (Good good : goodList) {%>
    <tr>
    <td><%=good.getId()%></td>
    <td><%=good.getName()%></td>
    <td><%=good.getPrice()%></td>
        <td>
            <form action="/editgood" method="post">
                <input type="hidden" name="id" value="<%=good.getId()%>"><input type="submit" value="Edit">
            </form>
        </td>

        <td>
            <form action="/deletegood" method="post">
                <input type="hidden" name="id" value="<%=good.getId()%>"><input type="submit" value="Delete">
            </form>
        </td>
    </tr>
       <% }
    %>
</table>
<br/><a href="/newgood">Add New Good</a>
<br/><a href="../../../index.jsp">Main Page</a>
</body>
</html>
