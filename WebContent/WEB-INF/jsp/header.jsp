<html>
<head>
    <title>E-Tourism Planner</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/materialize.css" rel="stylesheet" >
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/materialize.js"></script>
</head>
<body>
<ul id="dropdown1" class="dropdown-content light blue-text">
  <li><a href="http://localhost:8080/E-Planner/student.html">Add Destination</a></li>
  <li><a href="http://localhost:8080/E-Planner/view.html">Manage Destinations</a></li>
  <li class="divider"></li>
</ul>
<nav>
  <div class="nav-wrapper  light-blue darken-4">
    <a href="http://localhost:8080/E-Planner/NewFile.jsp" font-style="italic" class="brand-logo">E-Planner</a>
    
    <ul class="right hide-on-med-and-down">
<li><a class="dropdown-button btn-large waves-effect waves-light light-blue darken-4" href="#!" data-activates="dropdown1"><i class="material-icons left" >person_pin</i>Admin<i class="material-icons right">arrow_drop_down</i></a></li>
</ul>
    
  </div>
</nav>