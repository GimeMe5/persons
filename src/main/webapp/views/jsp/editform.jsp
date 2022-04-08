<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Form</title>
</head>
<body>

<%@ page import="model.Good" %>
<%@ page import="dao.GoodDao" %>

<%
    String id=request.getParameter("id");
    Good g= GoodDao.getInstance().find(Integer.parseInt(id)).get();
%>

<h1>Edit Form</h1>
<form action="editgood.jsp" method="post">
    <input type="hidden" name="id" value="<%=g.getId() %>"/>
    <table>
        <tr><td>Name:</td><td>
            <input type="text" name="name" value="<%= g.getName()%>"/></td></tr>
        <tr><td>Price:</td><td>
            <input type="text" name="price" value="<%= g.getPrice()%>"/></td></tr>

        <tr><td colspan="2"><input type="submit" value="Edit Good"/></td></tr>
    </table>
</form>

</body>
</html>