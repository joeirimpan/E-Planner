<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="com.planit.util.KIMClientConstants"%>
<%@ page import="com.hp.hpl.jena.rdf.model.impl.ResourceImpl"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View destinations</title>
<%--<link rel="stylesheet" href="resources/default.css" type="text/css"> --%>
<script language="javascript">

</script>
</head>
<body>
<div class="wrapper">
	<div id="mainContent">
	<h3>View destinations</h3>
	<form:form method="POST" action="/Form/deleteDestinations">
	<div class="message">Selected kb is: <%=request.getSession().getAttribute("kbModel")!=null?"kbName":""%>
    </div>
    	<%List list = (ArrayList) request.getAttribute("resultsList");		
			if (list.size() > 0) {%>
	<table class="mainTable">
	    <tr>
			<th>View resource</th>
			<th>View/Edit</th>
			<th>Delete</th>
		</tr>
		<!-- list of folders -->
	
	
		<!-- list of files -->
		<%
		Iterator i = list.iterator();
		while (i.hasNext()) {
			  Object result = (Object) i.next();
			  ResourceImpl r = (ResourceImpl) result;
			   String re = null;
			   if (result!=null)
		  	   re = URLEncoder.encode(result.toString(), KIMClientConstants.DEFAULT_ENCODING);
        %>
		<tr>
	 <td><a href="showDetails.do?resourceURI=<%=re%>">view resource</a></td>
			<td><a href="/travelguides/updateDestination.do?resourceURI=<%=re%>" ><%=r.getLocalName()%></a></td>
			<td class="right">
			<form:checkbox path="resourceURI" value="<%=re%>" /></td>
		</tr>
		<%}%>
		<tr>
			<td colspan="2">&nbsp;</td>
			<td><input class="btn" type="submit" value="Delete" /></td>
		</tr>
	</table>
	<%} else if (list.size() == 0) {%><div class="redtext">Kb <span class="message"><%="kbName"%></span> is empty.</div><%}%>
	</form:form>

	</div>
</div>
</body>
</html>