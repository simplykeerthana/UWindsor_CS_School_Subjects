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
            #form_error{  color: red;}
			#success{	color: green;}
  </style>
</head >

<body > 
		<form action="" method="POST">
            <input type="submit" value="Add" name="selected_control">
            <input type="submit" value="Edit" name="selected_control">
            <input type="submit" value="Delete" name="selected_control">
        </form>
<?php

	@$_SESSION['selected_control'] = $_POST['selected_control']; // when the user_sel selects from the control menu, then we need to remember what he/she chose.
	//if the add users button was clicked in the add form
	if(isset($_POST['selected_add'])){
		//check if the fields are filled
		if(!empty($_POST['fname']) && !empty($_POST['lname']) && !empty($_POST['email'])){
			//read and save values
			$email = htmlspecialchars(trim($_POST['email']));
			$fname = htmlspecialchars(trim($_POST['fname']));
			$lname = htmlspecialchars(trim($_POST['lname']));
			$query = "select * from users where email='$email' AND fname='$fname' AND lname='$lname'"; 	//create query to count all rows that have that email
			$result = mysqli_query($connection_db, $query);							//run the query in the database
			$check_email = $result->num_rows;
			//add the email if it doesnt exist
			if($check_email<=0){
				//creating the sql statement tto add the first, last name and email to the db
                $sql = "INSERT INTO users( fname, lname, email) VALUES ('$fname', '$lname', '$email')";
                if ($connection_db->query($sql) === TRUE) {
                    echo "<p id='success'> New user created successfully </p>";
                  } else {
                    echo "Error: " . $sql . "<br>" . $connection_db->error;
                  } //   $connection_db->close();
			}
			else{
				echo "<p id ='form_error'>User Already exists!</p>";
			}
		}
		//if empty form is submittted tell the user
		else{
			if(empty($_POST['fname'])){
				echo "<p id='form_error' >enter fist name...</p>";
			}
			if(empty($_POST['lname'])){
				echo "<p id='form_error' >enter last name...</p>";
			}
			if(empty($_POST['email'])){
				echo "<p id='form_error' >enter email...</p>";;
			}
		}
	}
	//when the edit form is selectted
	else if(isset($_POST['selected_edit'])){
		$change_user = explode(",",$_POST['change_user']);//get user info to change 
		if(!empty($_POST['edit_fname'])){ //if the user wants to change first name
			//update with value specified with the below sql statement
			$sql = "UPDATE users SET fname='".(htmlspecialchars(trim($_POST['edit_fname'])))."' WHERE id=".$change_user[0];
			if (mysqli_query($connection_db, $sql)) { //connect and update
			  echo "<p id='success'> First Name updated successfully </p>";
			} else {
			  echo "Error updating record: " . mysqli_error($connection_db);
			}
		}
		if(!empty($_POST['edit_lname'])){//if the lname value was entered
			//update with value specified with the below sql statement
			$sql = "UPDATE users SET lname='".(htmlspecialchars(trim($_POST['edit_lname'])))."' WHERE id=".$change_user[0];
			if (mysqli_query($connection_db, $sql)) {//connect and upadate
			  echo "<p id='success'> Last Name updated successfully </p>";
			} else {
			  echo "Error updating record: " . mysqli_error($connection_db);
			}
		}
		if(!empty($_POST['edit_email'])){//if the user want to edit the email 
			//update with value specified 
			$sql = "UPDATE users SET email='".(htmlspecialchars(trim($_POST['edit_email'])))."' WHERE id=".$change_user[0];
			if (mysqli_query($connection_db, $sql)) { //connect and update
				echo "<p id='success'>Email updated sucessfuuly</p>";
			} else {
			  echo "Error updating record: " . mysqli_error($connection_db);
			}
		}
	}
	//when the delete form is selectted
	else if(isset($_POST['submitDelete'])){
		//save user information that needs to be deleted
		$del_user = $_POST['del_user'];
		$user_sel = explode(',',$_POST['del_user']);
		//with deleting the user you should also delete the user appointment asssociated with the id
		$sql = "DELETE FROM appointment WHERE id=".($user_sel[0]).";";
		if (mysqli_query($connection_db, $sql)) {
		  echo "<p id='success'>Appointments successfully Removed!</p>";
		} else {
		  echo $sql;
		  echo "Error updating record: " . mysqli_error($connection_db);
		}
		$sql = "DELETE FROM users WHERE id=".($user_sel[0]).";"; // delete the user from user db
		if (mysqli_query($connection_db, $sql)) { //runt the sql query
		  echo "<p id='success'>User successfully Removed!</p>";
		} else {
		  echo $sql;
		  echo "Error updating record: " . mysqli_error($connection_db);
		}	
		//remove user from local storage
		$_SESSION['users'] = array_diff($_SESSION['users'], array($del_user));
	}
	//show add form 
	if($_SESSION['selected_control'] == 'Add'){
		echo '
		<h3>Add User</h3>
		<form action="" method="POST">
			<div id = "fname">
				<label>First Name:</label>
				<input type="text"  name="fname" placeholder="first name...">
			</div>
			<div id = "lname">
				<label>Last Name:</label>
				<input type="text"  name="lname" placeholder="last name...">
			</div>
			<div id="email">
				<label>Email:</label>
				<input type="email"    name="email" placeholder="email here..">
			</div>
			<div id = "submit">
				<input type="submit" value="Add User" name="selected_add" >
			</div>
		</form>';
		
	}
	//show edit form  
	else if($_SESSION['selected_control'] == 'Edit'){
		echo '
		<h3>Edit User</h3>
		<form action="" method="POST">
			<div>
			<p> only enter fields you want to change </p>
				<label>Choose a User To Edit:</label>
				<select id="users" name="change_user">
				';
		foreach($_SESSION['users'] as $user){
			echo '<option value ="'.($user).'">'.($user).'</option>';
		}	
		echo    '</select>
			<div>
			<div id = "fname">
				<label>Edit First Name:</label>
				<input type="text"  name="edit_fname" >
			</div>
			<div id = "lname">
				<label>Edit Last Name:</label>
				<input type="text"  name="edit_lname" >
			</div>
			<div id="email">
				<label>Edit Email:</label>
				<input type="text"  name="edit_email" >
			</div>
			<div id = "submit">
				<input type="submit" value="Edit User" name="selected_edit" >
			</div>
		</form>';
		
	}
	//show delete form 
	else if($_SESSION['selected_control'] == 'Delete'){
		echo '
		<h3>Delete User</h3>
		<form action="" method="POST">
			<div>
				<label>Choose a User:</label>
				<select id="users" name="del_user">
				';
		foreach($_SESSION['users'] as $user){
			echo '<option value ="'.($user).'">'.($user).'</option>';
		}	
		echo    '</select>
			<div>
			<div id = "submit">
				<input type="submit" value="Delete User" name="submitDelete" >
			</div>
		</form>';
	}
	
	echo '<br>';
	echo "<h2> User List: </h2>";
	//get all active users to display 
    $sql1 = "SELECT * FROM users";
    $result1 = $connection_db->query($sql1);
	if ($result1->num_rows > 0) {
		$_SESSION['users'] = array();
		// output data of each row
		while($row = $result1->fetch_assoc()) {
			//add them to local storage to display when the page refreshes. 
			array_push($_SESSION['users'],"".($row['id']).",".($row['fname']).",".($row['lname']).",".($row['email']));
				$_SESSION['ids']["".($row['id'])]= ($row['fname']).",".($row['lname']);
			echo "<br> id: ". $row["id"]. " - Name: ". $row["fname"]. " " . $row["lname"] .  " Email: ". $row["email"]." .<br>";
		}
		
	} else {echo "0 results";
	}
	// $connection_db->close();
	?>	
</body>
</html>	