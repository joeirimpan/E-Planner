<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>E-Tourism Planner</title>
</head>
<body>

<h2>Search</h2>
<form:form method="POST" action="/E-Planner/addConstraints" commandName="userconstraints" >
   	<table>
   
    <tr>
        <td><form:label path="destinationName">Destination Name</form:label></td>
        <td><form:input path="destinationName" /></td>
    </tr>
    
    <tr>
        <td><form:label path="ratingSelected">Rating Selected</form:label></td>
        <td><form:input path="ratingSelected" /></td>
    </tr>
    
    <tr>
        <td><form:label path="parkingSelected">Parking Selected</form:label></td>
        <td><form:input path="parkingSelected" /></td>
    </tr>
    
    <tr>
        <td><form:label path="board">Food Board</form:label></td>
        <td><form:input path="board" /></td>
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
        <td><form:input path="sPoolSelected" /></td>
    </tr>
    
    <tr>
        <td><form:label path="fitnessRoomSelected">Fitness Room</form:label></td>
        <td><form:input path="fitnessRoomSelected" /></td>
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