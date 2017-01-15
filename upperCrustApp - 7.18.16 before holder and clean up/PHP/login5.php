<?php

$user = "ab73953_test";
$pass = "H3@ther78";
$db = "ab73953_testdb";

$db =  mysqli_connect('localhost', $user, $pass, $db) or die("did not work");


$email=$_POST['username'];
$email = "thomas@wiregrass.edu"; // testing 

$qry = 'SELECT * FROM users WHERE email = "'. $email .'"' ;

$result = mysqli_query($db, $qry) or die(" did not query");

$count = mysqli_num_rows($result);
$output = array();
if($count > 0){
while($row = mysqli_fetch_assoc($result))
       {
       $output[]=$row;
       }
       
       echo json_encode($output);
	   
}
else 
	echo json_encode("Could not find user");
	
mysqli_close($db);
?>