<?php

$user = "root";
$pass = "";
$db = "testdb";

$db = new mysqli("localhost", $user, $pass, $db) or die("did not work");

//echo "<br>it connected";

$email = "test";
$pass1 = "test1";

//$email=$_POST['username'];
//$pass1=$_POST['password'];

$qry = 'SELECT * FROM users WHERE email = "'. $email .'" AND password = "'.$pass1.'"';

$result = mysqli_query($db, $qry) or die(" did not query");

$count = mysqli_num_rows($result);

if( $count > 0)
	//echo " <br><br> found user";
	echo 1;
else 
	//echo " <br><br> did not find user or password";
	echo 0;
	
?>