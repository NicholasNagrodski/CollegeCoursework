/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 4
* Date Due:   10/15/2008
*
* Purpose:  This file defines a Node of a binary tree.  
*******************************************************************************/

#ifndef H_NODE
#define H_NODE

template<class T> class BT;

template<class T>
class Node
{
  friend class BT<T>;
  public:
    Node(const T& = T(), Node<T>* = 0, Node<T>* = 0);

  private: 
    T data;
    Node<T>* left;
    Node<T>* right;
};


template<class T>
Node<T>::Node(const T& newData, Node<T>* leftChild, Node<T>* rightChild)
{
  this->data = newData;
  this->left = leftChild;
  this->right = rightChild;
}

#endif
