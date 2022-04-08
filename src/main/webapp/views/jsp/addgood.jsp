<%@page import="dao.GoodDao" %>
<%@ page import="java.sql.SQLException" %>
<jsp:useBean id="g" class="model.Good"></jsp:useBean>
<%--<jsp:setProperty property="*" name="g"/>--%>

<%
    boolean i = false;
    try {
        i = GoodDao.getInstance().save(g);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    if (i) {
        response.sendRedirect("addgood-success.jsp");
    } else {
        response.sendRedirect("addgood-error.jsp");
    }
%>