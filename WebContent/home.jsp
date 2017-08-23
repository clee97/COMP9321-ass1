<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Entry"%>
<%@page import="java.util.List"%>
<%@page import="dao.XMLDao"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%
	XMLDao dao = new XMLDao();
	List<Entry> randomEntries = new ArrayList<Entry>();
	String count = request.getParameter("count") != null ? request.getParameter("count") : "10";
	if (count.equals("ALL")){
		randomEntries = dao.randomise(Integer.MAX_VALUE);
	}else{
		randomEntries = dao.randomise(Integer.parseInt(count));
	}
	
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">News Room</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="search.jsp">Search News</a></li>
      <li><a href="contactus.jsp">Contact Us</a></li>
      <li><a href="sitemap.jsp">Site Map</a></li>
    </ul>
  </div>
</nav>

<div class="container">
    <hgroup class="mb20">
		<h1>You may like</h1>
	 	<form id="random-count" method=GET action="home.jsp">
			<div class="form-group">
				<label for="sel1">Select number of artifacts showing:</label>
				<select class="form-control" name="count" id="sel1" placeholder="<%=count%>">
					<option value="10">10</option>
					<option value="100">100</option>
					<option value="1000">1000</option>
					<option value="ALL">ALL</option>
				</select>
			</div>
			<input type="submit" class="btn btn-primary" value="Show">
		</form>
		<h2 class="lead">Showing <strong class="text-danger"><%=count %></strong> Artifacts</h2>								
	</hgroup>
	<form id="artifact-page" method="GET" action="API">
	    <section class="col-xs-12 col-sm-6 col-md-12">
			<%for (Entry e : randomEntries){ %>
			<input type="hidden" name="artifact-address" value="<%=e.getAddress()%>">
			<article class="search-result row">
				<div class="col-xs-12 col-sm-12 col-md-2">
					<ul class="meta-search">
						<li><i class="glyphicon glyphicon-calendar"></i> <span><%=e.getDate().split("T")[0]%></span></li>
						<li><i class="glyphicon glyphicon-home"></i> <span><%=e.getCity() %></span></li>
						<li><i class="glyphicon glyphicon-user"></i> <span><%=e.getEnteredBy() %></span></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-7 excerpet">
					<h3><a href="API?address=<%=e.getAddress()%>&action=artifact" title=""><%=e.getHeadline() %></a></h3>
					<p><%=e.getContent() %></p>						
				</div>
				<span class="clearfix borda"></span>
			</article>
			<%} %>
		</section>
	</form>
</div>
</body>
</html>