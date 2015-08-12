#include "BT.h"

#define Empty(x)\
    cout << "tree is " << (x.empty() ? "" : "not ") << "empty\n"

#define HDR(x) Empty(x);\
    cout << "\tno of nodes    = " << setw(2) << x.size()\
         << "\n\tno of leaves   = " << setw(2) << x.leaves()\
         << "\n\theight of tree = " << setw(2) << x.height()\
         << endl << endl;

#define ORD(x,y,s,t)\
    cout << "The elements of '" << s << "' in " << t << ":\n\t";\
    x.y(print_vals); cout << endl

template<class T>
void print_vals(T& x) { cout << x << ' '; }

template<class T>
void increase_vals(T& x) { x += 1; }

template<class T>
void decrease_vals(T& x) { x -= 1; }

template<class T>
void negate_vals(T& x) { x = -x; }

const int a[] = { 1, -2, 3, -4, 5, -6, 7, -8, 9, -10, 11, -12,
        13, -14, 15 };
const float b[] = { 0.5, 1.75, -3, 4.25, 5.50, -6.75, 8, 9.25,
        -10.5 };
const char* c[] = { "This", "is", "a", "list", "of", "string",
        "objects." };

const int sz_a = sizeof(a) / sizeof(int);
const int sz_b = sizeof(b) / sizeof(float);
const int sz_c = sizeof(c) / sizeof(char*);


int main()
{
    BT<int> first;
    for (int i = 0; i < sz_a; i++)
        first.insert(a[i]);

    cout << "first: "; HDR(first);
    ORD(first, inOrder, "first", "inorder");
    ORD(first, preOrder, "first", "preorder");
    ORD(first, postOrder, "first", "postorder");

    BT<int> second(first);
    second.inOrder(negate_vals);

    first.clear();
    cout << "\nfirst: "; HDR(first);
    cout << "second: "; HDR(second);
    ORD(second, inOrder, "second", "inorder");
    cout << endl;

    BT<float> third, fourth;
    for (int i = 0; i < sz_b; i++)
        third.insert(b[i]);

    cout << "third: "; HDR(third);
    third.preOrder(increase_vals);
    ORD(third, preOrder, "third", "preorder");
    cout << endl;

    fourth = third;
    cout << "fourth: "; HDR(fourth);
    fourth.postOrder(decrease_vals);
    ORD(fourth, postOrder, "fourth", "postorder");
    cout << endl;

    BT<string> fifth;
    for (int i = 0; i < sz_c; i++)
        fifth.insert(c[i]);

    cout << "fifth: "; HDR(fifth);
    BT<string> sixth = fifth;
    ORD(sixth, inOrder, "sixth", "inorder");
    ORD(sixth, preOrder, "sixth", "preorder");
    ORD(sixth, postOrder, "sixth", "postorder");
    cout << endl;
    return 0;
}

