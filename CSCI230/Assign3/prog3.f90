PROGRAM  PROG3
!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   3                          Due Date:  09/24/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program caclulates the "SIGMA" of a number where sigma is
!                the sum of all divisors of N other than N itself.  Some 
!                examples are shown below.
!
!         SIGMA(2)  = 1
!         SIGMA(4)  = 1 + 2 = 3
!         SIGMA(10) = 1 + 2 + 5 = 8
!         SIGMA(24) = 1 + 2 + 3 + 4 + 6 + 8 + 12 = 36
!
!                 This program  reads in an input of two numbers and calculates
!                all the sigmas between the two numbers.  It then classifies  
!                them as perfect, deficient, abundant, and/or near-perfect based    
!                on the following system;      
!
!              -> Deficient if SIGMA(N) < N,
!              -> Perfect if SIGMA(N) = N,
!              -> Abundant if SIGMA(N) > N, and
!              -> Near-Perfect if SIGMA(N) = either N + 1 or N - 1.   
!
!  Input:         Two numbers 'M' and 'N'.  The program calulates the sigmas for
!                each number M through N and also keeps a running total of the 
!                perfect, deficient, abundant, and/or near-perfect numbers for 
!                each set of input.
!
!  Output:        The sigma values for each number M through N entered.  It also
!                outputs the totals of the perfect, deficient, abundant, and
!                near-perfect numbers.          
!                                                                   
!-------------------------------------------------------------------------------

  IMPLICIT NONE

  ! VARIABLE EXPLANATIONS 
  !   M and N are the variables used for input data.
  !   K and I are counter variables for the loops.
  !   sigma, numDeficient, numAbundant, numPerfect, and numNearPerfect are all used
  !    in calculating statistics and are self-explanitory.
  INTEGER :: M, N, K, I, sigma, numDeficient, numAbundant, numPerfect, numNearPerfect

  ! Format line for the output of the final statistics.
  !   " Number of Deficient numbers    :XXXX"
  100 FORMAT(1X, A, I4)

  ! Format line for each sigma calculation and classification.
  !   " NUMBER =XXXXXX   SIGMA =XXXXXX   Classification = ..."  
  110 FORMAT(1X, 2(A, I6), A, A)


  ! Prime the loop with the first set of data points.
  READ *, M, N

  ! This loop reads the input file until the end of file marker "0  1".
  DO WHILE ( M /= 0 .OR. N /= 1 )

    PRINT '(2/)'   ! Skip a few lines inbetween each set of input data.

    ! Reset statistics numbers for each set of data.
    numDeficient = 0
    numAbundant = 0
    numPerfect = 0
    numNearPerfect = 0

    !  This inner loop calculates the sigma for each number from 'N' throguh 'M'
    ! and classifies it as abundant, deficient, perfect, or near-perfect.
    DO K = M, N
       
      ! Calculate sigma
      sigma = 0
      DO I = 1,K/2  ! We can stop at k/2 (See the program description for examples).
        ! This is a check to see if 'I' is an even divisor of our input.
        if( MOD(K,I) == 0 ) sigma = sigma + I    
      END DO

      ! Find the classification of the number.
      IF ( sigma == K) THEN
        PRINT 110,"NUMBER =", K, "   SIGMA =", sigma, "   Classification = Perfect"
        numPerfect = numPerfect + 1
      ELSE IF ( sigma-1 == K ) THEN
        PRINT 110,"NUMBER =", K, "   SIGMA =", sigma, "   Classification = Near-Perfect and Abundant"  
        numNearPerfect = numNearPerfect + 1    
        numAbundant = numAbundant + 1  
      ELSE IF ( sigma+1 == K ) THEN
        PRINT 110,"NUMBER =", K, "   SIGMA =", sigma, "   Classification = Near-Perfect and Deficient"
        numNearPerfect = numNearPerfect + 1 
        numDeficient = numDeficient + 1
      ELSE IF ( sigma > K ) THEN
        PRINT 110,"NUMBER =", K, "   SIGMA =", sigma, "   Classification = Abundant"
        numAbundant = numAbundant + 1 
      ELSE 
        PRINT 110,"NUMBER =", K, "   SIGMA =", sigma, "   Classification = Deficient"  
        numDeficient = numDeficient + 1    
      END IF

    END DO

    ! Print out statistics of the set of number read.
    PRINT *
    PRINT 100,"Number of Deficient numbers    :",numDeficient
    PRINT 100,"Number of Abundant numbers     :",numAbundant
    PRINT 100,"Number of Perfect numbers      :",numPerfect
    PRINT 100,"Number of Near-Perfect numbers :",numNearPerfect

    READ *, M, N    ! Read in the next set of values

  END DO

END PROGRAM PROG3