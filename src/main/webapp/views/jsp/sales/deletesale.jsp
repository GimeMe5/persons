
<%@ page import="dao.SalesDao" %>
<jsp:useBean id="s" class="model.Sale"></jsp:useBean>
<jsp:setProperty property="*" name="g"/>
<%
    SalesDao.getInstance().delete(s);
    response.sendRedirect("/listsale");
%>