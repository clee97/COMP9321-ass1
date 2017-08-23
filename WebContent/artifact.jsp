<!DOCTYPE html>
<%@page import="dao.XMLDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Entry"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/artifact.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%
	Entry artifact = (Entry)request.getAttribute("artifact");
%>
<nav class="navbar navbar-inverse navbar-static-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">News Room</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="home.jsp">Home</a></li>
      <li><a href="search.jsp">Search News</a></li>
      <li><a href="contactus.jsp">Contact Us</a></li>
      <li><a href="sitemap.jsp">Site Map</a></li>
    </ul>
  </div>
</nav>
<section class="banner-section">
</section>
<section class="post-content-section">
    <div class="container">

        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 post-title-block">
               
                <h1 class="text-center"><%=artifact.getHeadline()%></h1>
                <ul class="list-inline text-center">
                    <li><%=artifact.getEnteredBy() %> |</li>
                    <li><%=artifact.getAgency() %> |</li>
                    <li><%=artifact.getDate().split("T")[0] %> |</li>
                </ul>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-12">
            	<p class="lead">Artifact Content: </p>
                <pre><%=artifact.getContent() %></pre>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12">
                <div class="well">
                    <h2>Subscription Box</h2>
                    <p>Form Description Goes here</p>
                    <div class="input-group">
			      <input type="text" class="form-control" placeholder="Search for...">
			      <span class="input-group-btn">
			        <button class="btn btn-default" type="button">Go!</button>
			      </span>
			 	</div>
                </div>
                <form id="extract" method="GET" action="API">
                <input type="hidden" name="address" value="<%=artifact.getAddress()%>">
	                <div class="list-group">
	                	<input type="submit" class="btn btn-primary" name="action" value="Extract Keywords">
	                    <a class="list-group-item" href="#"> <h4 class="list-group-item-heading">Extract Keywords</h4> <p class="list-group-item-text">Press "Extract Keywords" to have this page highlight all its keywords</p> </a>
	                    <br>
	                    <input type="submit" class="btn btn-primary" name="action" value="Extract People">	
	                    <a class="list-group-item" href="#"> <h4 class="list-group-item-heading">Extract People</h4> <p class="list-group-item-text">Press "Extract People" to have this page highlight people</p> </a>
	                    <br>
	                    <input type="submit" class="btn btn-primary" name="action" value="Extract Organisations">	
	                    <a class="list-group-item" href="#"> <h4 class="list-group-item-heading">Extract Organisations</h4> <p class="list-group-item-text">Press "Extract Organisations" to have this page highlight all organisations</p> </a> 
	                	<br>
	                	<input type="submit" class="btn btn-primary" name="action" value="Extract Locations">	
	               		<a class="list-group-item" href="#"> <h4 class="list-group-item-heading">Extract Locations</h4> <p class="list-group-item-text">Press "Extract Locations" to have this page highlight all locations</p> </a> 
	                </div>
                </form>
            </div>
        </div>
      

    </div> <!-- /container -->
</section>

</body>
</html>