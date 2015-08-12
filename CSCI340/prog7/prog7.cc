/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 7
* Date Due:   11/25/2008
*
* Purpose:  This is a driver file for a hash table implementation.
*******************************************************************************/

#include "/home/turing/onyuksel/courses/340/common/340.h"
#include "/home/turing/onyuksel/courses/340/progs/08f/p7/HT.h"

int main()
{
  HT table;  // Define a hash table.  
  string line;  // A temp variable to read in a line of text. 

  cout << "\n\tBegining program execution.\n\n";

  // Process the input file. 
  while( getline(cin, line) )
  {
    if( line.at(0) == 'A' )
    {
      // Create a new entry.
      Entry a;
      
      // Add the input key to the entry.
      a.key = line.substr(2,ID_SZ);

      // Add the number field to the entry. 
      int descriptIndex = line.find_first_of(":",6,4)+1;
      a.number = atoi( line.substr(6,descriptIndex-6).c_str() );

      // Add the description field to the entry.
      a.descript = line.substr(descriptIndex,line.length()-1-descriptIndex);

      // Add the item to the table.
      table.insert(a);
    }
    else if( line.at(0) == 'S' )
    {
      // Search the table for the key.
      table.search(line.substr(2,ID_SZ));
    }
    else if( line.at(0) == 'P' )
    {
      // Print the table.
      table.tabl_prnt();
    }
    else 
      cout << "Unrecognized processing command.\n";
  }
 
  // Sort and print the sorted data.
  table.ptabl_prnt();

  cout << "\n\tProgram execution Over.\n\n";
  return 0;
}
