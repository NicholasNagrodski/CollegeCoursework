/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 2
* Date Due:   9/23/2008
*
* Purpose:     This program calls several functions, defined in prog2.h, to 
*            read in a stream of words.  It then strips them of thier puntuation
*            and counts thier frequencies.  It finally neatly prints out the
*            words and thier frequencies.     
*******************************************************************************/

// CSCI340 course header file.
#include "/home/turing/onyuksel/courses/340/common/340.h"

// Program2 header file.
#include "/home/turing/z140294/csci340/prog2/prog2.h"


int main()
{
  map<string,int> wordFrequency;

  // Fill the map and return the total number of words read.
  int numOfWords = get_words(wordFrequency); 

  print_words(wordFrequency, numOfWords);

  return 0;
}
