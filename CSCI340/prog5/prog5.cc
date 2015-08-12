/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 5
* Date Due:   10/28/2008
*
* Purpose:  This is a driver file for a binary search tree class.
*******************************************************************************/


//#include "/home/turing/onyuksel/courses/340/common/340.h"
#include <iostream.h>
#include <vector.h>
#include "BST.h"

#define N 1000

void printVal(int&);
void sieve(set<int>&);
void printBSTinfo(BST<int>);

int main()
{
  // Define our variables.
  vector<int> v;
  BST<int> t;
  int n;  

  // Initalize the random number generator.
  srand(1);  

  // Get the input data.
  cout << "\nHow many elements should we insert? ";
  cin >> n;

  // Fill the BST and the vector with data.
  for(int i = 0; i < n; i++)
  {
    int randNum = rand() % N;

    v.push_back(randNum);
    t.insert(randNum);
  }  

  cout << "\nThe tree is filled.\n";

  // Create a set of all numbers numbers.
  set<int> s;
  for(uint i = 2; i < N; i++)
    s.insert(i);
  
  // Remove all the non prime numbers.
  sieve(s);

  // Seach the BST with all the elements in v. 
  cout << "\nSearching the tree\n"; 

  int totalSearchLength = 0; 
  int totalSuccessfulSearches = 0;   

  for(int i = 0; i < n; i++)
  { 
    int thisSearchLength = 0;
    totalSuccessfulSearches += t.search(v[i], thisSearchLength);  
    totalSearchLength += thisSearchLength; 
  } 
 

  // Print out t's information.
  printBSTinfo(t); 

  // Format the output.
  cout << setiosflags(ios::fixed) << setprecision(2);

  // Print out the tree's search statistics.
  cout <<  "\n\nRatio of successfull searches for 't' is: "
       << (double) totalSuccessfulSearches/n*100 
       << " %\nAverage search length for 't' is: "
       << (double) totalSearchLength/n; 

  // Print out the values of 't' in ascending order.         
  cout << "\n\nThe elements of 't' in inOrder:\n\n";
  t.inOrder(printVal);


  // Remove all the non-prime numbers in 't' the binary search tree.
  cout << "\n\nRemoving all non-prime numbers in 't'..." << endl;

  for ( uint i = 0; i < v.size(); i++ )
  {
    if ( !s.count(v[i]) )  // If the number isn't a prime and is in the BST.
    {
      t.remove(v[i]); // Remove the value from the tree.
    }
  } 


  // Print out t's information.
  printBSTinfo(t);

  cout << "\nSearching the updated tree.\n";

  totalSearchLength = 0;
  totalSuccessfulSearches = 0;

  for(int i = 0; i < n; i++)
  {
    int thisSearchLength = 0;
    totalSuccessfulSearches += t.search(v[i], thisSearchLength);
    totalSearchLength += thisSearchLength;
  }

  // Format the output.
  cout << fixed << setprecision(2);

  // Print out the tree's search statistics.
  cout <<  "\nRatio of successfull searches for 't' is: "
       << (double) totalSuccessfulSearches/n*100
       << " %\nAverage search length for 't' is: "
       << (double) totalSearchLength/n;


  // Print out the values of 't' in ascending order.
  cout << "\nThe elements of 't' in inOrder:\n";
  t.inOrder(printVal);
  cout << endl << endl;
}


// Function  : printVal
// Use       : Prints out values, 16 per line.
// Arguments : The value to be printed.
// Returns   : Nothing.
void printVal(int& value)
{
  static int printedSoFar = 0;
  static int lastValuePrinted = 0;  


  // Goto a newline if we need to
  // 1) We have printed 16 values on this line.
  // 2) We are starting a printout of a BST in which case 
  //    the last value printed will be greater than the current.
  if ( printedSoFar % 16 == 0 || lastValuePrinted > value )
  {
    cout << "\n    ";
    printedSoFar = 0;
  }

  // Print the value.
  cout << setw(5) << value;

  lastValuePrinted = value;
  printedSoFar++; 
}


// Function  : sieve
// Use       : Removes all non-prime numbers in a set.
// Arguments : The a set of contiguous integers to operate on.
// Returns   : Nothing.
// Notes     : The set must initially contain numbers from 2 through N.
void sieve(set<int>& s)
{
  for (int m = 2; m*m < N; m++)
  {
    //  Remove all multiples of 'm' in the set. 
    // If 'm' does not exist no action is taken.
    for (int k = m; k*m <= N; k++ )
    {
      s.erase(k*m);
    }
  }
}


// Function  : printBSTinfo
// Use       : Prints out the size, # of leaf nodes, and the height of the tree.
// Arguments : A BST to print.
// Returns   : Nothing.
void printBSTinfo(BST<int> tree)
{
  cout << endl << "Binary Search Tree info:"
       << "\n  Number of nodes: " << tree.size()
       << "\n  Number of leaf nodes: " << tree.leaves()
       << "\n  Height of tree: " << tree.height() << endl;
}


