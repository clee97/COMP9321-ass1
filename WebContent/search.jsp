<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Online Book Store Search</h2>
  <form action="API" method="GET">
    <div class="form-group">
      <label for="book-title">Title:</label>
      <input type="text" class="form-control" id="book-title" placeholder="Enter Book Title" name="book-title">
    </div>
    <div class="checkbox">
      <label>Publication Type: </label>
      <label><input type="checkbox" name="type-book"> Book</label>
      <label><input type="checkbox" name="type-journal"> Journal</label>
      <label><input type="checkbox" name="type-conference"> Conference</label>
    </div>
    <div class="form-group">
      <label for="book-journal">Journal:</label>
      <input type="text" class="form-control" id="book-journal" placeholder="Enter Journal" name="book-journal">
    </div>
    <div class="form-group">
      <label for="book-year">Year:</label>
      <input type="text" class="form-control" id="book-year" placeholder="Enter Year" name="book-year">
    </div>
    <div class="form-group">
      <label for="book-authors">Author(s):</label>
      <input type="text" class="form-control" id="book-authors" placeholder="Use commas as separators ','" name="book-authors">
    </div>
    <div class="form-group">
      <label for="book-volume">Volume:</label>
      <input type="text" class="form-control" id="book-volume" placeholder="Enter Volume" name="book-volume">
    </div>
    <button type="submit" class="btn btn-primary">Search</button>
  </form>
</div>

</body>
</html>
