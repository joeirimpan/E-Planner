<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>E-Tourism Planner</title>
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" >
	
</head>
<body>

<h1>Search For Destinations</h1>
<form:form method="POST" action="/E-Planner/addConstraints" commandName="userconstraints" >
   	<table>
   
    <tr>
        <td><form:label path="destinationName">Destination Name</form:label></td>
        <td><form:input path="destinationName" /></td>
    </tr>
    
    <tr>
        <td><form:label path="ratingSelected">Rating Selected</form:label></td>
        <td><form:radiobutton path="ratingSelected" value="1"/>1 Star 
			<form:radiobutton path="ratingSelected" value="2"/>2 Star
			<form:radiobutton path="ratingSelected" value="3"/>3 Star
			<form:radiobutton path="ratingSelected" value="4"/>4 Star
			<form:radiobutton path="ratingSelected" value="5"/>5 Star</td>
    </tr>
    
    <tr>
        <td><form:label path="parkingSelected">Parking Selected</form:label></td>
        <td><form:checkbox path="parkingSelected" /></td>
    </tr>
    
    <tr>
        <td><form:label path="board">Food Board</form:label></td>
        <td>
        <form:radiobutton path="board" value="fullboard"/>Full Board
			<form:radiobutton path="board" value="halfboard"/>Half Board
			<form:radiobutton path="board" value="allinclusive"/>All Inclusive
</td>
    </tr>
    
    <tr>
   		<td><form:label path="activities">Activities</form:label></td>
    	<td>
    	<form:select path="activities" multiple="true">
             <form:option value="hiking">Hiking</form:option>
             <form:option value="surfing">Surfing</form:option>
             <form:option value="skiing">Skiing</form:option>
             <form:option value="shopping">Shopping</form:option>
             <form:option value="sightseeing">Sight Seeing</form:option>
        </form:select>
        </td>
	</tr>
   
    <tr>
        <td><form:label path="sPoolSelected">Swimming Pool</form:label></td>
        <td><form:checkbox path="sPoolSelected" /></td>
    </tr>
    
    <tr>
        <td><form:label path="fitnessRoomSelected">Fitness Room</form:label></td>
        <td><form:checkbox path="fitnessRoomSelected" /></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
    
	</table>  
</form:form>
</body>
</html>