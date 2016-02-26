<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/header.jsp" %>


<div class="center teal-text "><h3>Result</h3></div>
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
    <div class="col s4 white-text">a
    </div>
    <div class="col s6">
 
        <div class="row">
          <div class="col s6">Destination Name</div>
          <div class="col s6">${userconstraints.destinationName}</div>
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
	
	<h2>Query Result</h2>
	<br>
	<h3>
	${queryresult}
	</h3> 

    
    <script type="text/javascript">
    string.replace(/false/g,'str2')

    </script>
</body>
</html>