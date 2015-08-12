/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 5
* Date Due:   10/28/2008
*
* Purpose:  This file defines binary search tree.  
*******************************************************************************/

#include "BT.h"

#ifndef H_BST
#define H_BST

template<class T>
class BST : public BT<T>
{
  public:
    BST() { BT<T>(); }
    void insert(const T&); 
    void remove(const T&); 
    bool search(const T&, int&);
 
  private:
    void deleteByCopy(Node<T> *&);
};


// Function  : insert
// Use       : inserts a node into the BST.
// Arguments : The value to insert.
// Returns   : nothing.
template<class T>
void BST<T>::insert(const T& x)
{
  Node<T>* node = this->root, *prev = 0;

  // Find a place to insert the new node.
  while ( node != 0 )
  {
    prev = node;

    if ( x < node->data )
      node = node->left;
    else if ( x > node->data )
      node = node->right;
    else
      return;
  }

  // Then insert the node.
  if ( this->root == 0 )
    this->root = new Node<T>(x);  // Insert the new node.  
  else if ( x > prev->data )
    prev->right = new Node<T>(x);  // Insert the new node to the right.  
  else if ( x < prev->data )
    prev->left = new Node<T>(x);  // Insert the new node to the left.  
}


// Function  : deleteByCopy
// Use       : Deletes a node by copy using the inorder predecessor.
// Arguments : A reference to a pointer to the node to be deleted.
// Returns   : Nothing.
// Notes     :  Code is from 'Data Structures and Algorithms in C++, 
//           : Third Edition', by Adam Drozdek C2005, Page 249
template<class T>
void BST<T>::deleteByCopy(Node<T> *& node)
{
  // The remove the node.
  Node<T> *previous, *tmp = node;

  if ( node->right == 0 ) // Node has one child.
    node = node->left;
  else if ( node->left == 0 ) // Node has one child.
    node = node->right;
  else // Node has both children.
  { 
    // First find the predecessor.
    tmp = node->left;
    previous = node;
    while ( tmp->right != 0 )
    {
      previous = tmp;
      tmp = tmp->right;
    }
    node->data = tmp->data; // Copy data.
    if ( previous == node )
      previous->left = tmp->left;
    else
      previous->right = tmp->left;
  }
  delete tmp; // Finally delete the node.
}


// Function  : remove
// Use       : Given a value, finds the node with that value.
//           : It then calls deleteByCopy to delete the node.
// Arguments : A value to delete in the tree.
// Returns   : Nothing.
// Notes     :  Code is adapted from 'Data Structures and Algorithms in C++,
//           : Third Edition', by Adam Drozdek C2005, Page 247
template<class T>
void BST<T>::remove(const T& x)
{
  Node<T> *previous = 0, * node = this->root;
  
  // First find the node with the value of 'x'.
  while ( node != 0)
  {
    // If we found the node.
    if ( node->data == x )
      break;
 
    previous = node;
 
    if ( x < node->data )
      node = node->left;
    else 
      node = node->right;
  }

  if ( node != 0 && node->data == x )
    if ( node == this->root )
      deleteByCopy(this->root);
    else if ( previous->left == node ) 
      deleteByCopy(previous->left);
    else
      deleteByCopy(previous->right);
}


// Function  : search
// Use       : Searches for a value in the BST.
// Arguments : The value to search for, and a value to store the length of each search.
// Returns   : Whether or not the value was found.
template<class T>
bool BST<T>::search(const T& x, int& length)
{
  Node<T>* node = this->root;

  length = 0;

  while ( node != 0 )
  { 
    length++; // Increment the search path length.

    // Otherwise traverse the tree.
    if ( x < node->data )
      node = node->left;
    else if ( x > node->data )
      node = node->right;
    else 
      return true;
  }
  return false;
}

#endif
