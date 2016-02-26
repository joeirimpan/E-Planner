<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Insert New Destination</h2>
<form:form method="POST" action="/Form/addStudent">
   <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="age">Destination URL</form:label></td>
        <td><form:input path="age" /></td>
    </tr>
    
    <tr><th colspan="2">Accommodation data:</th></tr>
    
        <tr><td colspan="2">
        <table class="mainTable">
               <tr>
               <td><form:label path="accomodationType">Select Type:</form:label></td>
               <td>
               <form:select path="accomodationType">
               <form:option value="hotel">Hotel</form:option>
               </form:select>
               </td>
               
               <td><form:label path="nam">name</form:label></td>
               <td><form:input path="nam" /></td>
               
               <td><form:label path="ratingSelected">Select Rating:</form:label></td>
               <td><form:select path="ratingSelected">
               <form:option value='1' label="one star rating"/>	
                 <form:option value='2' label="two star rating"/>
                   <form:option value='3' label="three star rating"/>
                     <form:option value='4' label="four star rating"/>
                       <form:option value='5' label="five star rating"/>
               </form:select>
               </td>
           
               
               <td colspan="2"></td></tr>
               <tr><td colspan="2"></td></tr>
               <tr>
               <td colspan="2"><form:label path="parkingSelected">Please provide the information if the hotel has its own parking place:</form:label></td>
               <td><form:select path="parkingSelected">
               <form:option value="yes">yes</form:option>
               <form:option value="no">no</form:option>
               </form:select></td></tr>
               
               <tr><td colspan="2"><form:label path="sPoolSelected">Please provide the information if the hotel has its own swimming-pool:</form:label></td>
               <td><form:select path="sPoolSelected">
               <form:option value="yes">yes</form:option>
               <form:option value="no">no</form:option>
               </form:select></td></tr>
               
              <tr><td colspan="2"><form:label path="fitnessRoomSelected">Please provide the information if the hotel has its own fitness room:</form:label></td>
               <td><form:select path="fitnessRoomSelected">
               <form:option value="yes">yes</form:option>
               <form:option value="no">no</form:option>
               </form:select></td></tr>
               
           <tr><td colspan="2"><form:label path="board">Please provide the information if the hotel provides food service:</form:label></td>
               <td><form:select path="board" multiple="multiple">
               <form:option value="1">all-inclusive</form:option>
               <form:option value="2">half-board</form:option>
               <form:option value="3">full-board</form:option>
               <form:option value="4">breakfast</form:option>
               </form:select></td></tr>
              
          </table></td>
        </tr> 
        
         <tr><th colspan="2">Activities Close To The Destination:</th></tr>
         <tr><td colspan="2">
         <table class="mainTable">
               <tr><td><form:checkbox path="hiking" value="Hiking"/>Hiking</td>
               <td><form:label path="hikingName">Please insert name of the place for hiking:</form:label></td>
             <td><form:input path="hikingName"/></td></tr>
              <tr><td><form:checkbox path="sightseeing" value="Sightseeing"/>Sightseeing</td>
               <td><form:label path="sightseeingName">Please insert name of the place for sightseeing:</form:label></td>
               <td><form:input path="sightseeingName" type="textarea"/></td></tr>
               <tr><td><form:checkbox path="surfing" value="Surfing"/>Surfing</td>
               <td><form:label path="surfingName">Please insert name of the place for surfing:</form:label></td>
               <td><form:input path="surfingName" type="textarea"/></td></tr>
               <tr><td><form:checkbox path="skiing" value="Skiing"/>Skiing</td>
               <td><form:label path="skiingName">Please insert name of the place for skiing:</form:label></td>
               <td><form:input path="skiingName" type="textarea"/></td></tr>
               <tr><td><form:checkbox path="shopping" value="Shopping"/>Shopping</td>
               <td><form:label path="shoppingName">Please insert name of the place for shopping:</form:label></td>
               <td><form:input path="shoppingName" type="textarea"/></td></tr>
        </table>
         </td></tr>
         
         
        <tr><th colspan="2">Transportation offered for this destination:</th></tr>
        <tr><td colspan="2">
        <table>
               <tr><td><form:checkbox path="bus" value="Bus"/>Bus</td>
               <td>Please insert name of the transport service:</td>
               <td><form:input path="busName" type="textarea"/></td></tr>
               <tr><td><form:checkbox path="train" value="Train"/>Train</td>
               <td>Please insert name of the transport service:</td>
               <td><form:input path="trainName"  type="textarea"/></td></tr>
               <tr><td><form:checkbox path="airplane" value="Airplane"/>Airplane</td>
               <td>Please insert name of the transport service:</td>
               <td><form:input path="airplaneName"  type="textarea"/></td></tr>
               <tr><td colspan="2">
               <form:checkbox path="car" value="Recommended by car"/>Recommended by car</td>
               <td></td></tr>
        </table></td>
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