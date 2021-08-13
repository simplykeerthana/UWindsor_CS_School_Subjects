<!DOCTYPE html> <!-- start of the html file -->
<html> <!-- start of the html page -->
<head> 
	<!-- meta data with informaiton-->
    <meta name="author" content="Keerthana Madhavan">
    <style>     
			.myDiv {
					border: 5px outset red;
					background-color: lightblue;    
				}
            #form_error{  color: red;}
			#success{	color: green;}
  </style>

<script>

</script>
</head >

<body > 
	<form action="" method="POST"><!--buttons to change the appp option-->
		<input type="submit" value="Add" name="appointment" >
		<input type="submit" value="Edit" name="appointment" >
		<input type="submit" value="Delete" name="appointment" >
	</form>
<?php	
	@$_SESSION['appointment'] = $_POST['appointment']; //save to local storage, the selected button value
	if(isset($_POST['submitEditapp'])){ //if edit is selected. 
			$person = $_SESSION['EditpersonApp']; // choose the person 
			$previous_appointment = explode("-",$_POST[$person[0]."edit_appointment"]); // which appointment they want to change
			$weekday = htmlspecialchars(trim($_POST['edit_appointment_day'])); //set new weekday
			$time = htmlspecialchars(trim($_POST['edit_appointment_time'])); //set new time
			//check if the appointment is already taken
			$query = "select * from appointment where weekday='$weekday' AND hour='$time';"; //create query
			$result = mysqli_query($connection_db, $query); //run the query to get info from db
			$check_time = $result->num_rows;
			if ($check_time > 0){//if the time is not available
				echo "<p>That time slot is taken. choose something else</p>";
			}
			else{//use update statement to set day, time and user that chose the time. 
				$sql = "UPDATE appointment SET weekday='".($weekday)."',hour=".($time)." WHERE id=".($person[0])." AND weekday='".($previous_appointment[0])."' AND hour=".($previous_appointment[1]).";";
				if ($connection_db->query($sql) === TRUE) {
				  echo "<p id='success'> Appointment updated successfully</p>";
                 
				} else {
				  echo "Couldn't update:  " . mysqli_error($connection_db);
				}
			}
	}
	// if delete appointmeent is selected. 
	else if(isset($_POST['submitDeleteapp']))
    {
			$person = $_SESSION['delete_user_appointment'];
			$current_Appointment = explode("-",$_POST[$person[0]."delete_Appointment"]);//get the appointment info to delete
			$sql = "DELETE FROM appointment WHERE id=".($person[0])." AND weekday='".($current_Appointment[0])."' AND hour=".($current_Appointment[1]).";";
			//run query 
			if ($connection_db->query($sql) === TRUE) {
			  echo "<p id='success'>Appointment successfully Removed!</p>";
			} else {
			  echo $sql;
			  echo "Error updating record: " . mysqli_error($connection_db);
			}
	}
	if($_SESSION['appointment'] == 'Add'){ // if add appointment
		echo '
		<p>Add a time Slot</p>
		<form action="" method="POST">
			<div>
				<label>Choose a User:</label>
				<select id="usersAddapp" name="usersAddapp">
				';
                //show them user options to choose from 
			foreach($_SESSION['users'] as $user){
				echo '<option value ="'.($user).'">'.($user).'</option>';
			}	
		echo    '</select>
			</div>
			<div>
				<label>Choose a day:</label>
				<select id="day_appointment_chosen" name="day_appointment_chosen">
				';
			foreach(range(0,4) as $day){ //return mon-fri
				echo '<option value ="'.(jddayofweek($day,1)).'">'.(jddayofweek($day,1)).'</option>';
			}
		echo    '</select>
			</div>
			<div>
				<label>Choose a time:</label>
				<select id="timeAddapp" name="timeAddapp">
				';
			$times = array(8,9,10,11,12,1,2,3); //8:30 - 3 interval by 1 hour
			foreach($times as $time){
				echo '<option value ="'.($time).':30">'.($time).':30 to '.($time+1).':30</option>';
			}	
		echo	'
				</select>
			</div>
			<div id = "submit">
				<input type="submit" value="Add appointment" name="create_appointment" >
			</div>
		</form>';
	}
	// ask which user they want to edit info for
	else if($_SESSION['appointment'] == 'Edit'){
		echo '
		<p>Edit a time Slot</p>
		<form action="" method="POST">
			<div>
				<label>**Choose a User:</label>
                <br>
				<select id="usersEditapp" name="usersEditapp">
				';
			foreach($_SESSION['users'] as $user){
                
				echo '<option value ="'.($user).'">'.($user).'</option>';
			}	
		echo    '</select>
			</div>
            <br>
			<div id = "submit">
            <br>
				<input type="submit" value= " show appointments for selcted User" name="edits_appointment" >
			</div>
		</form>';
        echo ' ';
	}
	// ask which user they want to delete
	else if($_SESSION['appointment'] == 'Delete'){
		echo '
		<p>Delete a time Slot</p>
        <br>
		<form action="" method="POST">
			<div>
				<label>Choose a User:</label>
                <br>
				<select id="user_deleted_app" name="user_deleted_app">
				';
                //get all the uusers form the session. 
			foreach($_SESSION['users'] as $user){
				echo '<option value ="'.($user).'">'.($user).'</option>';
			}	
		echo    '</select>
			</div>
			<div id = "submit">
             <br>
				<input type="submit" value="click to show existing appointments" name="select_deleted" >
			</div>
		</form>';
	}
	
	//if the submit button for adding an appointment is pressed
	if(isset($_POST['create_appointment'])){
		$person = explode(",",$_POST['usersAddapp']);
		$weekday = htmlspecialchars(trim($_POST['day_appointment_chosen']));
		$time = htmlspecialchars(trim($_POST['timeAddapp']));
		//check if the appointment is already taken
		$query = "select * from appointment where weekday='$weekday' AND hour='$time'"; 	//create query to count unique users
		$result = mysqli_query($connection_db, $query);	 //run the query in the database
		$check_day = $result->num_rows;
		//if user is not in the system
		if($check_day<=0){		
			// I had to use this stmt bind because my hour + ":30" string couldnt work as a integer. 
			$sql = "INSERT INTO appointment (weekday,hour,id) VALUES (?,?,?)"; //prepare sql insert statement
			if($stmt = mysqli_prepare($connection_db, $sql)){
				mysqli_stmt_bind_param($stmt, "sii",$weekday , $time, $person[0]);			
				if(mysqli_stmt_execute($stmt)){ 
					echo "<p id='success' >Appointment successfully added for ".($person[1])." ".($person[2])."!</p>";
				} else{
					echo "<p id='error' > Something went wrong. Try again later.</p>".mysqli_error($connection_db);
				}
				mysqli_stmt_close($stmt);
			}		
		}
		else{ 	//if appointment is in the db
			echo "<p id ='error'>Appointment is already taken! choose something else</p>";
		}
	}
	//whhich user you wnat tto delete
	else if(isset($_POST['edits_appointment'])){
		$_SESSION['appointment'] = 'Edit'; //check session option 
		$person = explode(",",$_POST['usersEditapp']); 
		$_SESSION['EditpersonApp'] = $person;
		$query = "select * from appointment where id=".$person[0]; // query all the existing appointment for hte user to edit for. 
		$result = mysqli_query($connection_db, $query);
		$check_user = $result->num_rows;
		if ($result && ($check_user>0)){ //show the edit form. 
	    	echo"<form method='POST' action=''>
					<div>
                    <br>
						<label>Choose a new appoinment for ".($person[1])." ".($person[2])." to Current Time:</label>
                        <br>
						<select name='".($person[0])."edit_appointment'>
				";
			while($row = mysqli_fetch_assoc($result)){
			//print thte options with a list
				echo "<option value='".($row['weekday'])."-".($row['hour'])."'>".($row['weekday']).": ".($row['hour']).":30-".($row['hour']+1).":30</option>";
			}
			echo'	</select>
					</div>
					<div id >
						<label>Edit weekday:</label>
						<select id="edit_appointment_day" name="edit_appointment_day">
				';
			foreach(range(0,4) as $day){
				echo '<option value ="'.(jddayofweek($day,1)).'">'.(jddayofweek($day,1)).'</option>';
			}
			echo'		</select>
					</div>
					<div id = "lname">
						<label>Edit Time:</label>
						<select id="edit_appointment_time" name="edit_appointment_time">
				';
			$times = array(8,9,10,11,12,1,2,3);
			foreach($times as $time){
				echo '<option value ="'.($time).'">'.($time).':30 to '.($time+1).':30</option>';
			}
			echo'		</select>	
					</div>
					<div id = "submit">
						<input type="submit" value="Edit Appointment" name="submitEditapp" >
					</div>
					
				</form>';
		}
		else{ // not in db
			echo "<p id='form-error'>The user has no appointments to edit</p>";
		}
	}
	//appointments to delete for  user
	else if(isset($_POST['select_deleted'])){
		$_SESSION['appointment'] = 'Delete';
		$person = explode(",",$_POST['user_deleted_app']);
		$_SESSION['delete_user_appointment'] = $person;
		$query = "select * from appointment where id=".$person[0]; //get all the appointments for user
		$result = mysqli_query($connection_db, $query);
		$check_user_delete = $result->num_rows;
		if ($result && ($check_user_delete>0)){ //show delete form 
			echo"<form method='POST' action=''>
					<div>
						<label>Choose an appoinment for ".($person[1])." ".($person[2])." to Delete:</label>
						<select name='".($person[0])."delete_Appointment'>
				";
			while($row = mysqli_fetch_assoc($result)){
				
				echo "<option value='".($row['weekday'])."-".($row['hour'])."'>".($row['weekday']).": ".($row['hour']).":30-".($row['hour']+1).":30</option>";
			}
			echo'		</select>	
					</div>
					<div id = "submit">
						<input type="submit" value="Delete Appointment" name="submitDeleteapp" >
					</div>
					
				</form>';
		}
		else{
			echo "<p id='form-error'>Ripp no appointments to delete</p>";
		}	
	}
	?>	
</body>
</html>	