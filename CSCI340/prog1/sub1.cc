/*********************************************************************
* Programmer: Nicholas Nagrodski
* Course:     CSCI340
* Section:    2
* Assignment: 1
* Date:       9/8/2008
*
* Purpose:  This program implements functions that act on 
*          vectors, and implement two search routines showing 
*          the efficiency of both.     
*********************************************************************/


// CSCI340 course header file.
#include "/home/turing/onyuksel/courses/340/common/340.h"
// Program one header file.
#include "/home/turing/onyuksel/courses/340/progs/08f/p1/prog1.h"


/********************************************************************
Function : selSort
Use      : Performs the selection sort on a vector of intergers.
Arguments: A vector of intergers.
Returns  : Nothing.
Notes    : None.
********************************************************************/
void selSort( vector<int>& v )
{
  int temp, min;
  for ( uint i=0; i < v.size()-1; i++ )
  {
    min = i;
    for ( uint j=i+1; j < v.size(); j++ )
    {
      if ( v[j] < v[min] )
        min = j;
    } 
    // Swap.
    temp = v[min];
    v[min] = v[i];
    v[i] = temp; 
  }
}


/********************************************************************
Function  : linSearch
Use       : Searches for a given value 'x'.
Argumnets : 1) A vector of interger values to search.
          : 2) The value to search for.
          : 3) An interger to pass back the number of comparisons 
          :   done.
Returns   :  The subscript where the element was found, or negitave 
          : one, if the value isn't found.
********************************************************************/ 
int linSearch( const vector<int>& v, int x, int& cnt ) 
{
  for ( uint i = 0; i < v.size(); i++ )
  {
    cnt++; // Increment the number of comparisons done.
    if ( v[i] == x )
      return i;
  }
  return -1;
}


/******************************************************************** 
Function  : binSearch 
Use       : Searches for 'x' in vector v.
Argumnets : 1) A vector of interger values to search.
          : 2) The value to search for.
          : 3) An interger to pass back the number of comparisons 
          :   done.
Returns   :  The subscript where the element was found, or negitave 
          : one, if the value isn't found.
********************************************************************/ 
int binSearch(const vector<int>& v, int x, int& cnt) 
{
  int low = 0;
  int high = v.size() - 1;
  int mid;

  while ( low <= high )
  {
    // Increment the number of comparisons.
    cnt++;

    // Find the middle point in the vector.
    mid = ( low + high ) / 2;

    if  ( x == v[mid] )
      return mid;
    else if ( x < v[mid] )
      high = mid - 1;
    else  // ( x > v[mid]) 
      low = mid + 1;
  } 
  // If the item is not found return -1;
  return -1;
}


/********************************************************************
Function  : search
Use       :  A generic search algorithm that allows for a choice of 
          : seaching function> It also keeps track of the total number 
          : of comparisons done and the total successful searches.
Arguments : 1) const vector<int>& v1; The vector to be searched.
          : 2) const vector<int>& v2; The vector with the elements to 
          :  search for.
          : 3) int (*f)(const vector<int>&, int, int&);  A function 
          :  pointer.
Returns   : Nothing.
********************************************************************/
void search( const vector<int>& v1, const vector<int>& v2, int (*f)(const vector<int>&, int, int&) )  
{
  int total_successful_searches=0;
  int total_num_of_comparisons=0;  
  int num_of_comparisons;

  // First search the arrays.
  for ( uint i=0; i < v2.size(); i++ )
  {
    num_of_comparisons=0;

    // If the value is anything other than -1, the search was successful.
    if ( f( v1, v2[i], num_of_comparisons ) != -1 )
      total_successful_searches++;

    total_num_of_comparisons += num_of_comparisons;  
  }

  // Call the print_stat function.
  print_stat( total_num_of_comparisons, total_successful_searches, v2.size() );
}


/********************************************************************
Function  : Vecs
Use       :  Fills two vectors with random numbers, specified by thier
          : respective seed numbers.
Arguments : 1) vector<int>& v1; The first vector. 
          : 2) vector<int>& v2; The second vector.
          : 3) The seed value for v1.
          : 4) The seed value for v2.
Returns   : Nothing.
********************************************************************/
void Vecs( vector<int>& v1, vector<int>& v2, int s1, int s2 )
{
  // Set the seed for v1 and fill it.
  srand(s1);
  for ( uint i = 0; i < v1.size(); i++ )
  {
    v1[i] = rand() % RND_NO_RANGE + 1;
  }
  
  // Set the seed for v2 and fill it.
  srand(s2);
  for ( unsigned int i = 0; i < v2.size(); i++ )
  {
    v2[i] = rand() % RND_NO_RANGE + 1;
  }
}


/********************************************************************
Function  : print_vec
Use       : Prints a formatted output of the values in the vector.
Agruments : 1) const vector<int>& v; The vector to be printed.
Returns   : Nothing.
********************************************************************/
void print_vec( const vector<int>& v )
{
  for( uint i = 0; i < v.size(); i++ )
  {
    
    if ( i % LINE_SIZE == 0 )
      cout << endl;
    
    // Print the element.
    cout << v[i] << " ";
  }  
  cout << endl;
}


/********************************************************************
Function  : print_stat
Use       :  Calculates and formats for output the average number of
          : comparisons and the percent of successful searches.
Arguments : 1) The total number of comparisons done.
	  : 2) The number of successful searches.
	  : 3) The total number of elements searched for.
Returns   : Nothing.
********************************************************************/
void print_stat( int tot_cnt, int tot_suc_cnt, int vec_size )  // prints final statistics
{
  cout << setiosflags(ios::fixed) << setprecision(2);
  cout << "  Average number of comparisons:  " 
    << setw(8) << (float)tot_cnt/vec_size << endl;

  cout << "  Percent of successful searches: " 
    << setw(8) << (float)tot_suc_cnt/vec_size*100 << "%" << endl; 
}
