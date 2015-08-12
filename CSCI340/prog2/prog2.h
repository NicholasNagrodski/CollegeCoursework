/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 2
* Date Due:   9/23/2008
*
* Purpose:  This file implements functions that are used to count 
*          the frequencys of words in an input stream.
*******************************************************************************/

#ifndef H_PROG2
#define H_PROG2

/*******************************************************************************
Function  : clean_entry
Use       :  This function cleans punctuation marks off of words, and makes all
          : characters lowercase.
Arguments :  The string to be cleaned, and a reference to the location for the
          : string to be stored.
Returns   : Nothing.
*******************************************************************************/
void clean_entry(const string& dirty, string& clean)
{
  clean.clear();
  int beginingCharIndex;
  int length = 0;

  // Find the index of the first alpha numberic character in the word.
  uint i;
  for ( i = 0; i < dirty.length(); i++ )
  {
    if ( isalnum(dirty[i]) )
    {
      beginingCharIndex = i;
      break;
    }
  }

  // Find the index of the character before the next punctuation mark.
  while ( i + length < dirty.length() && isalnum(dirty[i+length]) )
  {
    length++;
  }

  // Get the proper substring if it exists.
  string upperCase;
  if ( length != 0)
    upperCase = dirty.substr(beginingCharIndex, length);

  // Make the word lowercase.
  for ( uint i = 0; i < upperCase.length(); i++ )
  {
    clean.push_back( tolower(upperCase[i]) );
  }
}




/*******************************************************************************
Function : get_words
Use      :  Reads in words from standard input,calls clean_entry, and 
         : store thier frequencies in a map container.
Arguments: A reference to a map.
Returns  : The number of words read in.
Notes    : None.
*******************************************************************************/
int get_words(map<string,int>& wordFrequency)
{
  int numberOfWords = 0;
  string word;
  cin >> word;
  
  while(cin)
  {
    string cleanedWord;

    // Strip punctuation.
    clean_entry(word, cleanedWord);
      
    if ( cleanedWord.length() >= 1 )
    {
      numberOfWords++;
   
      // Add the word to the list of word frequencies.
      wordFrequency[cleanedWord]++;
    }

    // Read in the next word.
    cin >> word;
  }
  return numberOfWords;
}


/*******************************************************************************
Function  : print_words
Use       : Prints the list of words frequencies, 3 per line.
Arguments : The map of word frequencies, and the num of words read by get_words.
Returns   : Nothing.
*******************************************************************************/
void print_words(const map<string,int>& wordFrequency, int numberOfWords)
{
  map<string,int>::const_iterator wrdIter = wordFrequency.begin();

  // A variable to the number of items printed so far, used for formatting .
  int printedSoFar = 1;
  
  cout << "\nTotal number of words in input stream : " << numberOfWords
       << "\nTotal number of unique words : " << wordFrequency.size() 
       << endl << endl;

  while( wrdIter != wordFrequency.end() )
  {
    // Print out the word and it frequency.
    cout << setiosflags(ios::left) << setw(15) << wrdIter->first << ": " 
         << setw(3) << wrdIter->second << "  ";

    // If this is the 3rd on the line, goto the next line.
    if( printedSoFar % 3 == 0 ) 
      cout << endl;

    // Point the iterator at the next map element.
    wrdIter++;
    printedSoFar++;
  }
  cout << endl;
}

#endif
