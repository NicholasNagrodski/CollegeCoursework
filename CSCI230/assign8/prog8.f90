PROGRAM PROG8
!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   8                          Due Date:  11/08/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program is demonstrating sorting using several functions.
!                
!
!  Input:  Described in step 10.
!     
!  Output: Output is described in the main numbered comments.
!                                                                   
!-------------------------------------------------------------------------------

  IMPLICIT NONE

  ! Input Variables as defined above.


  ! Program variables.
  INTEGER, PARAMETER :: REAL_ARRAY_SIZE = 121
  REAL :: RealArray(REAL_ARRAY_SIZE)
  REAL :: RealArrayCopy(REAL_ARRAY_SIZE)
  INTEGER :: NumComparisions = 0, NumSwaps = 0 

  CHARACTER(10) :: DATE, DateDisplay   ! Character vars for the date display.
  REAL :: X             ! Used to generate a ramdom array fo reals.
  INTEGER :: I          ! Counter used in loops.

  INTEGER, PARAMETER :: NAME_ARRAY_SIZE = 52
  Character(10) :: FirstName(NAME_ARRAY_SIZE), LastName(NAME_ARRAY_SIZE), Password(NAME_ARRAY_SIZE)

  ! Define file information.
  INTEGER, PARAMETER :: fileNumber = 9
  INTEGER :: inputStatus, openStatus
  CHARACTER(20), PARAMETER :: fileName = "data8.txt"


!!! MAIN  EXECUTABLE  CODE  !!!


  ! 1. Get the Date and format it into a readable format.
  CALL DATE_AND_TIME(DATE)     ! DATE = YYYYMMDD
  DateDisplay = DATE(5:6)//'/'//DATE(7:8)//'/'//DATE(1:4)    ! DateDisplay = "MM/DD/YYYY"

  ! 2. Fill the array with 121 real random numbers [0.0, 750.0]
  CALL RANDOM_SEED
 
  DO I = 1, REAL_ARRAY_SIZE 
    CALL RANDOM_NUMBER(X)
    RealArray(I) = 750.0 * x    ! Fill the array with a ramdom number on [0.0, 750.0 ]
  END DO 
  
  ! Copy the array into the spare copy.
  RealArrayCopy = RealArray

  ! 3. Print the array.
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T25, A, 2/)' ), "Random array of numbers between [0.0, 750.0]."
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 4. Sort the array ascending using selection sort.
  CALL selectionSortReal( RealArray, NumComparisions, NumSwaps )

  ! 5. Print the array and the number of comparisons and swaps.
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T19, A, 2/)' ), "Array sorted in ascending order using selection sort:"
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 6. Reset the array to its original contents and sort it using bubble sort.
  RealArray = RealArrayCopy  ! Reset the array.
  CALL bubbleSortReal( RealArray, NumComparisions, NumSwaps )
  
  ! 7. Print the array and the number of comparisons and swaps.
  CALL heading(DateDisplay) ! Print a heading. 
  WRITE ( *, '(T22, A, 2/)' ), "Array sorted in ascending order using bubble sort:"
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 8. Reset the array to its original contents and sort it using bubble sort.
  RealArray = RealArrayCopy  ! Reset the array.
  CALL insertionSortReal( RealArray, NumComparisions, NumSwaps )

  ! 9. Print the array and the number of comparisons and swaps. 
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T19, A, 2/)' ), "Array sorted in ascending order using insertion sort:" 
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 10. Read the disk file, "data8.txt". Each line in it contains:
  !  First Name (10 characters long)  
  !  Last Name  (10 characters long)
  !  Password   (8 characters long)

  ! Open the input file and check for errors.
  OPEN( UNIT = fileNumber, FILE = fileName, STATUS = "OLD", ACTION = "READ", IOSTAT = openStatus )
  IF ( OpenStatus > 0 ) STOP "*** Error Opening File ***"
 
  DO I = 1, NAME_ARRAY_SIZE
    ! Read file and check for EOF or input errors.
    READ ( UNIT = fileNumber, FMT = *, IOSTAT = inputStatus), FirstName(I), LastName(I), Password(I)

    IF ( inputStatus < 0 ) EXIT
    IF ( inputStatus > 0 ) STOP "*** Input File Error ***"
 
  END DO
 
  CLOSE( fileNumber ) 


  ! 10. Print the Sorted Arrays. 
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T20, A, 2/)' ), "NAMES BEFORE SORTING"
  CALL printPersonInfo( FirstName, LastName, Password )

  ! 11. Sort all three arrays in descending order by Last Name using Selection Sort.
  CALL selectionSortNames(LastName, FirstName, Password)

  ! 12. Print the Sorted Arrays. 
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T20, A, 2/)' ), "Descending order by Last Name using Selection Sort:"
  CALL printPersonInfo( FirstName, LastName, Password )

  ! 13. Sort all three arrays in descending order by Password using Insertion Sort.
  CALL insertionSortNames( Password, FirstName, LastName)

  ! 14. Print the Sorted Arrays.
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T20, A, 2/)' ), "Descending Order by Password using Insertion Sort:"
  CALL printPersonInfo( FirstName, LastName, Password )



  ! EXTRA CREDIT PART
  

  ! PART A:   Fill the array with 245.0, 243.0, 241.0, 239.0, 237.0, ..., 5.0
  RealArray(1) = 245.0
  DO I = 2, REAL_ARRAY_SIZE 
    RealArray(I) = RealArray(I-1) - 2    ! Fill the array.
  END DO 

  ! Copy the array into the spare copy.
  RealArrayCopy = RealArray 

  ! 3. Print the array.
  CALL heading(DateDisplay) ! Print a heading.
  NumComparisions = 0  ! Reset counter variables to neatly print out the array ( See print real array ).
  NumSwaps = 0
  WRITE ( *, '(T14, A, 2/)' ), "EXTRA CREDIT - Array of 245.0, 243.0, 241.0, 239.0, 237.0, ..., 5.0."
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 4. Sort the array ascending using selection sort.
  CALL selectionSortReal( RealArray, NumComparisions, NumSwaps )

  ! 5. Print the array and the number of comparisons and swaps.
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T14, A, 2/)' ), "EXTRA CREDIT - Array sorted in ascending order using selection sort:"
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 6. Reset the array to its original contents and sort it using bubble sort.
  RealArray = RealArrayCopy  ! Reset the array.
  CALL bubbleSortReal( RealArray, NumComparisions, NumSwaps )
  
  ! 7. Print the array and the number of comparisons and swaps.
  CALL heading(DateDisplay) ! Print a heading. 
  WRITE ( *, '(T14, A, 2/)' ), "EXTRA CREDIT - Array sorted in ascending order using bubble sort:"
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 8. Reset the array to its original contents and sort it using bubble sort.
  RealArray = RealArrayCopy  ! Reset the array.
  CALL insertionSortReal( RealArray, NumComparisions, NumSwaps )

  ! 9. Print the array and the number of comparisons and swaps. 
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T14, A, 2/)' ), "EXTRA CREDIT - Array sorted in ascending order using insertion sort:" 
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 


  ! PART C:   Fill the array with 5.5, 7.5, 9.5, 11.5, ..., 245.5
  RealArray(1) = 5.5
  DO I = 2, REAL_ARRAY_SIZE 
    RealArray(I) = RealArray(I-1) + 2    ! Fill the array.
  END DO 

  ! Copy the array into the spare copy.
  RealArrayCopy = RealArray 


  ! 3. Print the array.
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T14, A, 2/)' ), "EXTRA CREDIT - Array of: 5.5, 7.5, 9.5, 11.5, ..., 245.5"
  NumComparisions = 0  ! Reset counter variables to neatly print out the array ( See print real array ).
  NumSwaps = 0
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 4. Sort the array ascending using selection sort.
  CALL selectionSortReal( RealArray, NumComparisions, NumSwaps )

  ! 5. Print the array and the number of comparisons and swaps.
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T14, A, 2/)' ), "EXTRA CREDIT - Array sorted in ascending order using selection sort:"
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 6. Reset the array to its original contents and sort it using bubble sort.
  RealArray = RealArrayCopy  ! Reset the array.
  CALL bubbleSortReal( RealArray, NumComparisions, NumSwaps )
  
  ! 7. Print the array and the number of comparisons and swaps.
  CALL heading(DateDisplay) ! Print a heading. 
  WRITE ( *, '(T14, A, 2/)' ), "EXTRA CREDIT - Array sorted in ascending order using bubble sort:"
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 

  ! 8. Reset the array to its original contents and sort it using bubble sort.
  RealArray = RealArrayCopy  ! Reset the array.
  CALL insertionSortReal( RealArray, NumComparisions, NumSwaps )

  ! 9. Print the array and the number of comparisons and swaps. 
  CALL heading(DateDisplay) ! Print a heading.
  WRITE ( *, '(T14, A, 2/)' ), "EXTRA CREDIT - Array sorted in ascending order using insertion sort:" 
  CALL printRealArray( RealArray, NumComparisions, NumSwaps ) 


!!!  END  OF  MAIN  EXECUTABLE  CODE  !!! 


CONTAINS

  !-----------------------------------------------------------------------------
  ! SUBROUTINE swapReal(Array, i1, i2)
  !  
  !  Description:   Swaps two real values, in an array.  The indicies of the
  !                two positions to be swapped.
  !
  !  Input: Array - The array of real numbers two be swapped.
  !         i1, i2 - The indicies of the two positions to be swapped.
  !
  !  Output: None
  !-----------------------------------------------------------------------------
  SUBROUTINE swapReal(Array, i1, i2)
    IMPLICIT NONE
    REAL, DIMENSION(:), INTENT(INOUT) :: Array
     
    REAL :: temp
    INTEGER, INTENT(IN) :: i1, i2

    temp = Array(i1)
    Array(i1) = Array(i2)
    Array(i2) = temp

  END SUBROUTINE swapReal


  !-----------------------------------------------------------------------------
  ! SUBROUTINE swapCharacter(Array, i1, i2)
  !  
  !  Description:   Swaps two strings, in an array.  The indicies of the
  !                two positions to be swapped.
  !
  !  Input: Array - The array of strings two be swapped.
  !         i1, i2 - The indicies of the two positions to be swapped.
  !
  !  Output: None
  !-----------------------------------------------------------------------------
  SUBROUTINE swapCharacter(Array, i1, i2)
    IMPLICIT NONE
    CHARACTER(10), DIMENSION(:), INTENT(INOUT) :: Array
     
    CHARACTER(10) :: temp
    INTEGER, INTENT(IN) :: i1, i2

    temp = Array(i1)
    Array(i1) = Array(i2)
    Array(i2) = temp

  END SUBROUTINE swapCharacter


  !-----------------------------------------------------------------------------
  ! SUBROUTINE insertionSortReal( RealArray, NumComparisions, NumSwaps )
  !  
  !  Description:   Sorts an array of real values in ascending order. Also the
  !                number of comparisons and swaps are recorded.
  !
  !  Input: Array - The array of real numbers to be printed.
  !
  !
  !  Output: Records the following information and passes it back to the caller.
  !         NumComparisons - The number of comparisons the previous sort has taken.
  !         NumSwaps - The number of swaps the previous sort has taken.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE insertionSortReal( RealArray, NumComparisions, NumSwaps )
    IMPLICIT NONE
    REAL, DIMENSION(:), INTENT(INOUT) :: RealArray 
    INTEGER, INTENT(OUT) :: NumComparisions, NumSwaps
 
    INTEGER :: I, J, N

    NumComparisions = 0
    NumSwaps = 0
    N = SIZE(RealArray)

    DO I = 2, N
      J = I

      NumSwaps = NumSwaps + 1  ! Update the number of swaps
      DO while ( J > 1 .AND. RealArray(J) < RealArray(J-1) )
        NumComparisions = NumComparisions + 1  ! Update the number of comparisons.
        CALL swapReal( RealArray, J, J-1 )  ! Not really swaps, more like shifts.
        J = J - 1
      END DO
    
    END DO

  END SUBROUTINE insertionSortReal


  !-----------------------------------------------------------------------------
  ! SUBROUTINE bubbleSortReal( RealArray, NumComparisions, NumSwaps )
  !  
  !  Description:   Sorts an array of real values in ascending order. Also the
  !                number of comparisons and swaps are recorded.
  !
  !  Input: Array - The array of real numbers to be printed.
  !
  !
  !  Output: Records the following information and passes it back to the caller.
  !         NumComparisons - The number of comparisons the previous sort has taken.
  !         NumSwaps - The number of swaps the previous sort has taken.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE bubbleSortReal( RealArray, NumComparisions, NumSwaps )
    IMPLICIT NONE
    REAL, DIMENSION(:), INTENT(INOUT) :: RealArray
    INTEGER, INTENT(OUT) :: NumComparisions, NumSwaps

    INTEGER :: I, J, N

    NumComparisions = 0
    NumSwaps = 0
    N = SIZE(RealArray)

    DO I = 1, N-1
      DO J = 1, N-1
        NumComparisions = NumComparisions + 1  ! Update the number of comparisons.
        IF ( RealArray(J) > RealArray(J+1) )  THEN
          NumSwaps = NumSwaps + 1  ! Update the number of swaps
          CALL swapReal( RealArray, J, J+1 )
        ENDIF
      END DO
   END DO

  END SUBROUTINE bubbleSortReal


  !-----------------------------------------------------------------------------
  ! SUBROUTINE selectionSortReal( RealArray, NumComparisions, NumSwaps )
  !  
  !  Description:   Sorts an array of real values in ascending order. Also the
  !                number of comparisons and swaps are recorded.
  !
  !  Input: Array - The array of real numbers to be printed.
  !
  !
  !  Output: Records the following information and passes it back to the caller.
  !         NumComparisons - The number of comparisons the previous sort has taken.
  !         NumSwaps - The number of swaps the previous sort has taken.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE selectionSortReal( Array, NumComparisions, NumSwaps )
    IMPLICIT NONE
    REAL, DIMENSION(:), INTENT(INOUT) :: Array
    INTEGER, INTENT(OUT) :: NumComparisions, NumSwaps

    INTEGER :: N, I, J, iMin
    NumComparisions = 0 
    NumSwaps = 0 

    NumComparisions = 0
    NumSwaps = 0
    N = SIZE(Array)
    

    DO I = 1, N-1 
      iMin = I

      ! Find the new minumum.
      Do J = I+1, N
   
        ! Check to see if this is a minimum 
        NumComparisions = NumComparisions + 1  ! Update the number of comparisons.
        IF ( Array(J) < Array(iMin) ) iMin = J
      END DO
    
    ! Don't swap unless needed.
    IF ( iMin /= I ) THEN
      NumSwaps = NumSwaps + 1  ! Update the number of swaps.
      CALL swapReal( Array, I, iMin )  ! Swap the values
    ENDIF
 
    END DO

  END SUBROUTINE selectionSortReal


  !-----------------------------------------------------------------------------
  ! SUBROUTINE selectionSortNames( Array1, Array2, Array3 )
  !  
  !  Description:   Sorts three arrays of characters in descending order.
  !
  !  Input: Array# - The arrays to be sorted.  The first array is the one we
  !                  sort by.  The other two are sorted based on the entries of 
  !                  first array.
  !
  !  Output: None
  !-----------------------------------------------------------------------------
  SUBROUTINE selectionSortNames( Array1, Array2, Array3 )
    IMPLICIT NONE
    CHARACTER(10), DIMENSION(:), INTENT(INOUT) :: Array1, Array2, Array3

    INTEGER :: N, I, J, iMin
    N = SIZE(Array1)
    
    DO I = 1, N-1     
      iMin = I

      ! Find the new minumum.
      Do J = I+1, N-1
   
        ! Check to see if this is a maximum 
        IF ( Array1(J) > Array1(iMin) ) iMin = J
      END DO
    
      ! Swap the calues in all three arrays to keep everything in order.
      CALL swapCharacter( Array1, I, iMin )
      CALL swapCharacter( Array2, I, iMin )
      CALL swapCharacter( Array3, I, iMin )
     
    END DO
  END SUBROUTINE selectionSortNames


  !-----------------------------------------------------------------------------
  ! SUBROUTINE insertionSortNames( Array1, Array2, Array3 )
  !  
  !  Description:   Sorts three arrays of characters in descending order.
  !
  !  Input: Array# - The arrays to be sorted.  The first array is the one we
  !                  sort by.  The other two are sorted based on the entries of 
  !                  first array.
  !
  !  Output: None
  !-----------------------------------------------------------------------------
  SUBROUTINE insertionSortNames( Array1, Array2, Array3 )
    IMPLICIT NONE
    CHARACTER(10), DIMENSION(:), INTENT(INOUT) :: Array1, Array2, Array3
 
    INTEGER :: I, J, N

    N = SIZE(Array1)

    ! Do the insertion sort.
    DO I = 2, N
      J = I

      DO WHILE ( J > 1 .AND. Array1(J) > Array1(J-1) )

        CALL swapCharacter( Array1, J, J-1 )  ! Not really swaps, more like shifts...
        CALL swapCharacter( Array2, J, J-1 )
        CALL swapCharacter( Array3, J, J-1 )
      
        J = J - 1
      END DO    
    END DO
    
  END SUBROUTINE insertionSortNames


  !-----------------------------------------------------------------------------
  ! SUBROUTINE printRealArray( Array, NumComparisons, NumSwaps )
  !  
  !  Description:   This subroutine prints the contents of a real array of values
  !                and prints them 9 items per line.
  !
  !         Input:  Array - The array of real numbers to be printed.
  !                 NumComparisons - The number of comparisons the previous sort 
  !                                   has taken.
  !                 NumSwaps - The number of swaps the previous sort has taken.
  !
  !  Output:    Prints the contents of the array and the swaps and comparison 
  !            count.  If there are no swaps and no comparisons we can assume 
  !            there was no sort performed and therefore the swap and comparison
  !            information is not needed and a message is printed.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE printRealArray( Array, NumComparisons, NumSwaps )
    IMPLICIT NONE
    REAL, DIMENSION(:), INTENT(IN) :: Array
    INTEGER, INTENT(IN) :: NumComparisons, NumSwaps

    ! Print Array.
    210 FORMAT( 9( 3X, F6.2 ), 2/ )
    WRITE (*, 210), Array
 
    ! Print the stats information if nessecary.
    IF ( NumComparisons > 0 .AND. NumSwaps > 0 ) THEN
      220 FORMAT( 2/, T24, A, I8, / )
      WRITE (*, 220), "Number of comparisons performed: ", NumComparisons
      WRITE (*, 220), "Number of swaps performed:       ", NumSwaps 
    ELSE
      WRITE (*, 220), "NO SWAPS OR COMPARISONS PERFORMED." 
    END IF

    WRITE (*, '(2/)')
  END SUBROUTINE printRealArray
 

  !-----------------------------------------------------------------------------
  ! SUBROUTINE printPersonInfo( Array1, Array2, Array3 )
  !  
  !  Description:   This subroutine prints the contents of the three arrays
  !                containing the peoples informations. 
  !
  !  Input:  The three character arrays to be printed.
  !
  !  Output: The arrays of inforamtion printed in nice neat columns. 
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE printPersonInfo( Array1, Array2, Array3)
    IMPLICIT NONE
    CHARACTER(10), DIMENSION(:), INTENT(IN) :: Array1, Array2, Array3
    INTEGER :: I

    ! Print Array.
    310 FORMAT( 22X, 3( A10, 6X ) )

    ! Print the three arrays.
    WRITE (*, 310),"First Name ", "Last Name ", " Password "
    WRITE (*, 310),"-----------", "----------", "----------"
    
    DO I = 1, SIZE(Array1)-1
      WRITE (*, 310), Array1(I), Array2(I), Array3(I)
    END DO  
 
  END SUBROUTINE printPersonInfo


  !-----------------------------------------------------------------------------
  ! SUBROUTINE heading(date)
  !  
  !  Description:   Prints out the heading with the date and page number.
  !
  !  Input:    The date as calculated by the calling program as follows:
  !                     10 characters as:  "MM/DD/YYYY"
  !
  !  Output:    The heading, date, and page number are printed along with the page 
  !            number being updated.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE heading(date)
    IMPLICIT NONE
    CHARACTER(10), INTENT(IN) :: date

    ! Subroutine Variables
    INTEGER, SAVE :: pageNumber = 1 

    ! Program Information Header.
    WRITE (*, '( 4/, A, T30, A, T80, A, I2, / )' ), date, "Sorting - By: Nicholas Nagrodski", "Page: ", pageNumber 

    pageNumber = pageNumber + 1 

  END SUBROUTINE heading


END PROGRAM PROG8