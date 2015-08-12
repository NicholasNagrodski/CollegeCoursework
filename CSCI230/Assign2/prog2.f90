PROGRAM  PROG2
!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   2                          Due Date:  09/15/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program caculate the best fit line to the equation:
!                                      "Y = MX + B" 
!              
!                 It does this by computing the least squares for this 
!                particular regression formula.
!
!  Input:         Pairs of coordinates, seperated by a line.  Each data set is 
!                seperated by the marker of '-777.0'  The end of the file is 
!                marked with the flag '-999.0'
!
!  Output:        The slope and y-intercept are calclated for the best-fit line
!                for the given set of data.
!                                                                   
!-------------------------------------------------------------------------------               

  IMPLICIT NONE
  REAL :: X, Y, sumX, SumY, Sumx2, SumXY, XMean, YMean, Slope, YInt
  INTEGER :: count

  ! Prime the loop with the first set of data points.
  READ *,X,Y

  ! This loop reads the input file until the end of file marker '-999.0'.
  DO WHILE (X /= -999.0)

    ! Reset Variables for each new set of datapoints.
    Count = 0
    SumX = 0
    SumY = 0
    SumX2 = 0
    SumXY = 0

    PRINT *,"*** Starting new set of data ***"

    ! This loop reads in each set of data points and keeps a running 
    !  total of the values needed to calculate the linear least 
    !  squares regression.
    DO WHILE (X /= -777.0)         

      ! Process all input information.
      count = count + 1
      SumX = SumX + X
      SumY = SumY + Y
      SumX2 = SumX2 + X**2
      SumXY = SumXY + X*Y

      PRINT '(1X, "X", I1, "=", F10.4, "     Y", I1, "=", F10.4)',count,X,count,Y

      READ *,X,Y     ! Read in the next set of values 

    END DO

    ! Calculate the slope, Y-Intercept and print out the information.
    XMean = SumX / count
    YMean = SumY / count
    Slope = (SumXY - SumX * YMean) / (SumX2 - SumX * XMean)
    YInt = YMean - Slope * XMean

    PRINT *
    PRINT *,"The least squares regression for Y = M * X + B"
    PRINT 100,"  -> The Slope (M) is:", slope
    PRINT 100,"  -> The Y-Int (B) is:", YInt
    100 FORMAT(1X, A, F10.4)

    ! Have a few lines to seperate each iteration.
    PRINT '(1X, 3/)'

    READ *,X,Y  ! Read in the next set of values

  END DO

END PROGRAM PROG2