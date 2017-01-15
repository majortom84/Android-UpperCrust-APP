<?php
    
    if(isset($_REQUEST['user']))
       {
       $con = mysql_connect("localhost","root","");
       if (!$con)
       {
       die('Could not connect: ' . mysql_error());
       }
       mysql_select_db("TestDatabase", $con);
       
       $name = $_REQUEST['user'];
       
       $result = mysql_query("SELECT * FROM Customer WHERE FirstName = '$name' ") or die('Errant query:');
       
       
       while($row = mysql_fetch_assoc($result))
       {
       $output[]=$row;
       
       }
       
       print(json_encode($output));
       
       mysql_close($con);
       }
    else
       {
       $output = "not found";
       print(json_encode($output));
       }




?>