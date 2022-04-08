<%@page import="dao.GoodDao"%>
<jsp:useBean id="g" class="model.Good"></jsp:useBean>
<jsp:setProperty property="*" name="g"/>
<%
    GoodDao.getInstance().delete(g);
    response.sendRedirect("viewgoods.jsp");
%>