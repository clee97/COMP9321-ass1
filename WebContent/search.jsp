<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">News Room</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="home.jsp">Home</a></li>
      <li class="active"><a href="#">Search News</a></li>
      <li><a href="#">Contact Us</a></li>
    </ul>
  </div>
</nav>
<div class="container">
  <h2>News Search</h2>
  <form action="API" method="GET">
  	<input type="hidden" name="action" value="search">
    <div class="form-group">
      <label for="entry-agency">Agency:</label>
      <input type="text" class="form-control" id="entry-agency" placeholder="Enter Agency" name="entry-agency">
    </div>
    <div class="form-group">
      <label for="entry-headline">Headline:</label>
      <input type="text" class="form-control" id="entry-headline" placeholder="Enter Headline" name="entry-headline">
    </div>
    <div class="form-group">
      <label for="entry-date">Date:</label>
      <input type="text" class="form-control" id="entry-date" placeholder="Enter Date" name="entry-date">
    </div>
    <div class="form-group">
      <label for="entry-city">City:</label>
      <input type="text" class="form-control" id="entry-city" placeholder="Enter City" name="entry-city">
    </div>
    <div class="form-group">
      <label for="entry-content">Content:</label>
      <input type="text" class="form-control" id="book-title" placeholder="Enter Content" name="entry-content">
    </div>
    <button type="submit" class="btn btn-primary">Search</button>
  </form>
</div>

</body>
</html>
