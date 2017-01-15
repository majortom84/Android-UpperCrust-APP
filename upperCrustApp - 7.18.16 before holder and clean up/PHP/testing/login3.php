<?php

$user = "root";
$pass = "";
$db = "testdb";

$db = new mysqli("localhost", $user, $pass, $db) or die("did not work");

echo "<br>it connected";

$email = "test";
$pass1 = "test1";
$qry = 'SELECT * FROM users WHERE email = "'. $email .'" AND password = "'.$pass1.'"';

$result = mysqli_query($db, $qry) or die(" did not query");
$info = mysqli_fetch_array( $result );
$count = mysqli_num_rows($result);

if( $count > 0){
	//echo " <br><br> found user";
	// Print out the contents of the entry 
	//echo "<br><b>Name:</b> ".$info['email'] . " ";
	//echo "<b>Password:</b> ".$info['password'] . " <br>";
	echo $result;
	
	}
else 
	echo " <br><br> did not find user or password";
	
?>