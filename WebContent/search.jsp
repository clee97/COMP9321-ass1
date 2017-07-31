<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Search Bar</title>

<link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>
<body>
<div style="float: right; margin-right: 20%;">
	<ul>
	  <li><a href="shopping_cart.jsp"><h2>Shopping Cart</h2><img src="pictures/shopping_cart.jpg" width="50px" height="50px"></a></li>
	</ul>
</div>
<img src="pictures/search_logo.jpg" class="search_logo">

<form class="form-wrapper" action="API" method="get">
    <input type="text" name="search" id="search" placeholder="Search for..." required>
    <input type="submit" value="go" id="submit">
    <input type="hidden" name="start" value="0">
    <input type="hidden" name="end" value="10">
    <a>Search By: 
	    <select name="searchBy">
	    	<option value="AUTHOR">Author</option>
	    	<option value="TITLE">Title</option>
	    	<option value="GENRE">Genre</option>
	    	<option value="DESCRIPTION">Description</option>
	    </select>
	</a>
</form>
</body>
</html>