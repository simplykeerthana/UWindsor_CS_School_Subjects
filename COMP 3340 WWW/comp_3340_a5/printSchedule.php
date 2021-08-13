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
				}
                #free{
                    background-color: #00FF00;
                    text-align: center;
                }
                #taken{
                    background-color: #f77f77;
                    text-align: center;
                }
  </style>
</head >

<body > 
<?php
	//display the schedule 
	$db_timeslots = array();
	$query = "select * from appointment;";
	$result = mysqli_query($connection_db, $query);
	$num_slots = $result->num_rows;
	if ($num_slots>0){
		//for ever entry save in the timeslots array
		while($row = mysqli_fetch_assoc($result)){
			$db_timeslots[$row['weekday']][$row['hour']] = $row['id'];
		}
	}
	//if no appointments are taken tell the user
	else{
		echo "<p> Time slots available</p>";
	}
	//print the weekdays
	echo"<table>
			<tr>
				<th><b></b></th>
				<th><b>Monday</b></th>
				<th><b>Tuesday</b></th>
				<th><b>Wednesday</b></th>
				<th><b>Thursday</b></th>
				<th><b>Friday</b></th>
				
			</tr>
	";
	$times = array(8,9,10,11,12,1,2,3);
	foreach ($times as $hour){
		echo"<tr>";
		//iterate through the weekdays
		foreach (range(-1,6) as $weekday){
			if($weekday == -1){ // print weekdays
				echo"<th>".($hour).":30 - ".($hour+1).":30</th>";
			}
			else{ //print red or green color based on taken or not, jddayofweek is a php func that returns current day of week. 
				if(isset($db_timeslots[jddayofweek($weekday,1)][$hour])){
					$if_taken = explode(',',$_SESSION['ids'][$db_timeslots[jddayofweek($weekday,1)][$hour]]);
					echo"<td id='taken'>".substr($if_taken[0],0,1)." ".$if_taken[1]."</td>";
				}
				else{//slot is free
					echo"<td id='free'></td>";
				}
			}
		}
		echo"</tr>";
	}
	echo"<table>";
	
	?>
    	
</body>
</html>	