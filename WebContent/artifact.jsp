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
	String addr = request.getParameter("address");
	XMLDao dao = new XMLDao();
	Entry artifact = dao.searchByAddress(addr);
%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">News Room</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="home.jsp">Home</a></li>
      <li><a href="search.jsp">Search News</a></li>
      <li><a href="#">Contact Us</a></li>
    </ul>
  </div>
</nav>
<!-- <section class="col-xs-12 col-sm-6 col-md-12">
	<article class="search-result row">
		<div class="col-xs-12 col-sm-12 col-md-2">
			<ul class="meta-search">
				<li><i class="glyphicon glyphicon-calendar"></i> <span><%=artifact.getDate().split("T")[0]%></span></li>
				<li><i class="glyphicon glyphicon-home"></i> <span><%=artifact.getCity() %></span></li>
			</ul>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-7 excerpet">
			<h3><a href="#" title=""><%=artifact.getHeadline() %></a></h3>
			<p><%=artifact.getContent() %></p>						
		</div>
		<span class="clearfix borda"></span>
	</article>
</section>-->
<article>
    
    <header>
        
        <h1><%=artifact.getHeadline() %></h1>
        
    </header>
    
    <div class="background-bar">
        
    </div>
    
    <section class="container-fluid main-body">
        <section class="row">
            
            <div class="hidden-xs col-sm-1 col-md-2">
                
            </div>
            
            <div class="col-xs-12 col-sm-10 col-md-8">
            
                <div class="content-holder">
                    
                    <div class="content-description">
                        <div class="author-avatar">
                            <img src="http://lorempixel.com/90/90/people" class="img-circle" />
                        </div>
                        <div class="author-name">
                            <h3><%=artifact.getEnteredBy() %></h3>
                        </div>
                        <div class="row blog-info">
                            <div class="col-xs-12 col-sm-6">
                                <span class="lead text-muted"><i class="fa fa-clock-o"></i> Published: <%=artifact.getDate().split("T")[0] %></span>
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <span class="lead text-muted"><i class="fa fa-tags"></i>City: <%=artifact.getCity() %></span>
                            </div>
                        </div>
                        
                    </div>
                    
                    <div class="content-body">
                       <p><pre><%=artifact.getContent() %></pre></p>
                    </div>
                    
                </div>
            
            </div>
            
            <div class="hidden-xs col-sm-1 col-md-2">
                
            </div>
            
        </section>
    </section>
    
</article>
</body>
</html>