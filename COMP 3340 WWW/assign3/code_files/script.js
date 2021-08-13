/*
    Name: Keerthana Madhavan
    Course: COMP 3340, A3
*/
// a multi dimensioanl array  thata hold 12 item s
// an element will have ["image.png", "iteem name", "item caption", price, quantity] default quantitty is 1


//array that holds all the potential items. [0] file name ,[1] Item Name, [2] price, [3] caption, [4]quantity
var item = [
    ["./images/airpods.png",
     "Apple Airpods", 260.00,
      "The only pods that actually fits", 
      1
    ],
    ["./images/baseball_bat.png",
     "Baseball Bat", 80.45,
      "A strong baseball bat for the homerun",
       1
    ],
    ["./images/baseball_glove.png",
    "Baseball Glove",
     50.34, 
     "Heavy duty glove wear",
      1
    ],
    ["./images/basketball.png",
     "Basketball", 
     20.00, 
     "Practice dribblinng and shoots with a practice ball",
      1
    ],
    ["./images/baseball.png", 
     "Baseball",
     65.23, 
     "The leather woven baseball",
     1
    ],
    ["./images/laptop.png",
     "Laptop",
     1200.33,
     "A laptop for CS",
     1
    ],
    ["./images/phone.png",
     "Phone",
      999.33,
      "A phone to pretty much do everything",
      1
    ],
    ["./images/smart_watch.png",
     "Smart Watch",
     300.3,
     "track your movements with these",
     1
    ],
    ["./images/soccerball.png",
     "Soccer Ball",
      45.98,
     "aim for the goal",
     1
    ],
    ["./images/tablet.png",
     "Tablet", 
      500.89,
     "more like a computer and phone in one",
     1
    ],
    ["./images/tennis_set.png",
     "Tennis Raquet and Ball",
     50.45, 
     "A great purchase to practice a healthy sport", 
     1
    ],
    ["./images/tv.png", 
     "TV", 
     700.343, 
     "Just lie down and binge on netflix", 
     1
    ]
];

console.log(item); // this helps visualize where each element is on the browser console

// send the item information based on the element id> 
var itemToTable = [
	document.getElementById("item1"),
	document.getElementById("item2"),
	document.getElementById("item3"),
	document.getElementById("item4"),
	document.getElementById("item5"),
	document.getElementById("item6")
];

var itemInShoppingCart = []; 	

function sendItem(list, itemIndex, item){
	// the innerHTML equal to empty clears all previous content on the item variable
    var itemName, itemImage, itemPrice, itemCaption;
	item.innerHTML = '';											
    itemName =  document.createElement("h2");	//create a heading for the item 
    itemName.classList.add('itemName'); //bolds them
	itemName.innerHTML = list[itemIndex][1];	 //takes the item name from the array
	item.appendChild(itemName);			//appends the item name to the h2 tag
	
	itemImage = document.createElement("img");	//creates a img element to hold the image
    itemImage.classList.add('itemImage'); // resize the image based on this class to 200px
	itemImage.src =  list[itemIndex][0]; //takes the image from the array and add it to image source	
	item.appendChild(itemImage);	//appends the itm name to the img tag				
	
	itemPrice =  document.createElement("p");	// creates a paragraph tag to hold the item price
	itemPrice.innerHTML = "Price $" + list[itemIndex][2]; //Taks the price from array and adds itt to the p tag		
	item.appendChild(itemPrice);	//appends thee pricee to the p tag				
	
	itemCaption =  document.createElement("p");	// creates another p tag to hold the item description
    itemCaption.classList.add('itemCaption');  // makes the text gray
	itemCaption.innerHTML = "Description: " + list[itemIndex][3]; //takes the item pricee from the array and adds it to the p tag		
	item.appendChild(itemCaption);		// appends the caption to the price tag. 			
}

var firstSixItems = '';		//holds the index numbers for all the current items displayed on screen
for(var x = 0; x < 6; x++){															
	var rNum = Math.floor(Math.random()*2)+1;										
	sendItem(item,(2*x)+rNum-1,itemToTable[x]);	// takes a random number and allots that index of  x item to be sent to the table							
	firstSixItems +=  ((2*x)+rNum-1);
    // here based on the the random number when the user clicks on thte images, it adds it to the cart. 											
	itemToTable[x].setAttribute( "onClick", "addItem("+((2*x)+rNum-1)+")");	//checks if 
}

/*
    This function will randomly print the first 6 random items stored in thee firstSixItems variable
    then if view more items button is clickd, then it will print or send to httml the other six items.
*/
function shuffleItems(){
	
	var secondSixItems = [];															
	
	for(var x = 0; x < 12;x++){				
        //sends the non-first six items in the array to the secondSixTtems array.										
		if (!(firstSixItems.includes(','+x+','))){	
            //if by traversing via the 12 items and it doesnt show up in the table
            //then add it to thte second six list. 									
			secondSixItems.push(x); 												
		}
	}
	firstSixItems = '';																	
	for(var x = 0; x < 6; x++){					
        // send the first six items to the first six boxes in table									
		firstSixItems += ","+ secondSixItems[x] + ",";	
        //then also send the second six items, this will show when view more items buttton is clicked. 								
		sendItem(item,secondSixItems[x],itemToTable[x]);							
		itemToTable[x].setAttribute( "onClick", "addItem("+secondSixItems[x]+")");	
		
	}
	
}

/* this function checks if the images is clicked and adds the item 
to the ittemIN shooping cart array that is send to the getItems element
*/
function addItem(num){
	if (itemInShoppingCart.indexOf(item[num]) >=0){						
		itemInShoppingCart[itemInShoppingCart.indexOf(item[num])][4] += 1; // the quantity column get added by one. 		
	}else{itemInShoppingCart.push(item[num]);								
	}

	refreshCart();										
	
}

console.log(itemInShoppingCart);

//tthis fucntion appends the quantity column based on the num of times add_item is called. 
function add_item(index){
	
	itemInShoppingCart[index][4] += 1;	//add +1 if add buttton is clicked on the action 
	refreshCart() // then call the cart to refresh the changes					
    console.log(itemInShoppingCart);
}




//this function will remove an item by -1 if the - button is clickeed in action. 
function remove_item(index){
	
	if (itemInShoppingCart[index][4] - 1 <1){	//if the quantity becomes zero, then just delete the entire item
		del(index);	// calls the function to delete an entire item						
	}
	else{
		itemInShoppingCart[index][4] -= 1; // decrement everytime the delete button in clicked
	}
	refreshCart() //refresh the cart. 
}

//this func deletes a entiree item from the cart
function del(index){
    //first ask the user if they want to delete the item or not
    //this is done by using js function confirm that shows a popup with yes or no ans
	if (confirm("You really want to delete this item... ")) { 
		itemInShoppingCart.splice(index,1);	//removes item from array, just remove the last added
		refreshCart()	//refresh the cart to reflect changes		
        countTotal() // when deleting an item the total of the cart will change too. 
	}
	
}

/* this function add the prices of each item in the cart keeping in mind of the quantity of each item
*/
function countTotal()
{
        var totalCart, total, finalTotal, totalTitle, totalCost; 
        //the variable totalcart will hols all the table elements
        totalCart = document.getElementById("totalCart");	
        totalCart.innerHTML = ''; // this will clear previous contents, so we dont end up creating a table everytime this function is called. 
    								
       cartTotal = 0;			
       //iterate through all the in the existing cart								
        for(var a = 0;a<itemInShoppingCart.length;a++){				
            cartTotal += itemInShoppingCart[a][4] * itemInShoppingCart[a][2];	// the second element of a product is price, so for each price calculate sum			
            cartTotal = Math.ceil(cartTotal * 100) / 100	//this will always round to 2 digit
        }
        
	    finalTotal = document.createElement("tr");	// this will create a new row for total		
		totalTitle = document.createElement("td"); // column that will span 2 columns till price		
		totalTitle.setAttribute( "colspan", "2");			
		totalTitle.innerHTML = "Total: ";		//name the column totak			
		finalTotal.appendChild(totalTitle); //append the thee title to the tag
		totalCost = document.createElement("td");		// create another column called total 
		totalCost.innerHTML = "$"+cartTotal;	// this column will hold the total price content
        finalTotal.appendChild(totalCost);				// append the total to the tag
        console.log("the total cost" + cartTotal);		// just checking to see if the total logic is working or not. 
		
        totalCart.appendChild(finalTotal);
}


countTotal()

