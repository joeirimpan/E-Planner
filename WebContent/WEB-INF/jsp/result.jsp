<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/header.jsp" %>


<div class="center blue-text text-darken-3"><h3>Matching Destinations</h3></div>
<!--
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
  	</tr>
    
    <tr>
        <td>Swimming Pool</td>
        <td>${userconstraints.sPoolSelected}</td>
    </tr>
    
    <tr>
        <td>Fitness Room</td>
        <td>${userconstraints.fitnessRoomSelected}</td>
    </tr>
    
    <tr>
        <td>Transport Service</td>
        <td>${userconstraints.transport}</td>
    </tr>
	</table> 
-->
<div class="row">
	<div class="col s1 white-text">a</div>
    <div class="col s6 card-panel blue lighten-4" style="font-size:20px;font-weight:bold;">
<%--     		${queryresult} --%>
    	<br>
    	<c:if test="${empty queryresult}">
    	<div class="row">
				   
				   	<div class="center">No Matching Destinations</div>
			   	</div>
    	 </c:if>
    		  
		<c:forEach items="${queryresult}" var="map">
			  <c:if test="${map.localName!=NULL}">
			  	<div class="row">
				   	<div class="col s4">Destination</div>
				   	<div class="col s6">${map.localName}</div>
			   	</div>
				  </c:if>
			  <c:if test="${map.skiing!=NULL}">
			  	<div class="row">
				   	<div class="col s4">Skiing</div>
				   	<div class="col s6">${map.skiing}</div>
			   	</div>
			  </c:if>
			  <c:if test="${map.surfing!=NULL}">
			  	<div class="row">
				   	<div class="col s4">Surfing</div>
				   	<div class="col s6">${map.surfing}</div>
			   	</div>
			  </c:if>
			  <c:if test="${map.hiking!=NULL}">
			  	<div class="row">
				   	<div class="col s4">Hiking</div>
				   	<div class="col s6">${map.hiking}</div>
			   	</div>
			  </c:if>
			  <c:if test="${map.sightSeeing!=NULL}">
			  	<div class="row">
				   	<div class="col s4">Sight Seeing</div>
				   	<div class="col s6">${map.sightSeeing}</div>
			   	</div>
			  </c:if>
			  <c:if test="${map.shopping!=NULL}">
			  	<div class="row">
				   	<div class="col s4">Showing</div>
				   	<div class="col s6">${map.shopping}</div>
			   	</div>
			  </c:if>
			  <c:if test="${map.destinationURI!=NULL}">
			  	<div class="row">
				   	<div class="col s4">Destination URI</div>
				   	<div class="col s6">${map.destinationURI}</div>	
			   	</div>
			  </c:if>
			  <c:if test="${map.uri!=NULL}">
			  	<div class="row" style="font-size:15px">
				   	<div class="col s4">URI</div>
				   	<div class="col s6">${map.uri}</div>
			   	</div>
			  </c:if>

			
		<%--     <c:forEach items="${map}" var="entry"> --%>
		    	
		<%--         ${entry.key} --%>
		<%--         ${entry.value}<br> --%>
		<%--     </c:forEach> --%>
		</c:forEach>
    </div>
    <div class="col s1 white-text">a</div>
    <div class="col s3 card-panel">
 		<br>
        <div class="row">
          <div class="center blue-text text-darken-3" style="font-size:20px">User Preferences</div>
          
        </div>

        <div class="row">
          <div class="col s6">Selected Rating</div>
          <div class="col s6">${userconstraints.ratingSelected}</div>
        </div>

        <div class="row">
          <div class="col s6">Parking Selected</div>
       
			<c:choose>
			  <c:when test="${userconstraints.parkingSelected == false}">
			   	<div class="col s6">Not Selected</div>
			  </c:when>
			  <c:otherwise>
			    <div class="col s6">Selected</div>
			  </c:otherwise>
			</c:choose>
        </div>

        <div class="row">
          <div class="col s6">Food Board</div>
          <div class="col s6">${userconstraints.board}</div>
        </div>

        <div class="row">
          <div class="col s6">Activities</div>
          <div class="col s6">${userconstraints.activities}</div>
        </div>

        <div class="row">
          <div class="col s6">Swimming Pool</div>
			<c:choose>
			  <c:when test="${userconstraints.sPoolSelected == false}">
			   	<div class="col s6">Not Selected</div>
			  </c:when>
			  <c:otherwise>
			    <div class="col s6">Selected</div>
			  </c:otherwise>
			</c:choose>
        </div>

        <div class="row">
          <div class="col s6">Fitness Room</div>
			<c:choose>
			  <c:when test="${userconstraints.fitnessRoomSelected == false}">
			   	<div class="col s6">Not Selected</div>
			  </c:when>
			  <c:otherwise>
			    <div class="col s6">Selected</div>
			  </c:otherwise>
			</c:choose>
        </div>

        <div class="row">
          <div class="col s6">Transport Service</div>
          <div class="col s6">${userconstraints.transport}</div>
        </div>
    </div>

</div>
        <!-- Page Content goes here -->
	
<%-- 	<h2>Query Result</h2>
	<br>
	<h3>

	</h3>  --%>

	
</body>
</html>