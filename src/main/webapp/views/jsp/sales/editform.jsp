<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Form</title>
</head>
<body>

<%@ page import="model.Sale" %>
<%@ page import="dao.SalesDao" %>

<%
    String id=request.getParameter("id");
    Sale s = SalesDao.getInstance().find(Integer.parseInt(id)).get();
%>

<h1>Edit Form</h1>
<form action="/updatesale" method="post">
    <input type="hidden" name="id" value="<%=s.getId() %>"/>
    <table>

        <tr><td>Count:</td><td>
            <input type="text" name="count" value="<%= s.getCount()%>"/></td></tr>
        <tr><td>Good Id:</td><td>
            <input type="text" name="goodId" value="<%= s.getGoodId()%>"/></td></tr>
        <tr><td colspan="2"><input type="submit" value="Edit Sale"/></td></tr>
    </table>
</form>

</body>
</html>