
<!DOCTYPE html> <!-- start of the html file -->
<html> <!-- start of the html page -->
<head> 
	<!-- meta data with informaiton-->
    <meta charset="UTF-8">
    <meta name="description" content="COMP 3340 Assignment 4">
    <meta name ="keywords" content ="histogram, programming languages, form, stats">
    <meta name="author" content="Keerthana Madhavan">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>     
	
			.myDiv{
			  margin-top: 39px;
			  border: 5px outset red;
  			  background-color: lightblue;   
			  width: 300px; 
			  float: left;
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
			  width: 300px;
			  float: left;
			}
            #form_error{
	        color: red;
            }
       
  </style>
</head >

<body > 
    <h1> Survey Form</h1>
	<a href=""> clear data this is used to test other functionality. It was annoying to restart server ;)</a>
    <div class="myDiv"> 
    <?php
		//variables to check if the field value is empty or not
		$fname_empty = $lname_empty = $age_empty = $progLang_empty  = false;
		//variables to check for local storage. 
		$fname_session = $lname_session = $age_session = $progLang_session = true;
        //collect all the information from the form 
		$firstName = @$_POST["fname"];                    
        $lastName = @$_POST["lname"];                    
        $age_int = @$_POST["age"];                    
        $progLang = @$_POST["language"]; 
        $storeLanguage = @$_POST["Langs"];
        $storeAge = @$_POST["storeAge"];
		
		//using the post method to submit the form 
		if(isset($_POST["submit"])){

			// the series of if statements will check for empty fields 
			if (empty($_POST["fname"])){ //if first name field is empty
				$fname_empty = true;		
				$fname_session = false;		
				print_r("<p id ='form_error'>!!!! First name is Required !!!!</p>");
			}
			if (empty($_POST["lname"])){ //if last name is doesnt have a value
				$lname_empty = true;		
				$lname_session = false;		
				print_r("<p id ='form_error'>!!!! Last name is Required !!!!</p>");	//print the form_error
			}
			if (empty($_POST["age"])){ // if the age isnt filled
				$age_empty = true;		//turn the form_error on (red * and label)
				$age_session = false;		//clear the field
				print_r("<p id ='form_error'>!!!!  age is Required !!!!</p>");	//print the form_error
			}
			if (empty($_POST["language"])){
				$progLang_empty = true;		//turn the form_error on (red * and label)
				$progLang_session = false;		//clear the field
				print_r("<p id ='form_error'>!!!! language is Required !!!!</p>");	//print the form_error
			}       
			//if the field values are not empty
			if (!empty($_POST["age"]) && !empty($_POST["fname"]) && !empty($_POST["lname"]) && !empty($_POST["language"]))
            {
				//for new submission reset empty variables to false. 
				$fname_empty = $lname_empty = $age_empty = $progLang_empty = false;
				//check the age 
				$checkAge = $_POST["age"];
				if($checkAge >= 14 && $checkAge <=120){	// only process if the age is greater than 14 and less than 120
					
					//clear the fields
					$fname_session = $lname_session = $age_session = $progLang_session = false;
					
					//get age value for the field
					$getAge = $_POST["age"];
					if (!empty($_POST["Ages"])) { // if age field is not empty
						$storeAge = explode(",",$_POST["Ages"]); //stores all the varialble value age into an array. 
						array_push($storeAge, $getAge);			//add  new ages to the array to calculate to the avg age
					}
					else{
						$storeAge = array($getAge);
					}
					
					//this will take care of caps and lower 
					$getLanguage = preg_replace('/\s+/','',strtolower(htmlspecialchars($_POST["language"])));
					
					
					//if the hidden input for the languaes has a value
					if (!empty($_POST["Langs"])) {
						$storeLanguage = explode(",",htmlspecialchars($_POST["Langs"])); //this will manipulate the txt
						
						//strip the text with the delimitter comman and add them to the storeeLanguage array. 
						if(strpos($getLanguage, ',') !== -1 ) {
							// this will have a elements of languages. 
							$storeLanguage = array_merge($storeLanguage,array_unique(explode(",",$getLanguage))); 
						}
						else{// if there is no comma, then only one languages added by the users
							array_push($storeLanguage, $getLanguage); //add language to the storelanguage array
						}
					}
					else{//if empty field 
						$storeLanguage = array_unique(explode(",",$getLanguage)); 
					}
				}
				//if person is younger than 14 or older than 120 
				else{
					if($checkAge < 14){
						print_r("<p id='form_error'>Persons under the age of 14</p>"); 	//tell them they dont qualify
					}
					if($checkAge >120)
					{
						print_r("<p id='form_error'>Persons over the age of 120</p>");	
					}
					$age_empty = true; //sets the age empty variable to true to check for emptty fields
					$age_session = false;
					//add to local storage
					$storeLanguage = explode(",",$_POST["Langs"]); 			
					$storeAge = explode(",",$_POST["Ages"]);			
				}				
			}
			else{
				$storeLanguage = explode(",",$_POST["Langs"]); 			//save in session 
				$storeAge = explode(",",$_POST["Ages"]);			//save in session
			}
			
		}
	?>
    <form action="index.php" method= "post">
		<h2> User Survey Form</h2>
        <div class="firstName"> <!--label and text field print the highlighted test with red and star with field errors-->
                <label  
						id ="<?php 
						 echo ($fname_empty)?"form_error":"";?>"
					   for="firstName"><?php echo ($fname_empty)?"*":"";?>First Name: </label><br>
                <input type="text"  name="fname" placeholder="enter text" value="<?php echo ($fname_session)?"$firstName":"";?>">
        </div>  <br>
        <div class="lastName">
                <label id ="<?php echo ($lname_empty)?"form_error":"";?>" for="lname"><?php echo ($lname_empty)?"*":""; ?>Last Name: </label><br>
                <input type="text" name="lname" placeholder="enter text" value="<?php echo ($lname_session)?"$lastName":"";?>">
        </div> <br>
        <div class="age"> <!-- this text field will get all the age-->
                <label id ="<?php echo ($age_empty)?"form_error":"";?>" for="age"><?php echo ($age_empty)?"*":"";?>Age: </label><br>
                <input type="age" name="age"  value="<?php echo ($age_session)?"$age_int":"";?>">
        </div>
        <br>
        <div class="textField"> <!-- this text field will get all the languages-->
                <label id ="<?php echo ($progLang_empty)?"form_error":"";?>" for="subject"><?php echo ($progLang_empty)?"*":"";?>Languages:</label><br>
                <textarea name="language" placeholder="enter languages: php, java, ..." style="height:150px"><?php echo ($progLang_session)?"$progLang":"";?></textarea>
        </div>
        <div>	
        <input type="hidden" name="Ages" value= "<?php // add age session storage
			echo (empty($storeAge))?"":implode(",", $storeAge);?>">
        </div>
        <div>
        <input type="hidden" name="Langs" value= "<?php //add language session storage
			echo (empty($storeLanguage))?"":implode(",", $storeLanguage);?>">
        </div>
        <div class="button"><!--when clicked this button -->
            <input type="submit" value="Submit" name="submit">
        </div>
     </form>
	 </div>
    <br><br>
	<?php
	
	//get rid of any "" values from the ages list and the langs list
	$storeAge = array_diff($storeAge,array(""));
	$storeLanguage = array_diff($storeLanguage,array(""));
	//draw the graph 
	function drawGraph($age_int){
		for($x = 0; $x < $age_int; $x++){ //given the number print that many Os
			print_r("+");
		}
	}
	//if all the fields were filled, and there is at least one entry 
	if(!empty($_POST["age"]) && !empty($_POST["fname"]) && !empty($_POST["lname"]) && !empty($_POST["language"]) && !empty($storeAge)){	
		//initialize statistics to 0		
		$numForms = $avgAge = $under18 = $over65 =  0;
		//number of participants is equal to the number of ages input
		$numForms = count($storeAge);
		//calculates average age, how many people are under 18, and how many are over 65
		foreach($storeAge as $a){
			$avgAge += $a; //add age to average age variable
			if($a < 18){	//if under 18, add 1 to the under 18 variable
				$under18++;
			}
			elseif($a >= 65){ //if over 65, add 1 to the over 65 variable
				$over65++;
			}
		}
		//divide all the sum of all the ages by the number of participants to get the average age
		$avgAge = $avgAge/count($storeAge);
		//use a table to format outputted text properly
		print_r("<div class='myDiv1'>");
		print_r("<h2>Results Display</h2>");
		print_r("Number of Participants: ");
		if(empty($storeAge)){
			print_r("0"); // print 0
		}
		//otherwise print the number of ages in the array
		else{
			print_r("$numForms");
		}
		//print the live survey in new line. 
		print_r("<br>");
		print_r("Average Age of Participants: ");
		print_r("$avgAge");
		print_r("<br>");
		print_r("Number of Participants Under 18: ");
		print_r("$under18");
		print_r("<br>");
		print_r("Number of Participants 65 and Over: ");
		print_r("$over65");
		print_r("<br>");
		print_r("</div>");
		print_r("<div class='myDiv2'>");
		$histogram = array();
		//go trough the languages array. 
		foreach($storeLanguage as $list){
			//if an lang exist in array append a count to it. 
			if (array_key_exists($list,$histogram)){	
				$histogram[$list] += 1;
			}
			else{
				$histogram[$list] = 1;
			}
		}
		print_r("<h2>Survey Results</h2>");
		//print the histogram by going through every entry in histogram array
		foreach(array_keys($histogram) as $list){
				print_r("<p>");
					print_r("$list | ");
					drawGraph(($histogram[$list]/$numForms)*10);
					$percent = round(($histogram[$list]/$numForms)*100);
					print_r("$percent%");
					print_r("<br>");
				print_r("</p>");	
		}	
		print_r("</div>");
	}
	?>
</body>
</html>