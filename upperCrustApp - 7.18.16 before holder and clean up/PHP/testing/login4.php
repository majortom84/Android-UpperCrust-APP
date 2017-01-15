<?php

$user = "root";
$pass = "";
$db = "testdb";

$db =  mysqli_connect("localhost", $user, $pass, $db) or die("did not work");


$email=$_POST['username'];

$qry = 'SELECT * FROM users WHERE email = "'. $email .'"' ;

$result = mysqli_query($db, $qry) or die(" did not query");

$count = mysqli_num_rows($result);
$json = array();
if($count > 0){
while($row = mysqli_fetch_assoc($result))
       {
       $output[]=$row;
       
       }
       
       echo json_encode($output);
	   mysqli_close($db);
}
else 
	echo json_encode(" Could not find user ");
	
?>