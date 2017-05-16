<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Search Bar</title>

<link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>
<body>
<img src="pictures/search_logo.jpg" class="search_logo">
<form class="form-wrapper" action="search" method="post">
    <input type="text" id="search" placeholder="Search for..." required>
    <input type="submit" value="go" id="submit">
</form>
</body>
</html>