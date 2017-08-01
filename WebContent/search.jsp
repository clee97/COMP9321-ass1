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
      <label for="book-publisher">Publisher:</label>
      <input type="text" class="form-control" id="book-publisher" placeholder="Enter Publisher" name="book-publisher">
    </div>
    <div class="form-group">
      <label for="book-year">Year:</label>
      <input type="text" class="form-control" id="book-year" placeholder="Enter Year" name="book-year">
    </div>
    <div class="form-group">
      <label for="book-isbn">IBSN:</label>
      <input type="text" class="form-control" id="book-isbn" placeholder="In the form " name="book-isbn">
    </div>
    <div class="form-group">
      <label for="book-author">Author(s):</label>
      <input type="text" class="form-control" id="book-author" placeholder="Use commas as separators ','" name="book-author">
    </div>
    <div class="form-group">
      <label for="book-venue">Venue:</label>
      <input type="text" class="form-control" id="book-venue" placeholder="Enter Venue" name="book-venue">
    </div>
    <button type="submit" class="btn btn-primary">Search</button>
  </form>
</div>

</body>
</html>
