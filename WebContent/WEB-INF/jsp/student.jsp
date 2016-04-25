<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="center light blue-text"><h3>Insert New Destination</h3></div>
<%-- <form:form method="POST" action="/E-Planner/addConstraints" commandName="userconstraints" >

<div class="row">
<div class="col m3 white-text">a</div>
<div class="col m6 card-panel">
   	<table>   
    <tr>
        <td><form:label path="Name"><h4>Destination Name</h4></form:label></td>
        <td><form:input path="Name" /></td>
    </tr>
     <tr>
        <td><form:label path="Age"><h4>Destination URL</h4></form:label></td>
        <td><form:input path="Age" /></td>
    </tr>
      
    <div class="center teal-text"><h3>Hotel Details</h3></div>
    <div class="row">
        <div class="input-field col s12">
          <input id="Name" type="text" class="validate">
          <label for="Name">Hotel Name</label>
        
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
        <label>Activities</label>
        <div class="row">
        <p>
          <input type="checkbox" id="parkingSelected" name="parkingSelected"/>
          <label for="parkingSelected">Parking</label>
        </p>
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
    
  </table>
  </div>
	<div class="row">
	<div class="col m5 white-text">a</div>
	<div class="col m2"><input class="btn waves-effect waves-light light-green" type="submit" value="Submit"/></div>
	<div class="col m5"></div>
	</div>
	
	
	<br>

</div>
<div class="col m6></div>
</div>

</form:form> --%>

<form action="/E-Planner/addConstraints.html" method="post">


<div class="row">
<div class="col m3 white-text">a</div>
<div class="col m6 card-panel">
<div class="col m12">
	    <div class="row">
        <div class="input-field col s12">
          <input id="Name" type="text" class="validate" >
<!--           <label for="Name">Destination Name</label> -->
        </div>
      </div>
       <!-- <div class="row">
        <div class="input-field col s12">
          <input id="Age" type="text" class="validate">
          <label for="Age">Destination URL</label>
        </div>
      </div> -->
    
      </div>
 <div class="center light blue-text"><h3>Hotel Details</h3></div>
 <div class="row">
        <div class="input-field col s12">
          <input id="Name" type="text" class="validate">
          <label for="Name">Hotel Name</label>
        </div>
        <div class="row">
        <div class="input-field col s12">
          <select name="ratingSelected">
            <option value="" disabled selected>Choose your Rating</option>
            <option value="one">one star rating</option>
            <option value="two">two star rating</option>
            <option value="three">three star rating</option>
            <option value="four">Four star rating</option>
            <option value="five">Five star rating</option>
          </select>
          <label>Select Rating</label>
        </div>
      </div>
       <div class="row">
        <div class="input-field col s12">
          <select name="board">
            <option value="" disabled selected>Select the food board you provide</option>
            <option value="one">All Inclusive</option>
            <option value="two">Full Board</option>
            <option value="three">Half Board</option>
          </select>
          <label>Food Board</label>
        </div>
      </div>
      <div class="row">
      <div class="center light blue-text"><h4>Tick if you Provide..</h4></div>
        <p>
          <input type="checkbox" id="parkingSelected" name="parkingSelected"/>
          <label for="parkingSelected">Parking</label>
        </p>
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
      <div class="center light blue-text"><h4>Activities You Have..</h4></div>
       
        <div class="input-field col s12">
	        <div class="row">
	        	<div class="input-field col s3">
	          <input type="checkbox" class="filled-in" id="hikingSelected" name="hikingSelected"/>
	          <label for="hikingSelected">hiking</label>
	          	</div>
	          	<div class="input-field col s3">
					<input id="Name" type="text" class="validate">
	          	</div>
	         
	        </div> 
	        <div class="row">
	        	<div class="input-field col s3">
	          <input type="checkbox"class="filled-in" id="surfingSelected" name="suringSelected"/>
          <label for="surfingSelected">surfing</label>
	          	</div>
	          	<div class="input-field col s3">
					<input id="Name" type="text" class="validate">
	          	</div>
	         
	        </div> 
	        <div class="row">
	        	<div class="input-field col s3">
	         <input type="checkbox" class="filled-in" id="skiingSelected" name="skiingSelected"/>
          <label for="skiingSelected">skiing</label>
	          	</div>
	          	<div class="input-field col s3">
					<input id="Name" type="text" class="validate">
	          	</div>
	         
	        </div> 
	        <div class="row">
	        	<div class="input-field col s3">
	          <input type="checkbox" class="filled-in" id="shoppingSelected" name="shoppingSelected"/>
          <label for="shoppingSelected">shopping</label>
	          	</div>
	          	<div class="input-field col s3">
					<input id="Name" type="text" class="validate">
	          	</div>
	         
	        </div> 
	        <div class="row">
	        	<div class="input-field col s3">
	          <input type="checkbox" class="filled-in" id="sightseeingSelected" name="sightseeingSelected"/>
          <label for="sightseeingSelected">sightseeing</label>
	          	</div>
	          	<div class="input-field col s3">
					<input id="Name" type="text" class="validate">
	          	</div>
	         
	        </div>     
        
      
      </div>
      <div class="center light blue-text"><h4>Transportation Facilities You Provide</h4></div>
       <div class="row">
        <div class="input-field col s12">
          <select multiple name="activities">
          <label>Choose What All You Provide..</label>
            <option value="" disabled selected>Car Preferred</option>
             <option value="Bus">Bus</option>
             <option value="Train">Train</option>
             <option value="Aeroplane">aeroplane</option>
          </select> 
        </div>
      </div>
      </div>
   	  <div class="row">
        <div class="col m5 white-text">a</div>
        <div class="col m2">
         <button class="btn-large circle waves-effect waves-light light-blue darken-4" type="submit" name="action">Add</button>
        </div>
      </div>
</div>
</div>
</div>
</form>
<script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.geocomplete.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    // Select - Single
    $('select').material_select();
});

$(function(){
    
    $("#Name").geocomplete()
      .bind("geocode:result", function(event, result){
        
      })
      .bind("geocode:error", function(event, status){
        
      })
      .bind("geocode:multiple", function(event, results){
      
      });
  });
</script>

</body>
</html>