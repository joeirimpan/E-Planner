<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="center light blue-text"><h3>Search For Destinations</h3></div>
<%-- <form:form method="POST" action="/E-Planner/addConstraints" commandName="userconstraints" >

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
             <option value="hiking">Hiking</option>
             <option value="surfing">Surfing</option>
             <option value="skiing">Skiing</option>
             <option value="shopping">Shopping</option>
             <option value="sightseeing">Sight Seeing</option>
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

</form:form> --%>

<form action="/E-Planner/addConstraints.html" method="post">


<div class="row">
<div class="col m3 white-text">a</div>
<div class="col m6 card-panel">
<div class="col m12">
	    <div class="row">
       <!--  <div class="input-field col s12">
          <input id="destinationName" type="text" class="validate">
          <label for="destinationName">Destination Name</label>
        </div> -->
      </div>
      <div class="row">
        <div class="input-field col s12">
          <select name="ratingSelected">
            <option value="" disabled selected>Choose your option</option>
            <option value="one">one star rating</option>
            <option value="two">two star rating</option>
            <option value="three">three star rating</option>
            <option value="four">Four star rating</option>
            <option value="five">Five star rating</option>
          </select>
          <label>Rating Selected</label>
        </div>
      </div>
            <div class="row">
        <div class="input-field col s12">
          <select name="board">
            <option value="" disabled selected>Choose your option</option>
            <option value="one">All Inclusive</option>
            <option value="two">Full Board</option>
            <option value="three">Half Board</option>
          </select>
          <label>Food Board</label>
        </div>
      </div>
      <div class="row">
        <p>
          <input type="checkbox" id="parkingSelected" name="parkingSelected"/>
          <label for="parkingSelected">Parking</label>
        </p>
      </div>
     


      <div class="row">
        <div class="input-field col s12">
          <select multiple name="activities">
            <option value="" disabled selected>Choose your option</option>
             <option value="hiking">Hiking</option>
             <option value="surfing">Surfing</option>
             <option value="skiing">Skiing</option>
             <option value="shopping">Shopping</option>
             <option value="sightseeing">Sight Seeing</option>
          </select>
          <label>Activities</label>
        </div>
      </div>
      <div class="row">
        <p>
          <input type="checkbox" id="sPoolSelected" name="sPoolSelected"/>
          <label for="sPoolSelected">Swimming Pool</label>
        </p>
      </div>
      <div class="row">
        <p>
          <input type="checkbox" id="fitnessRoomSelected" name="fitnessRoomSelected"/>
          <label for="fitnessRoomSelected">Fitness Room</label>
        </p>
      </div>

      <div class="row">
        <div class="col m5 white-text">a</div>
        <div class="col m2">
         <button class="btn-large circle waves-effect waves-light light-blue darken-4"" type="submit" name="action">Search</button>
        </div>
      </div>
</div>
</div>
</div>
</form>

<script type="text/javascript">
$(document).ready(function() {
    // Select - Single
    $('select').material_select();
});
</script>

</body>
</html>