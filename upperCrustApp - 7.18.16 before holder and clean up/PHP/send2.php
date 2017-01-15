<?php

$user = "root";
$pass = "";
$db = "testdb";

$db = new mysqli("localhost", $user, $pass, $db) or die("did not work");

$email=$_POST['email'];
$password=$_POST['password'];
$fname=$_POST['fname'];
$lname=$_POST['lname'];
$phone=$_POST['phone'];
$temppass=$_POST['tempPass'];
$alerttype=$_POST['alert'];

/*$email="@wiregrass.edu";
$password="12345678";
$fname="testfirst";
$lname="testlast";
$phone="1112223333";
$temppass="true";*/

/*$sql = "INSERT INTO users (email, password, fname, lname, phone, temppass)
VALUES (	'$email',
					'$password',
					'$fname',
					'$lname',
					'$phone',
					'$temppass')";*/


$sql = "INSERT INTO users (email, password, fname, lname, phone, temppass, alert)
VALUES (	'".$email."',
					'".$password."',
					'".$fname."',
					'".$lname."',
					'".$phone."',
					'".$temppass."',
					'".$alerttype."')";

$insert = mysqli_query($db,$sql);

if (!$insert) {
    echo "0";
} else {
    echo "1";
}

/*if (!$insert) {
    echo "0 : not created";
} else {
    echo "1 : created";
}*/

/*if ($db->query($sql) === TRUE) {
    echo "0";
} else {
    echo "1";
}*/

$db->close();
	
?>