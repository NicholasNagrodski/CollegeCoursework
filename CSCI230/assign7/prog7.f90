PROGRAM PROG7
!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   7                          Due Date:  11/01/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program takes an interval (A,B) and one of the functions 
!                defined below, N, which is designated by the input.  It then 
!                calculates the minimum and maxumim values along with thier 
!                locations.  The average value is computed with the number of 
!                sampling points, N. 
!                
!
!         F1(X) = ALOG10(X)
!         F2(X) is defined in three parts:
!             for X < -2.0, F2(X) = -0.6 * X - 0.2
!             for X between -2.0 and 3.0, F2(X) = 0.2 * X + 1.4
!             for X > 3.0, F2(X) = -1.2 * X + 5.6
!         F3(X) = 2.8 - 2.0 * SIN(X - 1.5)
!         F4(X) = 3.0 * LOG(X) - 0.5 * X
!         F5(X) = SQRT(36.0 - 2.5 * X)
!         F6(X) = (2.0 * X + 3.0) / (X ** 4 + 9.0)
!         F7(X) = 0.25 * ABS(X - 1.0) - 1.75 * ABS(X - 5.0) + 1.5 * ABS(X - 7.0)
!         F8(X) = 2.0 * X ** 3 - 6.0 * X ** 2 - 48.0 * X + 216.0
!
!  Input:
!     a REAL value A in F6.2 format (left endpoint)
!     5 spaces
!     a REAL value B in F6.2 format (right endpoint)
!     5 spaces
!     an INTEGER value M in I1 format (which function to use)
!     5 spaces  
!     one INTEGER value N in I3 format (number of subdivisions to use)
!
!                 "AAA.AA     BBB.BB     M     NNN"
!
!  Output:        A report calculating the above information.
!                                                                   
!-------------------------------------------------------------------------------

  IMPLICIT NONE

  ! Input Variables as defined above.
  REAL :: A, B
  INTEGER :: M, N 
 
  ! Program variables.
  REAL :: Min, Max, X_Min, X_Max, Avg


  ! Define file information.
  INTEGER, PARAMETER :: fileNumber = 9
  INTEGER :: inputStatus, openStatus
  CHARACTER(20), PARAMETER :: fileName = "data7.txt"


  ! External Functions.
  REAL, EXTERNAL :: F1, F2, F3, F4, F5, F6, F7, F8

  ! Function Descriptions.
  CHARACTER(50) :: functionDescriptions(9) = (/& 
    'ALOG10(X)                                     ',&
    '(defined in three parts, a polygonal line)    ',& 
    '2.8 - 2.0 * SIN(X - 1.5)                      ',&
    '3.0 * LOG(X) - 0.5 * X                        ',&
    'SQRT(36.0 - 2.5 * X)                          ',&
    '(2.0 * X + 3.0) / (X ** 4 + 9.0)              ',&
    '0.25*ABS(X-1.0)-1.75*ABS(X-5.0)+1.5*ABS(X-7.0)',&
    '2.0 * X ** 3 - 6.0 * X ** 2 - 48.0 * X + 216.0',&
    'ERROR: INTERVAL IS INVALID                    '/)


!!! MAIN  EXECUTABLE  CODE  !!!


  ! Open the input file and check for errors.
  OPEN( UNIT = fileNumber, FILE = fileName, STATUS = "OLD", ACTION = "READ", IOSTAT = openStatus )
  IF ( OpenStatus > 0 ) STOP "*** Error Opening File ***"

 
  DO
    ! Read file and check for EOF or input errors.
    READ ( UNIT = fileNumber, FMT = *, IOSTAT = inputStatus) A, B, M, N

    IF ( inputStatus < 0 ) EXIT
    IF ( inputStatus > 0 ) STOP "*** Input File Error ***"


    ! Check to see if the Interval, input function, and number of divisions is valid.
    !  If so, choose the appropriate function. Otherwise print an error.
    IF ( B >= A .AND. (M <= 8 .AND. M >= 1) .AND. N >= 1 ) THEN
      SELECT CASE(M)
        CASE(1)
          CALL calculateInfo(F1, A, B, N, Min, Max, X_Min, X_Max, Avg)
        CASE(2)
          CALL calculateInfo(F2, A, B, N, Min, Max, X_Min, X_Max, Avg)
        CASE(3)
          CALL calculateInfo(F3, A, B, N, Min, Max, X_Min, X_Max, Avg)
        CASE(4)
          CALL calculateInfo(F4, A, B, N, Min, Max, X_Min, X_Max, Avg)
        CASE(5)
          CALL calculateInfo(F5, A, B, N, Min, Max, X_Min, X_Max, Avg)
        CASE(6)
          CALL calculateInfo(F6, A, B, N, Min, Max, X_Min, X_Max, Avg)
        CASE(7)
          CALL calculateInfo(F7, A, B, N, Min, Max, X_Min, X_Max, Avg)
        CASE(8)
          CALL calculateInfo(F8, A, B, N, Min, Max, X_Min, X_Max, Avg)
      END SELECT
 
      ! After calculating the information, print the information.
      CALL printInformation(functionDescriptions(M), A, B, Min, Max, X_Min, X_Max, Avg)
     
    ELSE   ! Interval is invalid, print an error messsage.
      CALL printInformation(functionDescriptions(9), A, B, Min, Max, X_Min, X_Max, Avg)
    END IF

  END DO
 
  CLOSE( fileNumber ) 

!!!  END  OF  MAIN  EXECUTABLE  CODE  !!! 


CONTAINS


  !-----------------------------------------------------------------------------
  !  REAL FUNCTION average(F, A, B, N)
  !
  !   Description:  This functions calculates average value of a function over
  !                N+1 points.
  !         Input:  F    - The function to be computed.
  !                 A/B  - The lower and upper bouunds of the interval.
  !                 N    - The number of divisions on the interval.
  !
  ! Returns value:  The average value of the function.
  !
  !-----------------------------------------------------------------------------
  REAL FUNCTION average(F, A, B, N)
    IMPLICIT NONE
    REAL, INTENT(IN) :: A, B
    INTEGER, INTENT(IN) :: N
    REAL, EXTERNAL :: F

    ! Variables for average calculation.    
    REAL :: X, avg, divisionSize

    avg = 0
    divisionSize = (B-A) / N
    X = A

    ! Calculate the average.
    DO While ( X <= B )
      avg = avg + F(X)
      X = X + divisionSize    
    END DO

    average = avg / (N+1)   
 
  END FUNCTION average


  !-----------------------------------------------------------------------------
  ! SUBROUTINE findMaxInfo(F, A, B, N, Max, X_Max)
  !  
  !  Description:   This subroutine finds the maximum value on the interval with the
  !                given number of divisions, N.  It passes back the value and 
  !                the X-value location.  
  !
  !         Input:  F    - The function to be checked.
  !                 A/B  - The lower and upper bouunds of the interval.
  !                 N    - The number of divisions on the interval.
  !                 Max  - The maximum value on the interval.
  !                 X_Max- The location on the interval of the maximum value.
  !
  !  Output:    Passes back the values of Max and X_Max
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE findMaxInfo(F, A, B, N, Max, X_Max)
    IMPLICIT NONE
    REAL, EXTERNAL :: F
    REAL, INTENT(IN) :: A, B
    INTEGER, INTENT(IN) :: N
    REAL, INTENT(OUT) :: Max, X_Max   

    REAL :: X, divisionSize    
    X = A
    X_Max = A
    divisionSize = (B-A)/N
    
    Max = F(A)
    DO WHILE ( X <= B )
      IF ( F(X) > Max ) THEN
        Max = F(X)
        X_Max = X
      END IF
      X = X + divisionSize
    END DO
  END SUBROUTINE findMaxInfo


  !-----------------------------------------------------------------------------
  ! SUBROUTINE findMinInfo(F, A, B, N, Min, X_Min)
  !  
  !  Description:   This subroutine finds the minimum value on the interval with the
  !                given num0ber of divisions, N.  It passes back the value and 
  !                the X-value location.  
  !
  !         Input:  F    - The function to be checked.
  !                 A/B  - The lower and upper bouunds of the interval.
  !                 N    - The number of divisions on the interval.
  !                 Min  - The minimum value on the interval.
  !                 X_Min- The location on the interval of the minimum value.
  !
  !  Output:    Passes back the values of Min and X_Min
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE findMinInfo(F, A, B, N, Min, X_Min)
    IMPLICIT NONE
    REAL, EXTERNAL :: F
    REAL, INTENT(IN) :: A, B
    INTEGER, INTENT(IN) :: N
    REAL, INTENT(OUT) :: Min, X_Min   

    REAL :: X, divisionSize 
    X = A   
    X_Min = A
    divisionSize = (B-A)/N
    
    Min = F(A)
    DO WHILE ( X <= B )
      IF ( F(X) < Min ) THEN
        Min = F(X)
        X_Min = X
      END IF
      X = X + divisionSize
    END DO
  END SUBROUTINE findMinInfo


  !-----------------------------------------------------------------------------
  ! SUBROUTINE calculateInfo(F, A, B, N, Min, Max, X_Min, X_Max, Avg)
  !  
  !  Description:   This subroutine prints the column headings and the   
  !                appropriate page number when appropraite. 
  !
  !  Input:    The current page number.
  !
  !  Output:    The heading and page number are printed along with the page 
  !            number being updated.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE calculateInfo(F, A, B, N, Min, Max, X_Min, X_Max, Avg)
    IMPLICIT NONE
    REAL, EXTERNAL :: F
    REAL, INTENT(IN) :: A, B
    INTEGER, INTENT(IN) :: N
    REAL, INTENT(OUT) :: Min, Max, X_Min, X_Max, Avg

    ! If there is no interval, then the calculations are trivial.
    If( A == B ) THEN
      Avg = F(A)
      Min = F(A)
      Max = F(A)
      X_Min = A
      X_Max = A   
    ELSE
      ! Otherwise call routines to find the information.
      CALL findMaxInfo(F, A, B, N, Max, X_Max)
      CALL findMinInfo(F, A, B, N, Min, X_Min)
      Avg = average(F, A, B, N)   
    END IF

  END SUBROUTINE calculateInfo


  !-----------------------------------------------------------------------------
  ! SUBROUTINE printInformation(functionDescription, A, B, N, Min, Max, X_Min, X_Max, Avg)
  !  
  !  Description:   This subroutine prints the column headings and the   
  !                appropriate page number when appropraite. 
  !
  !  Input:    The current page number.
  !
  !  Output:    The heading and page number are printed along with the page 
  !            number being updated.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE printInformation(description, A, B, Min, Max, X_Min, X_Max, Avg)
    IMPLICIT NONE
    ! Input Variables
    REAL, EXTERNAL :: F
    REAL, INTENT(IN) :: A, B, Min, Max, X_Min, X_Max, Avg
    ! INTEGER, INTENT(IN) :: M
    CHARACTER(50), INTENT(IN) :: description

    ! Subroutine Variables
    INTEGER, SAVE :: inputCount = 0, pageNumber = 1 
    INTEGER, PARAMETER :: ItemsPerPage = 15
    CHARACTER(8) :: Date, DateDisplay*10

    ! Print a new page heading if needed.
    IF ( MOD(inputCount,ItemsPerPage) == 0 ) THEN
      210 FORMAT(5(A,4X))
    
      ! Get the Date and format it into a readable format.
      CALL DATE_AND_TIME(DATE)     ! DATE = YYYYMMDD
      DateDisplay = DATE(5:6)//'/'//DATE(7:8)//'/'//DATE(1:4)    ! DateDisplay = "MM/DD/YYYY"
      
      ! Program Information Header.
      PRINT '( 4/, T55, A )','FUNCTION PROPERTIES - By: Nicholas Nagrodski'
      PRINT '( A, A, T120, A, I2, 2/ )',"Date: ",DateDisplay,'Page Number: ',pagenumber

      ! Table Header.
      PRINT 210,"     Interval       ","    Function                                  ","      Maximum        ",&
                "      Minimum        "," Average "
      PRINT 210," -------------------","----------------------------------------------","---------------------",&
                "---------------------","---------"
      pageNumber = pageNumber + 1 
    END IF

    inputCount = inputCount + 1

    ! Invalid Interval.
    IF ( B < A ) THEN   
      200 FORMAT( 2/, 2(A,F7.3), A, 4X, A50 )
      PRINT 200,'  [',A, ',' ,B,' ]',description
    ELSE    ! Valid interval.
      ! Print the Line of information.
      220 FORMAT( 2/, 2(A,F7.3), A, 4X, A50, 2(F7.3, A, F7.2, 4X), 3X, F7.3)
      PRINT 220,'  (',A, ',' ,B,' )',description, Max,' at X=',X_Max,Min,' at X=',X_Min,Avg      
    END IF
  END SUBROUTINE printInformation

END PROGRAM PROG7


! External function definitions.

REAL FUNCTION F1(X)
  IMPLICIT NONE
  REAL, INTENT(IN) :: X
  F1 = ALOG10(X)
END FUNCTION F1

REAL FUNCTION F2(X)
!-----------------------------------------------------------------------------
!  REAL FUNCTION F2(X): is defined in three parts:
!                      for X < -2.0, F7(X) = -0.6 * X - 0.2
!                      for X between -2.0 and 3.0, F7(X) = 0.2 * X + 1.4
!    		         for X > 3.0, F7(X) = -1.2 * X + 5.6
!-----------------------------------------------------------------------------
  IMPLICIT NONE
  REAL, INTENT(IN) :: X

  IF(X < -2.0) THEN
    F2 = -0.6 * X - 0.2   
  ELSE IF(X > 3.0) THEN
    F2 = -1.2 * X + 5.6
  ELSE
    F2 = 0.2 * X + 1.4
  END IF
END FUNCTION F2

REAL FUNCTION F3(X)
  IMPLICIT NONE
  REAL, INTENT(IN) :: X
  F3 = 2.8 - 2.0 * SIN(X - 1.5)
END FUNCTION F3  

REAL FUNCTION F4(X)
  IMPLICIT NONE
  REAL, INTENT(IN) :: X
  F4 = 3.0 * LOG(X) - 0.5 * X
END FUNCTION F4

REAL FUNCTION F5(X)
  IMPLICIT NONE
  REAL, INTENT(IN) :: X
  F5 = SQRT(36.0 - 2.5 * X)
END FUNCTION F5

REAL FUNCTION F6(X)
  IMPLICIT NONE
  REAL, INTENT(IN) :: X
  F6 = (2.0 * X + 3.0) / (X ** 4 + 9.0)
END FUNCTION F6

REAL FUNCTION F7(X)
  IMPLICIT NONE
  REAL, INTENT(IN) :: X
  F7 = 0.25 * ABS(X - 1.0) - 1.75 * ABS(X - 5.0) + 1.5 * ABS(X - 7.0)
END FUNCTION F7

REAL FUNCTION F8(X)
  IMPLICIT NONE
  REAL, INTENT(IN) :: X
  F8 = 2.0 * X ** 3 - 6.0 * X ** 2 - 48.0 * X + 216.0
END FUNCTION F8