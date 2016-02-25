<%@ tag body-content="empty" %>
<%@ attribute name="var" required="true" type="java.lang.String" %>
<%@ attribute name="value" required="true" type="java.sql.Clob" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag import="java.sql.*" %>
<%@ tag import="javax.servlet.jsp.*" %>
<%
    String result;
    try {
        result = value.getSubString(1, new Long(value.length()).intValue());
    } catch (SQLException e) {
        e.printStackTrace();
        result = null;
    }
    jspContext.setAttribute(var, result, PageContext.REQUEST_SCOPE);
%>