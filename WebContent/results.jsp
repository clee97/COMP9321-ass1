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
<script src="js/shopping_cart.js"></script>
</head>
<body>

	<%!
		String searchString;
		String bookId;
		String searchBy;
		ArrayList<Element> numBooks;
		int start;
		int end;
		int counter;
	%>
	<%
		searchBy = (request.getParameter("searchBy") == null) ? "" : request.getParameter("searchBy");
		searchString = (request.getParameter("search") == null) ? "Not Specified" : request.getParameter("search"); 
		numBooks = XMLParser.find(searchBy, searchString);
		
		start = (request.getParameter("start") == null) ? 0 : Integer.parseInt(request.getParameter("start"));
		end = (request.getParameter("start") == null) ? 10 : Integer.parseInt(request.getParameter("end"));
		counter = start;
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
	<%if (!(end >= numBooks.size())){%>
		<form action="results.jsp">
			<input type="submit" value="next">
	
			<input type="hidden" name="search" value=<%=searchString%>>
			<input type="hidden" name="searchBy" value=<%=searchBy%>>
			<input type="hidden" name="start" value="<%=start + 10 %>">
			<input type="hidden" name="end" value="<%=end + 10 %>">
		</form>
		<%} %>
		<%if (start != 0 && end != 10){%>
		<form action="results.jsp">
			<input type="submit" value="prev">
			<input type="hidden" name="search" value=<%=searchString%>>
			<input type="hidden" name="searchBy" value=<%=searchBy%>>
			<input type="hidden" name="start" value="<%=start - 10 %>">
			<input type="hidden" name="end" value="<%=end - 10 %>">
		</form>
	<%} %>
	<form action="shopping_cart.jsp" id="add_items" method="post">
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
		
				for (int i = start; i < numBooks.size(); i++){
					Element e = numBooks.get(i);
					if (counter == end){
						break;
					}
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
					counter++;
				} 
			%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><button type="button" style="width: 150px; height: 40px" onclick="check_items()">Add Books To Cart</button></td>
				</tr>
			<% 
				}else{
			%>
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
				<h1> Sorry no matching datasets found!</h1>
			
			<%}%>

				</tbody>
			</table>
		</div>
	</form>
	


</body>
</html>