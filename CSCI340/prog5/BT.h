/*******************************************************************************
* Programmer: Nicholas Nagrodski
* Z-ID:       Z140294
* Course:     CSCI340
* Section:    2
* Assignment: 4
* Date Due:   10/15/2008
*
* Purpose:  This file implements a binary tree data structure.  
*******************************************************************************/

#include "Node.h"

#ifndef H_BT
#define H_BT

template<class T>
class BT
{
  public:
    BT() { root = 0; }
    BT(const BT<T>& oldTree) { root = copy(oldTree.root); }
    BT& operator=(const BT<T>&);

    ~BT() { clear(); }

    bool empty() const { return root == 0; }
    int size() const { return size(root); }
    int height() const { return height(root); }
    int leaves() const { return leaves(root); }
    virtual void insert(const T& x) { insert(root,x); }
    void clear() { clear(root); root = 0; }

    // Tree traversal functions.
    void inOrder(void (*p)(T&)) { inOrder(root,p); }
    void preOrder(void (*p)(T&)) { preOrder(root,p); }
    void postOrder(void (*p)(T&)) { postOrder(root,p); }

  protected:
    Node<T>* root;

  private:
    int size(Node<T>*) const;
    int height(Node<T>*) const;
    int leaves(Node<T>*) const;
    void insert(Node<T>*&, const T&);
    void clear(Node<T>*);

    void inOrder(Node<T>*, void (*)(T&));
    void preOrder(Node<T>*, void (*)(T&));
    void postOrder(Node<T>*, void (*)(T&));

    Node<T>* copy(const Node<T>*);
};

#endif


/**************** Public Functions **********************/

// Function  : operator=
// Use       :  Assigns the right tree to the left tree.  This 
//           : function makes a copy of the right tree and assigns it 
//           : to the left.
// Arguments : The tree to assign to the left tree.
// Returns   : The copied tree.
template<class T>
BT<T>& BT<T>::operator=(const BT<T>& rightTree)
{
  // Delete from memory the current tree.  
  clear();

  // Get a copy of the new tree and return it.
  this->root = copy(rightTree.root);
  return *this;
}


/**************** Private Functions *********************/

// Function : size
// Use      : Finds the size of a binary tree, the number of nodes.
// Arguments: A pointer to the root node.
// Returns  : The number of nodes in the tree as an interger.
template <class T>
int BT<T>::size(Node<T>* node) const
{
  // If the node is empty return zero.
  if ( node == 0 )
    return 0;

  //  Find the sizes of the left and right subtrees
  // and return the resulting value.
  return (size(node->left) + size(node->right) + 1);
}


// Function : height
// Use      : Finds the height of the tree
// Arguments: A pointer to the root node.
// Returns  : The height of the tree as an interger.
template <class T>
int BT<T>::height(Node<T>* node) const
{
  // If the node is empty return zero.
  if( node == 0 )
    return 0;
  
  int leftHeight = height(node->left);
  int rightHeight = height(node->right);

  if ( leftHeight > rightHeight )
    return (leftHeight + 1);
  else
    return (rightHeight + 1);
}


// Function : leaves
// Use      : Calculates the number of leaf nodes in the tree.
// Arguments: A pointer to the root node.
// Returns  : The leaves of the tree as an interger.
template <class T>
int BT<T>::leaves(Node<T>* node) const
{
  // If the node is empty return zero.
  if ( node == 0 )
    return 0;

  // If the node is a leaf node return one.
  if ( node->left == 0 && node->right == 0 )
    return 1;

  // Find the leaves in the left and right subtrees
  // and return the resulting value.
  return (leaves(node->left) + leaves(node->right));
}


// Function  : insert
// Use       : Inserts a value in the shortest tree.
// Arguments :  A pointer to the root node, and the data element to be 
//           : inserted.
// Returns   : Nothing.
template<class T>
void BT<T>::insert(Node<T>*& node, const T& data)
{
  // If there are no nodes here insert the node.
  if ( node == 0 )
  {
    node = new Node<T>(data, 0, 0);
    return;
  }

  // Find the smallest subtree in the BT.
  if ( height(node->left) <= height(node->right) )
    insert(node->left, data);
  else
    insert(node->right, data);
}


// Function : clear
// Use      : Deletes a binary tree.
// Arguments: A pointer to the root node.
// Returns  : Nothing.
template <class T>
void BT<T>::clear(Node<T>* node)
{
  // If this position contains a node.
  if ( node != 0 )
  {
    // Delete the left and right children.
    clear(node->left);
    clear(node->right);

    // Delete the node itself.
    delete node;
  }
}



// Function  : inOrder
// Use       : Traverses the tree in-order.
// Arguments :  A root node to start the traversal at, and a pointer to
//           : a function, that defines the action we want to take when
//           : we get visit the node.
// Returns   : Nothing.
template<class T>
void BT<T>::inOrder(Node<T>* node, void (*p)(T&) )
{
  // If this position contains a node.
  if ( node != 0 )
  {
    // Visit the left node.
    inOrder(node->left, p);
  
    // Call the function.
    p(node->data);

    // Visit the right node.
    inOrder(node->right, p);
  }
}


// Function  : preOrder
// Use       : Traverses the tree in pre-order.
// Arguments :  A root node to start the traversal at, and a pointer to
//           : a function, that defines the action we want to take when
//           : we get visit the node.
// Returns   : Nothing.
template<class T>
void BT<T>::preOrder(Node<T>* node, void (*p)(T&) )
{
  // If this position contains a node.
  if ( node != 0 )
  {
    // Call the function.
    p(node->data);

    // Visit the left node, then the right node.
    preOrder(node->left, p);
    preOrder(node->right, p);
  }
}


// Function  : postOrder
// Use       : Traverses the tree in the post order.
// Arguments :  A root node to start the traversal at, and a pointer to  
//           : a function, that defines the action we want to take when 
//           : we get visit the node.
// Returns   : Nothing.
template<class T>
void BT<T>::postOrder(Node<T>* node, void (*p)(T&) )
{
  // If this position contains a node.
  if ( node != 0 )
  {
    // Visit the left node, then visit the right node.
    postOrder(node->left, p);
    postOrder(node->right, p);

    // Call the function.
    p(node->data);
  }
}


// Function  : copy
// Use       : Copies a node, and all its children.
// Arguments : A pointer to the node to be copied.
// Returns   :  A pointer to a node which contains a copy of the 
//           : origional tree's node.
template<class T>
Node<T>* BT<T>::copy(const Node<T>* sourceNode)
{
   if ( sourceNode == 0 )
     return 0;

   // Create the new node.
   Node<T>* newNode = new Node<T>(sourceNode->data, 0, 0);

   // copy the left and right subtrees.
   newNode->left = copy(sourceNode->left);
   newNode->right = copy(sourceNode->right);   

   // Return the node we just created.
   return newNode;
}
