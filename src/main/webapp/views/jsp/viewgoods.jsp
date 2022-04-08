<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Users</title>
</head>
<body>

<%@page import="dao.GoodDao,model.Good,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Goods List</h1>

<%
    List<Good> list=GoodDao.getInstance().findAll();
    request.setAttribute("list",list);
%>

<table border="1" width="90%">
    <tr><th>Id</th><th>Name</th><th>Price</th><th>Edit</th><th>Delete</th></tr>
    <c:forEach items="${list}" var="g">
        <tr><td>${g.id}</td><td>${g.name}</td><td>${g.price}</td>
            <td><a href="editform.jsp?id=${g.id}">Edit</a></td>
            <td><a href="deletegood.jsp?id=${g.id}">Delete</a></td></tr>
    </c:forEach>
</table>
<br/><a href="addgoodform.jsp">Add New Good</a>

</body>
</html>