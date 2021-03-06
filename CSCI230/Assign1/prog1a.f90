!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   1a                         Due Date:  09/08/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program calculates the roots of a quadratic equation
!  	        given the input values 'a', 'b', and 'c' of the
!               characteristic equation.  It then prints out the two roots     
!
!  Input:         The coefficients: A, B, and C
!
!  Output:        The roots of the quadratic equation or the discriminant if
!               there are no real roots.
!
!              
!                                                                   
!-------------------------------------------------------------------------------
               
       PROGRAM  PROG1A

       IMPLICIT NONE
       REAL :: A, B, C, discriminant, root_1, root_2

       ! Get the coefficients from the user.
       PRINT *,"Enter the coefficients of the quadratic equation"
       PRINT *,"Use the form: A*X^2 + B*X + C"
       READ *,A,B,C
       PRINT *
       
       ! Calculate the Discriminant
       discriminant = B ** 2 - 4.0 * A * C

       ! Check to see if the discriminant is non-negative. If so calculate the
       ! two roots and display them.  Otherwise display the discrimant and a 
       ! "no real roots" message.
       IF ( discriminant >= 0 ) THEN
         discriminant = SQRT( discriminant )
         root_1 = ( -B + discriminant ) / ( 2.0 * A )
         root_2 = ( -B - discriminant ) / ( 2.0 * A )
         PRINT *,"The roots are:", root_1, root_2
       ELSE
         PRINT *,"The discriminant is:", discriminant
         PRINT *,"-> There are no real roots!"
       END IF
       

       END PROGRAM PROG1A