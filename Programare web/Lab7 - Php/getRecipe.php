<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "recipe";

$db = mysqli_connect($servername, $username, $password, $dbname);

	$query = "SELECT * FROM recipe";
	$result = mysqli_query($db, $query);

echo "<table class=\"table table-bordered\" id=\"recipe-table\" border = \"1\">
<tr>
<th>ID</th>
<th>Author</th>
<th>Name</th>
<th>Type</th>
<th>Actual Recipe</th>
</tr>";
while($row = mysqli_fetch_array($result)) {
    echo "<tr>";
    echo "<td>" . $row['id'] . "</td>";
    echo "<td>" . $row['author'] . "</td>";
    echo "<td>" . $row['name'] . "</td>";
    echo "<td>" . $row['type'] . "</td>";
    echo "<td>" . $row['actualrecipe'] . "</td>";
    echo "</tr>";
}
echo "</table>";

?>