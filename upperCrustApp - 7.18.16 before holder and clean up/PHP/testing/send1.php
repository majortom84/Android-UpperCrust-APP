<?php

$user = "root";
$pass = "";
$db = "testdb";

$db = new mysqli("localhost", $user, $pass, $db) or die("did not work");



$sql = "INSERT INTO users (email, password)
VALUES ('test email','password')";


if ($db->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $db->error;
}

$db->close();
	
?>