<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Output</title>
</head>
<body>

<h2>Result</h2>
   <table>
    <tr>
        <td>Destination Name</td>
        <td>${userconstraints.destinationName}</td>
    </tr>
    
    <tr>
        <td>Selected Rating</td>
        <td>${userconstraints.ratingSelected}</td>
    </tr>
    
    <tr>
        <td>Parking Selected</td>
        <td>${userconstraints.parkingSelected}</td>
    </tr>
    
    <tr>
        <td>Food Board</td>
        <td>${userconstraints.board}</td>
    </tr>
    
    <tr>
    	<td>Activities</td>
    	<td>${userconstraints.activities}</td>
    	<%-- <c:forEach items="${userconstraints.activities}" var="activity">
    	<tr>
      	<td><c:out value="${activity}" /></td>
    	</tr>
  		</c:forEach> --%>
  	</tr>
    
    <tr>
        <td>Swimming Pool</td>
        <td>${userconstraints.sPoolSelected}</td>
    </tr>
    
    <tr>
        <td>Fitness Room</td>
        <td>${userconstraints.fitnessRoomSelected}</td>
    </tr>
	</table>  
</body>
</html>