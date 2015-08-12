/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 3
* Date Due:   10/2/2008
*
* Purpose:      This file implements two functions  to compute all of the prime
*             numbers from two through the range given by the user input.  
              The values are then printed out. 
*******************************************************************************/

// CSCI340 course header file.
#include "/home/turing/onyuksel/courses/340/common/340.h"

// Program 3 header file.
#include "/home/turing/z140294/csci340/prog3/prog3.h"


int main()
{
  set<int> integerSet;
  int upperRange;

  // Prompt for the upper range.
  cout << "\n--> Prime number calculator."
       << "\nPlease enter the upper limit : ";
  cin >> upperRange;

  // Fill the set with integers from 2 to the upper range.
  for ( int i = 2; i <= upperRange; i++ )
    integerSet.insert(i);

  // Remove all the non-prime numbers.
  sieve(integerSet, upperRange);

  // Print the integers in the set.
  cout << "The following are prime numbers: \n";
  print_primes(integerSet);  

  return 0;
}


/*******************************************************************************
Function  : sieve
Use       : Removes all non-prime numbers from a set containing integer numbers.
Arguments : 1)  A set filled with integer numbers to be checked if they 
          :    are prime. 
          : 2)  The upper range of numbers to be checked.
Returns   : Nothing.
Notes     : This deletes all non-prime numbers in the when finished.
*******************************************************************************/
void sieve(set<int>& s, int n)
{
  
  for (int m = 2; m*m < n; m++)
  {
    //  Remove all multiples of 'm' in the set, if 'm' does not exist no 
    // action is taken.
    for (int k = m; k*m <= n; k++ )     
      s.erase(k*m);
  }
}


/*******************************************************************************
Function  : print_primes
Use       : This function prints all the numbers in the set passed in.
Arguments : A set of integers.
Returns   : Nothing.
*******************************************************************************/
void print_primes(const set<int>& s)
{
  // Get an iterator to the begining of the set.
  set<int>::const_iterator iter = s.begin();

  int printedSoFar = 1;

  // Prin the values of the set, ten numbers per line.  
  while ( iter != s.end() )
  {
    cout << setw(5) << *iter;

    if ( printedSoFar % 10 == 0 )
      cout << endl;

    printedSoFar++;
    iter++;
  }

  cout << endl;
}

