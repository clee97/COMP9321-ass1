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
		ArrayList<Element> numBooks;
	%>
	<%
		searchString = request.getParameter("search"); 
		numBooks = XMLParser.find("searchString");
		for (Element e : numBooks){
	%>
	<table border="1" align="center"><caption></caption>
			<tbody>

				<tr><td><img class="search_small_image" src="$smallpic"></td>
				<td>
					
					<br /><i><strong>Author: </strong></i><%=e.getElementsByTagName("author").item(0).getTextContent()%><br />
					<br /><strong>Title: </strong><%=e.getElementsByTagName("title").item(0).getTextContent()%><br />
					<br /><strong>Genre: </strong><%=e.getElementsByTagName("genre").item(0).getTextContent()%><br />
					<br /><strong>Publish Date: </strong><%=e.getElementsByTagName("publish_date").item(0).getTextContent()%><br />
				</td> 
				<td align="right"><%=e.getElementsByTagName("price").item(0).getTextContent()%></td> 
				<td>
					<strong>Description: </strong><%=e.getElementsByTagName("description").item(0).getTextContent()%>

				</td>
				</tr>
				</tbody>
	<%	} %>


</body>
</html>