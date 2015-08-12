#include "/home/turing/onyuksel/courses/340/progs/08f/p1/prog1.h"

// This driver program can be used to test several algorithms -
// selection sort, linear search, and binary search -
// on vectors of integers.

int main()
{
    // define two empty vectors of ints for given sizes and
    // fill them by random integers for given seed values

    vector<int> A(ARR_SIZE), B(TEST_ARR_SIZE);
    Vecs(A, B, SEED1, SEED2);

    // print test (1st) vector before sorting its elements
    cout << "Random Numbers Before Sorting:\n";
    print_vec(A);

    // sort test (1st) vector using selection sort algorithm
    selSort(A);

    // print test (1st) vector after sorting its elements
    cout << "\nRandom Numbers After Sorting:\n";
    print_vec(A);

    // print test values from 2nd vector
    cout << "\nRandom Numbers Searched:\n";
    print_vec(B);

    // search each test value from 2nd vector in 1st vector
    // using linear search algorithm

    cout << "\nLinear Search:\n";
    search(A, B, linSearch);

    // search each test value from 2nd vector in 1st vector
    // using binary search algorithm

    cout << "\nBinary Search:\n";
    search(A, B, binSearch);
    return 0;
}

