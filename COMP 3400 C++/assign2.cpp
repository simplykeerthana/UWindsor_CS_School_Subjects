/*
    Name: Keerthana Madhavan
    Student ID: 104995097
    COMP 3400: Assignment 2
    Date: 25/01/2020
 */

#include <cmath>      // to use std::sqrt
#include <array>      // to use std::array
#include <vector>     // to use std::vector
#include <limits>     // to use std::numeric_limits
#include <string>     // to use std::string
#include <istream>    // to use std::istream
#include <ostream>    // to use std::ostream
#include <iostream>   // to use std::cin, std::cout
#include <algorithm>  // to use std::transform algorit,

using namespace std;  // place this after the #includes

// a user defined data type of rgb values
struct rgb
{
  unsigned char red;
  unsigned char green;
  unsigned char blue;
};

// input streaam to read in the rgb values
std::istream& operator >>(std::istream& is, rgb& colour)
{
    unsigned r, g, b;
    
    if (is >> r >> g >> b)
    {
        colour.red  = r;
        colour.green = g;
        colour.blue = b;
    }
    else
    {
        is.setstate(ios_base::failbit);
    }
    return is;
    
}

//std ostream here, to output the color to the screen

std::ostream& operator << (std::ostream& os, rgb const& colour)
{
    os
    << static_cast<unsigned>(colour.red) << ' '
    << static_cast<unsigned>(colour.green) << ' '
    << static_cast<unsigned>(colour.blue) << ' '
    ;
    
    return os;
}

// the distance functions
double distance(rgb const& a, rgb const& b)
{
    float v1, v2, v3;
    
   double d;
    
    v1 = ((a.red - b.red) * (a.red - b.red));
    v2 = ((a.green - b.green)*(a.green - b.green));
    v3 = ((a.blue - b.blue)*(a.blue - b.blue));
    
    // sqrt of the sum
    d = sqrt(v1 + v2 + v3);
    
    return d; // returns the sum of the distances
}

int main()
{
    //array initilized with hex values for colours
    array<rgb,16> const colours{{
      { 0x00, 0x00, 0x00 }, // 0: black
      { 0x80, 0x00, 0x00 }, // 1: maroon
      { 0x00, 0x80, 0x00 }, // 2: green
      { 0x80, 0x80, 0x00 }, // 3: olive
      { 0x00, 0x00, 0x80 }, // 4: navy
      { 0x80, 0x00, 0x80 }, // 5: purple
      { 0x00, 0x80, 0x80 }, // 6: teal
      { 0xC0, 0xC0, 0xC0 }, // 7: silver
      { 0x80, 0x80, 0x80 }, // 8: grey
      { 0xFF, 0x00, 0x00 }, // 9: red
      { 0x00, 0xFF, 0x00 }, // 10: lime
      { 0xFF, 0xFF, 0x00 }, // 11: yellow
      { 0x00, 0x00, 0xFF }, // 12: blue
      { 0xFF, 0x00, 0xFF }, // 13: fushsia
      { 0x00, 0xFF, 0xFF }, // 14: aqua
      { 0xFF, 0xFF, 0xFF }  // 15: white
    }};

    //array initilized with the colour names
    array<string,16> const colour_names{
      "black", "maroon", "green", "olive", "navy", "purple", "teal", "silver",
      "gray", "red", "lime", "yellow", "blue", "fushsia", "aqua", "white"
    };


    //for loop used to read in rgb structure values until EOF or stream had failed
    for(rgb value{}; cin >> value;)
    {
      
      std::vector<double> distances; // a vector to stores the distanced read in
    
      distances.reserve(colours.size());
        /*
            the tranform function takes in beginning of colours and end of colors. and outputs using an iterator. The lambda function will accept the input and returns the output value
         */
      transform(
        begin(colours), end(colours), back_inserter(distances), [&value](auto const& colour){
          return distance(colour, value);}
      );

    
      size_t index = std::numeric_limits<size_t>::max();
  
      double smallest_distance = std::numeric_limits<double>::max();

        /*
            this for loop iterates through all the elements updating the index and smallest_distance and when a samlelr distaance is found it is updated
         */
   
      for(auto i=begin(distances), iEnd=end(distances); i != iEnd; ++i){


        if(*i < smallest_distance){
          smallest_distance = *i;
          index = i - begin(distances);
        }
      }
      //prints out the colour name based on the numbers that were entered
      cout << colour_names[index] << '\n';

      //this will execute if the index is invalid
      if (index < 0 || index > 15){
        cout << "ERROR occurred. Aborting...\n";
      }
    }
    return 1;
}


// sample main, have to delete it once done with it

/*
int main()
{
    rgb value;// this declares an instance of the rgb structure
        if (cin >> value)
           cout << "\nRead successful.\nWriting: " << value << '\n';
    
   cout << distances(value, value);


}


*/
