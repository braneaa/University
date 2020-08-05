<?php


$servername = "localhost";
$username = "root";
$password = "";
$dbname = "recipe";

$db = mysqli_connect($servername, $username, $password, $dbname);

if (isset($_POST['submit_add_entry'])) {
    $author = mysqli_real_escape_string($db, $_POST['author']);
    $name = mysqli_real_escape_string($db, $_POST['name']);
    $type = mysqli_real_escape_string($db, $_POST['type']);
    $actualrecipe = mysqli_real_escape_string($db, $_POST['actualrecipe']);
    $query = "INSERT INTO recipe (author, name, type, actualrecipe) VALUES ('$author', '$name', '$type', '$actualrecipe')";
    mysqli_query($db, $query);
    header('location: start.php');
}

if (isset($_POST['submit_update_entry'])) {
    $id = mysqli_real_escape_string($db, $_POST['id']);
    $author = mysqli_real_escape_string($db, $_POST['author']);
    $name = mysqli_real_escape_string($db, $_POST['name']);
    $type = mysqli_real_escape_string($db, $_POST['type']);
    $actualrecipe = mysqli_real_escape_string($db, $_POST['actualrecipe']);
    $query = "UPDATE recipe SET author = '$author', name = '$name', type = '$type', actualrecipe = '$actualrecipe' WHERE id = '$id'";
    mysqli_query($db, $query);
    header('location: start.php');
}

if (isset($_POST['submit_delete_entry'])) {
    $id = mysqli_real_escape_string($db, $_POST['id']);
    $query = "DELETE FROM recipe WHERE id = '$id'";
    mysqli_query($db, $query);
    header('location: start.php');
}


?>