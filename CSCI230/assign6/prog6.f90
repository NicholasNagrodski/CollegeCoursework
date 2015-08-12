PROGRAM PROG6
!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   6                          Due Date:  10/20/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program takes inputs of three real number representing
!		 the length's of the 3 sides of a triangle.  It then determines
!                whether the triange is possible.  If not the sides lengths will
!                be printed.  Otherwise the triangle's area and perimeter will
!                be displayed as well as calculating the triangle as:
!
!         COLLAPSED   -- at least one of the three sides is 0
!         RIGHT       -- one of the angles is a right angle
!         EQUILATERAL -- the three sides are equal
!         ISOCELES    -- two of the three sides are equal
!         SCALENE     -- no two of the three sides are equal 
!
!  Input:         Three real values numbers representing the lengths of the
!                sides of a triangle in the following format:
!
!                 "AA.AA    BB.BB    CC.CC"
!
!  Output:        A report classifing the input data of trangles. 
!                                                                   
!-------------------------------------------------------------------------------

  IMPLICIT NONE

  ! Program variables.
  REAL :: A, B, C  ! Input Variables.
  REAL, PARAMETER :: TOL = 0.1  ! Tolerance used on the FPN comparisons.
 
  ! Define file information.
  INTEGER, PARAMETER :: fileNumber = 15
  INTEGER :: inputStatus, openStatus
  CHARACTER(20), PARAMETER :: fileName = "data6.dat"

  ! Page formatting Variables.
  INTEGER :: inputCount = 0, pageNumber = 0 

  !!! Statement Functions. !!!
  REAL :: perimeter, semiPerimeter
  perimeter( A, B, C ) = A + B + C
  semiPerimeter( A, B, C ) =  ( A + B + C ) / 2.0


!!! MAIN  EXECUTABLE  CODE  !!!


  ! Open the input file and check for errors.
  OPEN( UNIT = fileNumber, FILE = fileName, STATUS = "OLD", ACTION = "READ", IOSTAT = openStatus )
  IF ( OpenStatus > 0 ) STOP "*** Error Opening File ***"
 
  DO
    ! Read file and check for EOF or input errors.
    READ ( UNIT = fileNumber, FMT = *, IOSTAT = inputStatus) A, B, C

    IF ( inputStatus < 0 ) EXIT
    IF ( inputStatus > 0 ) STOP "*** Input File Error ***"

    ! CALL printHeading if needed.
    IF ( MOD(inputCount, 10) == 0 ) CALL printHeading( pageNumber )
  
    inputCount = inputCount + 1

    PRINT '(/)'

    ! Check to see if the triangle is possible.
    IF ( is_Triangle_Possible(A, B, C) ) THEN

      ! Format for outputting user data.
      100 FORMAT( 3(6X, F5.2), 5X, F7.2, 4X, F7.2, 5X, A )
      
      PRINT 100, A, B, C, perimeter(A,B,C), area(A,B,C), "POSSIBLE"
  
      ! Check for Other PROPERTIES.
      110 FORMAT(T62,A)
      IF ( is_Collapsed(A,B,C) ) PRINT 110,"COLLAPSED"
      IF ( is_Right(A,B,C) ) PRINT 110,"RIGHT"
      IF ( is_Equilateral(A,B,C) ) PRINT 110,"EQUILATERAL"
      IF ( is_Isoceles(A,B,C) ) PRINT 110,"ISOCELES"
      IF ( is_Scalene(A,B,C) ) PRINT 110,"SCALENE"


    ELSE   ! Triangle is impossible!
      PRINT '( 3( 5X, F6.2), 2(7X, A) )', A, B, C, "N/A", "  N/A      IMPOSSIBLE"
    END IF

  END DO
 
  CLOSE( fileNumber ) 

!!!  END  OF  MAIN  EXECUTABLE  CODE  !!! 


CONTAINS

  !-----------------------------------------------------------------------------
  !  REAL FUNCTION area( a, b, c )
  !
  !   Description:  This functions calculates the area of a triangle.
  !         Input:  Three real value of the lengths of sides of a triangle.
  ! Returns value:  The area of the triange.
  !
  !-----------------------------------------------------------------------------
  REAL FUNCTION area( A, B, C )
    IMPLICIT NONE
    REAL, INTENT(IN) :: A, B, C
    REAL :: sp   

    ! Caculate the semiPerimeter for use in Heron's formula.
    sp = semiPerimeter( A, B, C ) 
    area = SQRT( sp*(sp-A)*(sp-B)*(sp-C) )   
 
  END FUNCTION area


  !-----------------------------------------------------------------------------
  ! LOGICAL FUNCTION is_Triangle_Possible( a, b, c )
  !
  !   Description:  This functions checks to see if a triangle is possible. 
  !                  ( If  A + B >= C   AND   B + C >= A   AND   C + A >= B )
  !
  !         Input:  Three real value of the lengths of sides of a triangle.
  ! Returns value:  A logical value on whether the triangle is possible.
  !
  !-----------------------------------------------------------------------------
  LOGICAL FUNCTION is_Triangle_Possible( A, B, C )
    IMPLICIT NONE
    REAL, INTENT(IN) :: A, B, C
    
    is_Triangle_Possible = ( A + B >= C .AND. A + C >= B .AND. B + C >= A )
  END FUNCTION is_Triangle_Possible

  !-----------------------------------------------------------------------------
  ! LOGICAL FUNCTION is_Collapsed( a, b, c )
  !
  !   Description:  This functions checks to see if a triangle is collapsed 
  !                    ( If one of the three sides is zero. )
  !                                  
  !         Input:  Three real value of the lengths of sides of a triangle.
  ! Returns value:  A logical value on whether the triangle is collapsed.
  !
  !-----------------------------------------------------------------------------
  LOGICAL FUNCTION is_Collapsed( A, B, C )
    IMPLICIT NONE
    REAL, INTENT(IN) :: A, B, C

    is_Collapsed = ( A == 0 ) .OR. ( B == 0 ) .OR. ( C == 0 ) 
  END FUNCTION is_Collapsed

  !-----------------------------------------------------------------------------
  ! LOGICAL FUNCTION is_Isoceles( a, b, c )
  !
  !   Description:  This functions if a triangle is isoceles. 
  !                   ( If two of the three sides are equal. )
  !
  !         Input:  Three real value of the lengths of sides of a triangle.
  ! Returns value:  A logical value on whether the triangle is isoceles.
  !
  !-----------------------------------------------------------------------------
  LOGICAL FUNCTION is_Isoceles( A, B, C )
    IMPLICIT NONE
    REAL, INTENT(IN) :: A, B, C

    is_Isoceles = ( ABS(A-B) <= TOL  ) .OR. ( ABS(B-C) <= TOL ) .OR. ( ABS(A-C) <= TOL )
  END FUNCTION is_Isoceles

  !-----------------------------------------------------------------------------
  ! LOGICAL FUNCTION is_Equilateral( a, b, c )
  !
  !   Description:  This functions checks if a triangle is equilateral. 
  !                 ( If two of the three sides are equal. )
  !
  !         Input:  Three real value of the lengths of sides of a triangle.
  ! Returns value:  A logical value on whether the triangle is equilateral.
  !
  !-----------------------------------------------------------------------------
  LOGICAL FUNCTION is_Equilateral( A, B, C )
    IMPLICIT NONE
    REAL, INTENT(IN) :: A, B, C

    is_Equilateral = ( ABS(A-B) <= TOL  ) .AND. ( ABS(B-C) <= TOL ) 
  END FUNCTION is_Equilateral

  !-----------------------------------------------------------------------------
  ! LOGICAL FUNCTION is_Scalene( a, b, c )
  !
  !   Description:  This functions checks if a triangle is Scalene.
  !                 ( If none of the three sides are equal. )
  !
  !         Input:  Three real value of the lengths of sides of a triangle.
  ! Returns value:  A logical value on whether the triangle is scalene.
  !
  !-----------------------------------------------------------------------------
  LOGICAL FUNCTION is_Scalene( A, B, C )
    IMPLICIT NONE
    REAL, INTENT(IN) :: A, B, C

    is_Scalene = ( ABS(A-B) >= TOL  ) .AND. ( ABS(B-C) >= TOL ) .AND. ( ABS(A-C) >= TOL )
  END FUNCTION is_Scalene

  !-----------------------------------------------------------------------------
  ! LOGICAL FUNCTION is_Right( a, b, c )
  !
  !   Description:  This functions checks if a triangle is a right triangle.
  !                 ( If   A**2 + B**2 ~= C**2  or )
  !                 (      B**2 + C**2 ~= A**2  or )
  !                 (      C**2 + A**2 ~= B**2     )
  !
  !         Input:  Three real value of the lengths of sides of a triangle.
  ! Returns value:  A logical value on whether the triangle is equilateral.
  !
  !-----------------------------------------------------------------------------
  LOGICAL FUNCTION is_Right( A, B, C )
    IMPLICIT NONE
    REAL, INTENT(IN) :: A, B, C

    is_Right = ABS( A**2 + B**2 - C**2 ) <= TOL .OR.&
               ABS( B**2 + C**2 - A**2 ) <= TOL .OR.&
               ABS( C**2 + A**2 - B**2 ) <= TOL 
  END FUNCTION is_Right

  !-----------------------------------------------------------------------------
  ! SUBROUTINE printHeading ( pageNumber )
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
  SUBROUTINE printHeading( pageNumber )
    IMPLICIT NONE
    INTEGER :: pageNumber

    ! Heading Format
    210 FORMAT( 6(5X, A) )


    ! Update the page number.
    pageNumber = pageNumber + 1 

     ! Print out the new heading.
    PRINT '( 3/, T20, A, I2, 2/ )',"Kinds of Triangles                        Page: ",pageNumber

    PRINT 210,"Side A","Side B","Side C","Perimiter","Area","Properties" 
    PRINT 210,"------","------","------","---------","----","----------"

  END SUBROUTINE printHeading

END PROGRAM PROG6
