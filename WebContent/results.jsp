<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="helpers.XMLParser"%>
<%@ page import="org.w3c.dom.Element"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Search Bar</title>

<link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>
<body>
	<%!
		String searchString;
		String bookId;
		String searchBy;
		ArrayList<Element> numBooks;
	%>
	<%
		searchBy = (request.getParameter("searchBy") == null) ? "" : request.getParameter("searchBy");
		searchString = (request.getParameter("search") == null) ? "Not Specified" : request.getParameter("search"); 
		numBooks = XMLParser.find(searchBy, searchString);
	%>
	<%if (!numBooks.isEmpty()){ %>
	<div style="float: right; margin-right: 20%;">
		<ul>
		  <li><a href="shopping_cart.jsp"><h2>Shopping Cart</h2><img src="pictures/shopping_cart.jpg" width="50px" height="50px"></a></li>
		  <li><a href="search.jsp"><h2>Back to Search</h2></a></li>
		</ul>
	</div>
	<form class="form-wrapper" action="results.jsp" method="post">

	    <input type="text" name="search" id="search" placeholder="Search again ..." required>
	    <input type="submit" value="go" id="submit">
	    <a>Search By: 
	    <select name="searchBy">
	    	<option value="AUTHOR">Author</option>
	    	<option value="TITLE">Title</option>
	    	<option value="GENRE">Genre</option>
	    	<option value="DESCRIPTION">Description</option>
	    </select>
	</a>
	</form>

	<form action="shopping_cart.jsp" method="post">
		<div style="margin-right:10%;padding:1px 16px;height:1000px;">
			
			<h1 align="center">Search Results For : <%=searchString %></h1>
		
			<table align="center">
				<col width="35%" />
				<col width="35%" />
				<col width="20%" />
				<col width="20%"  />
				<tbody>
					<tr>
						<th><h2>Book Details</h2></th>
						<th><h2>Description</h2></th>
						<th><h2>Price</h2></th>
						<th><h2>Add to Cart</h2>
					</tr>
		
			<% 
		
				for (Element e : numBooks){
					bookId = e.getAttribute("id");
			%>
					<tr>
						<td>
							<br /><i><strong>Author: </strong></i><%=e.getElementsByTagName("author").item(0).getTextContent()%><br />
							<br /><strong>Title: </strong><%=e.getElementsByTagName("title").item(0).getTextContent()%><br />
							<br /><strong>Genre: </strong><%=e.getElementsByTagName("genre").item(0).getTextContent()%><br />
							<br /><strong>Publish Date: </strong><%=e.getElementsByTagName("publish_date").item(0).getTextContent()%><br />
						</td> 
						
						<td><i><%=e.getElementsByTagName("description").item(0).getTextContent()%></i></td>
						<td align="middle"><h1>$<%=e.getElementsByTagName("price").item(0).getTextContent()%></h1></td> 
						<td><input position="right" type="checkbox" name=<%=bookId%> value=<%=bookId%>></td>
					</tr>
				
			<%	
					} 
			%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><input style="width: 150px; height: 40px" type="submit" value="Add Books To Cart"></td>
				</tr>
			<% 
				}else{
			%>
				<form class="form-wrapper" action="results.jsp" method="post">

				    <input type="text" name="search" id="search" placeholder="Search again ..." required>
				    <input type="submit" value="go" id="submit">
				    <a>Search By: 
					    <select name="searchBy">
					    	<option value="AUTHOR">Author</option>
					    	<option value="TITLE">Title</option>
					    	<option value="GENRE">Genre</option>
					    	<option value="DESCRIPTION">Description</option>
					    </select>
					</a>
				</form>
				<h1> No results found</h1>
			
			<%}%>

				</tbody>
			</table>
		</div>
	</form>
	


</body>
</html>