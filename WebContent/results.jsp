<%@page import="java.util.Collections"%>
<%@page import="models.Entry"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<% 
	List<List<Entry>> pages = (List<List<Entry>>)request.getSession().getAttribute("searchResults");
	String searchStrings = (String)request.getSession().getAttribute("searchStrings");
	Integer lastPage = pages.size();
	Integer pageNum = request.getParameter("pageNumber") != null ? Integer.parseInt(request.getParameter("pageNumber")) : 1;
%>
<nav class="navbar navbar-inverse">
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
<div class="container">
<%if (pages.size() != 0 && searchStrings != null){%>
<hgroup class="mb20">
	<h2 class="lead">Showing search results for: <%=searchStrings %></h2>
	<h2 class="lead"><strong class="text-danger">Page: <%=pageNum %></strong> </h2>	
</hgroup>
<form id="artifact-page" method="GET" action="API">
    <section class="col-xs-12 col-sm-6 col-md-12">
		<%for (Entry e : pages.get(pageNum - 1)){ %>
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
				<p><pre style="white-space: pre-wrap"><%=e.getContent() %></pre></p>						
			</div>
			<span class="clearfix borda"></span>
		</article>
		<%} %>
	</section>
</form>
</div>
<div class="container">            
  <ul class="pager">
  <%if (pageNum != 1) {%>
    <li class="previous"><a href="results.jsp?pageNumber=<%=pageNum - 1%>">Previous</a></li>
  <%} %>
  <%if (pageNum != lastPage) {%>
    <li class="next"><a href="results.jsp?pageNumber=<%=pageNum + 1%>">Next</a></li>
  <%}%>
  </ul>
</div>
<%}else{ %>
<div class="container">
	<hgroup class="mb20">
		<h2 class="lead"><strong class="text-danger">Sorry, no matching datasets found!</strong> </h2>									
	</hgroup>
<%} %>
</div>
</body>
</html>