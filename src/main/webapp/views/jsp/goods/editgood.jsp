<%@page import="dao.GoodDao"%>
<jsp:useBean id="g" class="model.Good"></jsp:useBean>
<jsp:setProperty property="*" name="g"/>
<%
    boolean i=GoodDao.getInstance().update(g);
    response.sendRedirect("/list");
%>