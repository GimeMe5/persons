<%@ page import="dao.SalesDao" %>
<jsp:useBean id="s" class="model.Sale"></jsp:useBean>
<jsp:setProperty property="*" name="g"/>
<%
    boolean i= SalesDao.getInstance().update(s);
    response.sendRedirect("/listsales");
%>