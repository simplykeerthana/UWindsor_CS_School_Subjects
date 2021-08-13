
<!DOCTYPE html> <!-- start of the html file -->
<html> <!-- start of the html page -->
<head> 
	<!-- meta data with informaiton-->
    <meta charset="UTF-8">
    <meta name="description" content="COMP 3340 Assignment 5">
    <meta name ="keywords" content ="add, appointment, users, delete">
    <meta name="author" content="Keerthana Madhavan">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>     
	.myDiv {
            border: 5px outset red;
            background-color: lightblue;   
            padding: 10px;
    
			    float: left; 
            width: auto;
        }
        .myDiv0 {
            border: 5px outset red;
            background-color: lightblue;   
            padding: 10px;
            text: center;
    
			    
        }

			.myDiv1{
			  border: 5px outset red;
  			  background-color: lightblue;    
			  padding: 10px;
			  width: 300px;
			  float: left;
			}
			.myDiv2{
			  border: 5px outset red;
  			  background-color: lightblue;    
			  width: auto;
			  float: left;
			}
            #form_error{
	        color: red;
            }
       
  </style>


<?php
// connect to db
session_start();

$host = "localhost";
$user_name = "madhava1_madhava1A5";
$password = "password_a5";
$database = "madhava1_madhava1A5";
//creating the connection 
$connection_db = mysqli_connect($host, $user_name, $password, $database);

//if the connection fails
if(!$connection_db)
{
    die("Failed to connect: ". mysqli_connect_error());
}


?>
</head >

<body > 
    <h1 class="myDiv0"> User Appointment Schedule</h1>

  <div  class="myDiv">
      
        <h2>User Control Menu: </h2>

        
        
        <?php
	
        include 'users.php';
      ?>
 </div>

 <div  class="myDiv1">
      
        <h2>Schedule Control Menu: </h2>

        
        <?php
	
  include 'appointments.php';
?>    
 </div>
 <div  class="myDiv2">
        <h2>Final Schedule: </h2>
        <?php include 'printSchedule.php'; ?>     
 </div>
	
</body>
</html>
