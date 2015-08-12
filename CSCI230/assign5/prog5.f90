PROGRAM PROG5
!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   5                          Due Date:  10/08/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program caclulates the doubling time of a investment with
!                a arbitrary amount - A, an APR - R, and the number of 
!                compounding periods per year - N.  The amount doubles when:
!
!              2A == A * ( 1.0 + R / N )^(N*t)
!
!      Solving for 't' Yeilds: 
!
!                 t = Ceiling( ln(2.0) / ln(1.0 + R / N) )
! 
!                              where 't' is the number of periods.
!
!                 We can then find the number of days it has taken by:
!
!                  #days = 365 * t / N
! 
!
!  Input:         The APR - R (as a real), and the number of compounding periods
!                per year - N (integer).
!
!  Output:        The calculated doubling time.
!                                                                   
!-------------------------------------------------------------------------------

  IMPLICIT NONE

  ! VARIABLE EXPLANATIONS 
  INTEGER :: inputStatus, openStatus

  INTEGER :: N, doublingTime, inputCount = 0, pageNumber = 0
  REAL :: R

  ! Define file information.
  INTEGER, PARAMETER :: fileNumber = 15
  CHARACTER(20), PARAMETER :: fileName = "data5.dat"


  ! Format for outputting user data.
  100 FORMAT( T16, F5.3, 13X, I3, 10X, I2, 7X, I3, / )

  ! Open the input file and check for errors.
  OPEN( UNIT = fileNumber, FILE = fileName, STATUS = "OLD", ACTION = "READ", IOSTAT = openStatus )
  IF ( OpenStatus > 0 ) STOP "*** Error Opening File ***"

 
  DO
    ! Read file and check for EOF or input errors.
    READ ( UNIT = fileNumber, FMT = *, IOSTAT = inputStatus) R, N 

    IF ( inputStatus < 0 ) EXIT
    IF ( inputStatus > 0 ) STOP "*** Input File Error ***"
       
    ! CALL printHeading if needed.
    CALL printHeading( inputCount, pageNumber )
 
    inputCount = inputCount + 1


    ! Doubling time is in "Number of days"
    doublingTime = 365 * CEILING( LOG(2.0) / ( LOG(1.0 + R / N) ) ) / N

    PRINT 100, R, N, (doublingTime/365), MOD(doublingTime,365)

  END DO
 
  CLOSE( fileNumber ) 


! END OF MAIN CODE. 


CONTAINS

  !-----------------------------------------------------------------------------
  ! SUBROUTINE printHeading ( inputCount ,pageNumber )
  !  
  !  Description:   This subroutine prints the column headings and the   
  !                appropriate page number when appropraite. 
  !
  !  Input:    The current number of input entries and the current page number.
  !
  !  Output:    The heading and page number are printed along with the page 
  !            number being updated.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE printHeading( inputCount, pageNumber )
    IMPLICIT NONE
    INTEGER, INTENT(IN) :: inputCount
    INTEGER, INTENT(INOUT) :: pageNumber
    INTEGER, PARAMETER :: MAX_LINES_PER_PAGE = 15

    IF ( MOD( inputCount, MAX_LINES_PER_PAGE ) == 0 ) THEN
 
      ! Update the page number.
      pageNumber = pageNumber + 1 

      ! Print out the new heading.
      PRINT '( 3/, "1", T20, A, I2, / )',"Doubling Times                   Page: ", pageNumber
      PRINT '( T29,A,5X,A / T12,A, / )',"Compoundings","Doubling Time:","Interest Rate      Per Year       Years     Days"
    END IF
          
  END SUBROUTINE printHeading

END PROGRAM PROG5
