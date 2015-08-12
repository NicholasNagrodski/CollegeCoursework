/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 7
* Date Due:   11/25/2008
*
* Purpose:  This is an implementation of a hash table. 
*******************************************************************************/

#include "/home/turing/onyuksel/courses/340/progs/08f/p7/HT.h"
#include "/home/turing/onyuksel/courses/340/progs/08f/p7/Entry.h"

void printEntry(Entry);

// Function : HT
// Use      : Constructor for a hash table.
// Arguments: The size of the table
// Returns  : N/A
HT::HT(int tableSize)
{
  table = new Entry[tableSize];
  ptable = new Entry*[tableSize];
  size = tableSize;  // max size
  psize = 0; // current # of inserted items

  for(int i = 0; i < tableSize; i++)
  {
    table[i].key = FREE;
    ptable[i] = 0; // Null pointer. 
  }
}

// Function : ~HT
// Use      : Destructor for the hash table.
// Arguments: None.
// Returns  : N/A
HT::~HT()
{
  delete [] table;
  delete [] ptable;
}

// Function : insert
// Use      : This function inserts entrys into the hash table. 
// Arguments: The entry to insert.
// Returns  : Nothing
// Notes    : No duplicate keys are allowed, these values won't be inserted.
void HT::insert(const Entry& item)
{
  int hsh = hash(item.key);

  // Look in the table for the next open spot.
  for( int i = 0; i < size; i++)
  {
    if( !table[hsh].key.compare(item.key) )
    {
      cout << "The key value \"" << item.key << "\" is already in the table!\n";
      return;
    }  

    // If there is an open space then insert this item.
    if( !table[hsh].key.compare(FREE) )
    {
      table[hsh] = item; // Insert the entry into the hash table.

      // Insert the address into the pointer table. 
      ptable[psize++] = &table[hsh];

      // Print out the insertion information.
      cout << "Inserting: " << right << item.key << " - "
           << setw(5) << item.number << "  - "
           << left << setw(ITEM_SZ) << item.descript 
           << " - Inserted in: " << hsh << endl;
      return;
    }
    hsh = (hsh + 1) % TBL_SZ; // Increase hash value for next iteration.
  }
  cout << "The hash table is full!\n";
}


// Function : search
// Use      : Searches the hash table for a specified key.
// Arguments: The key to search for. 
// Returns  : Nothing
void HT::search(const string& key)
{
  cout << "Searching for \"" << key << "\" ..." << endl; 
  
  int hsh = hash(key);

  // Look in the table for the key.
  for( int i = 0; i < size; i++)
  {
    if( !table[hsh].key.compare(key) )
    {
      cout << "Key found! ";
      printEntry(table[hsh]);
      return;
    }

    // If there is an open space then the item isn't in the table.
    if( !table[hsh].key.compare(FREE) )
      break;
    
    hsh = (hsh + 1) % size;
  }
  cout << "The item was not found!\n";
}


// Function : tabl_prnt
// Use      : Prints out the location, and entry information of the hash table.
// Arguments: None.
// Returns  : Nothing
void HT::tabl_prnt()
{
  cout << "\ntabl_prnt:\n";
  cout << "Entry | Key | number | description\n";

  for(int i = 0; i < TBL_SZ; i++)
  {
    if( table[i].key.compare(FREE) )
    {
      cout << setw(4) << i << "  : ";
      printEntry(table[i]);
    }    
  }
  cout << endl;
}

// Function : ptable_prnt
// Use      : Sorts and prints out the active entrys of the pointer table.
// Arguments: None.
// Returns  : Nothing.
void HT::ptabl_prnt()
{
  sort();
  cout << "\nSorted table:" << endl; 
  
  cout << "Key | number | description\n";
  for(int i = 0; i < psize; i++)
    printEntry(*ptable[i]);
}

// Function : sort
// Use      : Sorts a table of pointers to entrys in ascending order based on thier key value. 
// Arguments: None.
// Returns  : Nothing.
// Notes    : Selection sort is used.  The 
void HT::sort()
{
  int min = 0;
  for(int i = 0; i < psize-1; i++)
  { 
    min = i;
    for(int j = i+1; j < psize; j++)
    {  
      if( ptable[j]->key.compare(ptable[min]->key) < 0)
        min = j;
    }
    swap(ptable[i], ptable[min]);
  }
}

// Function : printEntry
// Use      : Prints the key, number, and description of the passed item.
// Arguments: An entry to print.
// Returns  : Nothing
void printEntry(Entry a)
{
  cout << a.key << " - "
       << setw(5) << right << a.number << "  - "
       << left << a.descript << endl;
}

