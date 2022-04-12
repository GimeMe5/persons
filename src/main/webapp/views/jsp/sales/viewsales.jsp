<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Sale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>View Sales</title>
</head>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h1>Sales List</h1>


<table border="2">
    <tr>
        <th>Id</th>
        <th>Count</th>
        <th>Good Id</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <%
        List<Sale> sales = (ArrayList<Sale>) request.getAttribute("list");
        for (Sale sale : sales) {%>
    <tr>
    <td><%=sale.getId()%></td>
    <td><%=sale.getCount()%></td>
    <td><%=sale.getGoodId()%></td>
        <td>
            <form action="/editsale" method="post">
                <input type="hidden" name="id" value="<%=sale.getId()%>"><input type="submit" value="Edit">
            </form>
        </td>

        <td>
            <form action="/deletesale" method="post">
                <input type="hidden" name="id" value="<%=sale.getId()%>"><input type="submit" value="Delete">
            </form>
        </td>
    </tr>
       <% }
    %>
</table>
<br/><a href="/newsale">Add New Sale</a>
<br/><a href="../../../index.jsp">Main Page</a>

</body>
</html>
