
/*
    Name: Keerthana Madhavan
    Student ID: 104995097
    COMP 3400 : Assignemnt 1
 */

#include <iostream>
#include <iomanip> // for std::noskipws

using namespace std;

int main()
{
bool quote = true; // this bool variable turns on existing or non-existing quote in byte
char byte;
    
  while (cin >> noskipws >> byte)
  {
        char temp = ' '; // a temporary black space charcter
      
        switch(byte)
        {
                // all the cases check check if there is an opening character then just print as-is, else print the space.
            case '.':  cout.put( temp = quote ? ' ': byte) ;
                    break;
            case  ',':   cout.put( temp =quote ? ' ' : byte) ;
                    break;
          case '?': cout.put( temp = quote ? ' ': byte) ;
                    break;
          case '-':  cout.put( temp = quote ? ' ': byte) ;
                    break;
            case '\'':  cout.put( temp = quote ? ' ': byte) ;
                     break;
          case '\"':
                    quote = !quote; // if quote is found switch the boolean value sensitive to the situation
                cout << byte;
                break;
          default:
                    cout << byte;
                    break;

            cout << endl;
        }
  }
   return 0;
}
