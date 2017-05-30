<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Set"%>
<%@ page import="helpers.XMLParser"%>
<%@ page import="org.w3c.dom.Element"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Search Bar</title>

<link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>
<body>
<%!
	Map<String, String[]> formData = new HashMap<String, String[]>();
	Set<String> keys = new HashSet<String>();
	Set<String> deleteKeys = new HashSet<String>();
	Element e;
	Float total;
%>
<%
	
%>
<script src="js/shopping_cart.js"></script>
<form action="shopping_cart.jsp" method="post">
<div style="padding:1px 16px;height:1000px;">
	<div style="float: right; margin-right: 20%;">
		<ul>
		  <li><a href="search.jsp"><h2>Back to Search</h2></a></li>
		  <li><a href="results.jsp"><h2>Back to Results</h2></a></li>
		</ul>
	</div>
	<table align="center">
		<col width="35%" />
		<col width="35%" />
		<col width="20%" />
		<col width="20%"  />
		
			<% 
				keys = new HashSet<String>();
				deleteKeys = new HashSet<String>();
				for (int i = 0; i < request.getCookies().length; i++){
					
					Cookie c = request.getCookies()[i];

					if (c.getValue() != null){
						if (c.getValue().contains("bk")){

							keys.add(c.getValue());
						
						}
					}				
				}

				for (String s : request.getParameterMap().keySet()){

					if (s.startsWith("bk")){
						keys.add(s);
					}else{
						deleteKeys.add(s.replace("delete", ""));
					}
				}
				
			
				formData = request.getParameterMap();
				total = 0f;
				if (keys.isEmpty() || (deleteKeys.size() == keys.size())){
					for (String s : deleteKeys){
						Cookie c = new Cookie(s, null);
						c.setMaxAge(0);
						response.addCookie(c);
					}
					%>
						<h1>Shopping Cart is Empty!</h1>
					<%
				}else{
					%>
					<tbody>
						<tr>
							<th><h2>Book Details</h2></th>
							<th><h2>Description</h2></th>
							<th><h2>Price</h2></th>
							<th><h2>Remove from Cart</h2></th>
							
						</tr>
					<% 
					for (String s: keys){
						if (deleteKeys.contains(s)){
						
							Cookie c = new Cookie(s, null);
							c.setMaxAge(0);
							response.addCookie(c);
							continue;
							
						}
						response.addCookie(new Cookie(s, s));
						
						e = XMLParser.getElementById(s);
						total += Float.parseFloat(e.getElementsByTagName("price").item(0).getTextContent());
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
							<td>
								
								<input type="checkbox" name="delete<%=s %>" value="<%=s %>">
							</td>
						</tr>
				<%
						
					}
				%>
				<tr>
					<td><input type="submit" value="Remove from Cart"></td>
					<td></td>
					<td><h2>Check Out (Total: $<%=total%>)</h2></td>
				</tr>
				<%
				
				}
				%>
		
		</tbody>
	</table>
</div>
</form>
</body>
</html>