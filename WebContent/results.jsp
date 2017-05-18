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
		ArrayList<Element> numBooks;
	%>
	<%
		searchString = (request.getParameter("search") == null) ? "Not Specified" : request.getParameter("search"); 
		numBooks = XMLParser.find("searchString");
	%>
	<h1 align="center">Search Results For : <%=searchString %><a class="shopping_cart">Shopping Cart</a></h1>

	<table align="center">
		<col width="20%" />
		<col width="15%" />
		<col width="30%" />
		<col width="15%"  />
		<tbody>
			<tr>
				<th><h2>Book Details</h2></th>
				<th><h2>Price</h2></th>
				<th><h2>Description</h2></th>
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
				<td align="middle"><h1>$<%=e.getElementsByTagName("price").item(0).getTextContent()%></h1></td> 
				<td><i><%=e.getElementsByTagName("description").item(0).getTextContent()%></i></td>
				<td><input position="right" type="checkbox" id=<%=bookId%>></td>
			</tr>
			
			
			
	<%	} %>
		</tbody>


</body>
</html>