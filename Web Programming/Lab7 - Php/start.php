<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    
    <style type="text/css">
        body{ font: 14px sans-serif; text-align: center; }
    </style>
</head>
<body>
    <form  action="functionalities.php" method="post">
		<div class="form-group" id="add-new-gbentry-div">
            <input id="id-input" type="text" name="id" placeholder="Id">
			<br>
			<input id="author-input" type="text" name="author" placeholder="Author">
			<br>
			<input id="name-input" type="text" name="name" placeholder="Name">
			<br>
			<input id="type-input" type="text" name="type" placeholder="Type">
            <br>
			<input id="actualrecipe-input" type="text" name="actualrecipe" placeholder="Actual Recipe">
            <br>
            <button id="add-gbentry-button" type="submit" name="submit_add_entry" class="btn btn-success">Add</button>
            <button id="update-gbentry-button" type="submit" name="submit_update_entry" class="btn btn-warning">Update</button>
		    <button id="delete-gbentry-button" type="submit" name="submit_delete_entry" class="btn btn-danger">Delete</button>
            
        </div>
	</form>
    <h2>All Recipes:</h2>
    <?php include('getRecipe.php'); ?>
    
	
	<button id="filter-gbentry-button" type="submit" name="submit_filter_entry" class="btn btn-primary">Filter by type</button>
    <input id="typeInput" type="text" name="type" placeholder="type">
    <div id="table1"> 
        
    </div>
    <script src="jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="text/javascript" src="main.js"></script>
</body>
</html>