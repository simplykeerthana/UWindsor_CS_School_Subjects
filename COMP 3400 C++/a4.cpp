/*
    Name: Keerthana Madhavan
    Program : Assignment 4, This assignment is designed to explore how to use the associative containers, e.g., map, multimap, unordered_map, and unordered_multimap.
    Date: 04/03/2020
 
 */

#include <map>
#include <iostream>
#include <ostream>
#include <vector>
#include <algorithm>
#include <string>
#include <map>

using namespace std;
// struct to process first-and-last name data:
struct record
{
    string fname_;
    string lname_;
};

using hist_t = map<record, size_t>;

// std::istream operator >> overload to read in a record.

istream& operator  >> (istream& is, record& recString)
{
    string firstName;
    string lastName;
    

    if (is >> firstName >> lastName)
    { //read in the first and last name values
        recString.fname_ = firstName;
        recString.lname_ = lastName;
    }
    else
    {
        is.setstate(std::ios_base::failbit);
    }


    return is;
}

//std::ostream operator << overload to write out a record.

ostream& operator  << (ostream& os, record const& recString)
{
    os << recString.fname_ << ' ' << recString.lname_; // outputing to the screen
    return os;
}

bool operator <(record const& rec1, record const& rec2)
{
    if ((rec1.lname_ < rec2.lname_) == false && (rec2.lname_ < rec1.lname_) == false)
    {//If the last names are "equivalent", then return the result of using < on the first names
        return rec1.fname_ < rec2.fname_;
        
    }
    else
    {//return the result of using < on the last names.
        return rec1.lname_ < rec2.lname_;
    }

}



int main()
{

    hist_t histogram;
    for (record r; std::cin >> r; )
    {// inserts the legal input in to the record
        auto isLegalInput  = histogram.insert({ r,1 });

        if (isLegalInput.second == false)
        {
            isLegalInput.first->second++;
        }
    }


    if (!histogram.empty())
    {
        cout << "{ ";
        map<record, size_t>::iterator it = histogram.begin();
        map<record, size_t>::iterator itlast = histogram.end();
     unsigned int counter = 0;
        
        for (; it != itlast; it++)
        {// iterates through all values in the map
            cout << it->first << " : " << it->second; // John D : 1

            if (counter < (histogram.size() - 1))
            {
                cout << ", ";
                counter++;
            }
            
        }

        cout << " }\n";
    }
    else
    {
        cout << "Please enter a valid input or text file" << endl;
    }
}
