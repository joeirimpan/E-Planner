<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>E-Tourism Planner</title>
    <link href="${pageContext.request.contextPath}/resources/css/materialize.css" rel="stylesheet" >
</head>
<body>

<div class="card-panel light-green lighten-2 center"><h2>Search For Destinations</h2></div>
<form:form method="POST" action="/E-Planner/addConstraints" commandName="userconstraints" >

<div class="row">
<div class="col m3 white-text">a</div>
<div class="col m6 card-panel">
   	<table>   
    <tr>
        <td><form:label path="destinationName"><h4>Destination Name</h4></form:label></td>
        <td><form:input path="destinationName" /></td>
    </tr>
    
    <tr>
        <td><form:label path="ratingSelected">Rating Selected</form:label></td>
        <td>
        <form:select path="ratingSelected">
             <form:option value="one">one star rating</form:option>
             <form:option value="two">two star rating</form:option>
             <form:option value="three">three star rating</form:option>
             <form:option value="four">four star rating</form:option>
             <form:option value="five">five star rating</form:option>
        </form:select>
        
        </td>
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
        <td><form:input type="checkbox" path="fitnessRoomSelected" /></td>
    </tr>
    
    <tr>
        <td><form:label path="transport">Transport Service</form:label></td>
        <td><form:input path="transport" /></td>
    </tr>
    
	</table>  
	<div class="row">
	<div class="col m5 white-text">a</div>
	<div class="col m2"><input class="btn waves-effect waves-light light-green" type="submit" value="Submit"/></div>
	<div class="col m5"></div>
	</div>
	
	
	<br>

</div>
<div class="col m3></div>
</div>

</form:form>
</body>
</html>