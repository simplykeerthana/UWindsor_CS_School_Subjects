/*
    Name: Keerthana Madhavan
    Program : Assignment 5, This assignment is designed to explore how to use the associative containers, e.g., map, multimap, unordered_map, and unordered_multimap. This is the inverse histograam.
    and the use of inverse histogram
    Date: 08/03/2020
 */



// the required headers files needed for this program.
#include <set>
#include <iostream>
#include <map>
#include <string>
#include <ostream>
#include <cstddef>
#include <vector>
#include <algorithm>

using namespace std;


// the structure that holds the first_name and last naame
struct  record
{
  string fname_;
  string lname_;
};



using hist_t = map<record, size_t>;
using inverse_hist = map<size_t,set<record>>; // inverse histogram using a set algorithm

// std::istream operator >> overload to read in a record.
istream& operator >>( istream& is, record& record)
{
  std::string firstName, lastName;
  if( is >> firstName >> lastName)
  { //read in the first and last name values
    record.fname_ = firstName;
    record.lname_ = lastName;
  } else { // if the input stream fails
    is.setstate(std::ios_base::failbit);
  }
  return is; // return the inputed stataement
}


// std::ostream operator << overload to write out a record.
ostream& operator <<( ostream& os, record const& record)
{
  os
  << record.fname_ << ' ' << record.lname_ << ' '; // to assure there is a space between the first name and last name

  return os;
}


bool operator <(record const& rec1, record const& rec2) // make sure you input the values from the text file is valid or not
{
  if( !(rec1.lname_ < rec2.lname_) && !(rec2.lname_ < rec1.lname_))
    return rec1.fname_ < rec2.fname_;
  else
    return rec1.lname_ < rec2.lname_;
}

// outputing the information to the stream
ostream& operator <<(ostream& os, hist_t const& s)
{
    
    // out the way the professor aksed it
  os << "{ ";
  for( auto p = s.begin(); p != s.end() ; p++)
  {
    os
    << p->first.fname_
    << " "
    << p->first.lname_
    << " : "
    << p->second;

    if(distance(p,s.end()) !=1 )
    {
      os << ",";
    }

    os << " ";
  }
    
  os << "}";
  return os;
}

int main (){
  
  hist_t h; // create a new object of the histogram
  inverse_hist ih; // a new object of the inverse histogram
  

  
  // find frequency key/value and add it to the histogram
  for( record r; cin >> r;){
    auto iter = h.insert({r,1});
    if (!iter.second)
      iter.first->second++;
  }
  // reverse map, the same word with frequency will be added in a set
  for( auto i = h.begin(); i!=h.end() ; ++i){

    auto word = i->first;
    auto freq = i->second;
    auto iter = ih.insert({freq,set<record>()});
    iter.first->second.insert(word);
  }

  // output the inverse map
  for(  auto start = ih.begin(), end = ih.end(); start != end; start++){

    using namespace std;
    auto key = start->first;
    auto list = start->second; // and the first and last names.

      ////2: { Stefan D } , that's how the sample output would be
    cout << key << ": { ";
    for( auto i = list.begin(), e = list.end(); i != e ; i++)
    {
      cout << i->fname_ << " " << i->lname_;
      if( distance(i,e)!=1)
        cout << ", ";
    }
    cout << " }" << endl ;
  }
  cout << endl ;
}
