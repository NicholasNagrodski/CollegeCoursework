
PROGRAM PROG4
!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   4                          Due Date:  10/01/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program caclulates the Body Mass Index (BMI) of an 
!                individual.  The BMI is calculated as follows...
!
!     BMI = (Weight in kilograms) / (Height in meters) ** 2
!  or
!     BMI = 705 * (Weight in pounds) / (Height in inches) ** 2
!
!                 A person whose BMI is 18.50 or less is considered underweight, 
!                and a person whose BMI is >= 25.00 is considered overweight.
! 
!
!  Input:         Each entry contains three lines.  The first line is the name
!                of the person and is limited to 15 character.  The second line
!                is the integer height of the person in inches.  The third line 
!                is the weight of the person in pounds.  Then end of file data
!                is three lines consisting of 'XXXX', '00', and '00'.   
!
!  Output:        The program will output the information of each person and 
!                calculate thier BMI as well as classify them as underweight,
!		 overweight, or normalweight          
!                                                                   
!-------------------------------------------------------------------------------

  IMPLICIT NONE

  ! VARIABLE EXPLANATIONS 
  !   numPeople - number of people in the input
  !   height - input height in inches for the person
  !   weight - input weight of the person in pounds.
  !   name - reads the input file for the name of the person.
  INTEGER :: numPeople = 0, height, weight, totalHeight = 0, totalWeight = 0
  INTEGER :: numOverweight = 0, numUnderweight = 0
  REAL :: bmi, avgWeight, avgHeight, avgbmi
  CHARACTER(15) :: name

  ! Format for reading in input data
  100 FORMAT( A, /, I4, /, I4 )
  
  ! Format for outputting user data.
  110 FORMAT( /, 11X, A19, A15, /, 11X, A18, I3, /, 11X, A18, I4, /, 11X, A18, F6.2 )

  ! Format for printing 
  120 FORMAT( 11X, A, / )

  ! Prime the loop with the first set of data.
  READ 100,name,height,weight

  DO WHILE ( name /= "XXXX" .OR. height /= 0 .OR. weight /= 0 )

    ! Check to see if a page heading needs to be printed.
    IF ( MOD(numPeople,10) == 0 ) THEN
      PRINT '( A, T20, A, I6, 3/ )', "1","BMI INDEX REPORT     PAGE: ", ( numPeople / 10 + 1 )
    END IF

    ! Add data to statistics.
    numPeople = numPeople + 1
    totalHeight = totalHeight + height
    totalWeight = totalWeight + weight

    bmi = 705.0 * weight / height ** 2   ! Calculate BMI

    Print 110, "Name:              ", name, "Height in inches: ", height, "Weight in pounds: ", weight, "Body mass Index:  ", bmi

    IF ( bmi <= 18.5 ) THEN
      numUnderweight = numUnderweight + 1
      PRINT 120,"Status:            Underweight"
    ELSE IF ( bmi >= 25.0 ) THEN
      numOverweight = numOverweight + 1
      PRINT 120,"Status:            Overweight"
    ELSE 
      PRINT 120,"Status:            Normalweight"      
    END IF

    ! Read in the new set of data.
    READ 100, name, height, weight

  END DO

  ! Calculate and print final statistics.
  avgHeight = REAL(totalHeight)/numpeople 
  avgWeight = REAL(totalWeight)/numpeople
  avgBMI = 705.0 * avgWeight / avgHeight**2

  PRINT '( 2/, A )',"    SUMMARY INFORMATION: "
  PRINT '( / T7, A, I4)',"Number of individuals:          ", numPeople
  PRINT '( T7, A, I4 )',"Number of overweight:           ", numOverweight 
  PRINT '( T7, A, I4 )',"Number of underweight:          ", numunderweight 
  PRINT '( T7, A, F6.2)',"Average height (inches):      ", avgHeight
  PRINT '( T7, A, F6.2)',"Average weight (pounds):      ", avgWeight
  PRINT '( T7, A, F6.2, / )',"BMI from avg data:            ", avgBMI
 
END PROGRAM PROG4
