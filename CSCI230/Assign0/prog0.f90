!-------------------------------------------------------------------
!                                                                   
!  Assignment:   0                         Due Date:  08/29/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:  This program declares four variables, asssigns
!                values to them, does some arithmetic, and prints
!                a few lines.
!                                                                   
!--------------------------------------------------------------------
               
       PROGRAM  PROG0

       IMPLICIT NONE

       REAL :: X, Y, Sum, Product
        
       PRINT *, 'My first FORTRAN program in CSCI 230, Autumn, 2010:'
       PRINT *, 'a simple program, but important to learn how to run'
       PRINT *, 'it!'

       X = 1.234
       Y = 5.5
       Sum = X + Y
       Product = X * Y  

       PRINT *
       PRINT *, "Sum of ", X, " and ", Y, "is ", Sum
       PRINT *, "Product of ", X, " and ", Y, "is ", Product

       END PROGRAM PROG0