!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   1b                         Due Date:  09/08/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program calculates the roots of a quadratic equation
!  	        given the input values 'a', 'b', and 'c' of the
!               characteristic equation.  It will read a table of values and   
!               calculate the roots of the quadratic equations.It then prints
!               out the two roots. 
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
       READ *,A,B,C

       DO WHILE (A /= 0.0)

         ! Display the input coefficients.
         PRINT *, 'The coefficients are: ', A, B, C
       
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

         ! Have a few lines to seperate calculations
         PRINT *
         PRINT *
         READ *,A,B,C  ! Read in the next set of values 

       END DO

       END PROGRAM PROG1A