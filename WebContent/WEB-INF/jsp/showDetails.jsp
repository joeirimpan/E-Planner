<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Iterator"%>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<html>
<head>
</head>
<body>
<div class="wrapper">
    <div id="mainContent">
	<div class="center teal-text"><h3>Show Details Page</h3></div>
    <table class="mainTable">
    <% if (request.getAttribute("resource") != null) { %>
    <tr><th>Object:</th>
        <th><%=(String) request.getAttribute("resource") %></th>
    </tr>
    <tr><td>Attribute:</td>
        <td>Value:</td>
    </tr>
    <% } else {%>
    <tr colspan="2"><td>Error! Resource not found!</td></tr>
    <% } %>

    <% if (request.getAttribute("result") != null) {
       HashMap result = (HashMap) request.getAttribute("result");  
       Set keySet = result.keySet();
       Iterator it = keySet.iterator();
       while (it.hasNext()) {
    	   String property = (String) it.next();
    	   String object = (String) result.get(property);
    	   %>
    	   <tr>
    	   <td><%=property%>
    	   </td>
    	   <td><%=object%>
    	   </td>
    	   </tr>
    	   <%
       }
       } %>
    </table>  
    </div>
</div>
</body>
</html>