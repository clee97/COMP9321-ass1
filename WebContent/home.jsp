<!DOCTYPE html>
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
	Integer count = request.getParameter("count") != null ? Integer.parseInt(request.getParameter("count")) : 10;
	List<Entry> randomEntries = dao.randomise(count);
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">News Room</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="search.jsp">Search News</a></li>
      <li><a href="#">Contact Us</a></li>
    </ul>
  </div>
</nav>
<div class="container">

    <hgroup class="mb20">
		<h1>You may like</h1>
		<h2 class="lead">Showing <strong class="text-danger"><%=count %></strong> Artifacts</h2>								
	</hgroup>

    <section class="col-xs-12 col-sm-6 col-md-12">
		<%for (Entry e : randomEntries){ %>
		<article class="search-result row">
			<div class="col-xs-12 col-sm-12 col-md-2">
				<ul class="meta-search">
					<li><i class="glyphicon glyphicon-calendar"></i> <span><%=e.getDate().split("T")[0]%></span></li>
					<li><i class="glyphicon glyphicon-home"></i> <span><%=e.getCity() %></span></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-7 excerpet">
				<h3><a href="#" title=""><%=e.getHeadline() %></a></h3>
				<p><%=e.getContent() %></p>						
                <span class="plus"><a href="#" title="Lorem ipsum"><i class="glyphicon glyphicon-plus"></i></a></span>
			</div>
			<span class="clearfix borda"></span>
		</article>
		<%} %>
	</section>
</div>
</body>
</html>