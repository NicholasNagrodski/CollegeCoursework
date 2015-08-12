PROGRAM PROG9
!-------------------------------------------------------------------------------
!                                                                   
!  Assignment:   9                         Due Date:  12/01/2010      
!                                                                   
!  Programmer:   Nicholas Nagrodski                                     
!                                                                   
!  Description:   This program impliments a Caesar Cipher as described below:
!
!    Each letter is replaced by another letter following it at a constant distance 
!   K in the alphabet. Decoding the message would use the constant distance 26 - K.
!   If there are nonalphabetic characters in the message, such as periods, commas 
!   or spaces, they will be left alone. The value of K will always be between 0 and 26.
!
!   For example, if K = 3, A is encoded as D, B as E, W as Z, X as A, and so on. 
!   If the original message is "HOW EXCITING!", the encoded version will be "KRZ HAFLWLQJ!". 
!
! Input:   I2 - a 2-digit integer number.
!          1X - a blank space.
!         60A - a character string of length 60.
!
! IE:   II CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
! EG:   01 SNEEZY, SLEEPY, DOPEY, DOC, HAPPY, BASHFUL AND GRUMPY       
!
!                                                                   
!-------------------------------------------------------------------------------

  IMPLICIT NONE

  ! Program variables.
  INTEGER, PARAMETER :: MSG_SIZE = 60
  CHARACTER :: originalMessage(MSG_SIZE), encodedMessage(MSG_SIZE), decodedMessage(MSG_SIZE)
  INTEGER :: K, msgCount = 0, LetterFrequencies(65:90)=0, I

  ! Define file information.
  INTEGER, PARAMETER :: infileNumber1 = 1, infileNumber2 = 2, outfileNumber1 = 3
  INTEGER :: inputStatus, openStatus
  CHARACTER(20), PARAMETER :: infileName1 = "data9p.txt", infileName2 = "data9c.txt", outfileName1 = "prog9.out"

  ! Input format.
  100 FORMAT( I2, 1X, 60A )

  ! Table heading format.
  110 FORMAT(18X, A, 5X, A)


!!! MAIN  EXECUTABLE  CODE  !!!


  ! Open the FIRST input file and check for errors.
  OPEN( UNIT = infileNumber1, FILE = infileName1, STATUS = "OLD", ACTION = "READ", IOSTAT = openStatus )
  IF ( OpenStatus > 0 ) STOP "*** Error Opening File 1 ***"

  ! Open the SECOND input file and check for errors.
  OPEN( UNIT = infileNumber2, FILE = infileName2, STATUS = "OLD", ACTION = "READ", IOSTAT = openStatus )
  IF ( OpenStatus > 0 ) STOP "*** Error Opening File 2 ***"

  ! Open the FIRST output file and check for errors.
  OPEN( UNIT = outfileNumber1, FILE = outfileName1, STATUS = "REPLACE", ACTION = "WRITE", IOSTAT = openStatus )
  IF ( OpenStatus > 0 ) STOP "*** Error Opening Output File 1 ***"


  ! Process the first file.
  DO
    ! Read file and check for EOF or input errors.
    READ ( UNIT = infileNumber1, FMT = 100, IOSTAT = inputStatus), K, originalMessage

    IF ( inputStatus < 0 ) EXIT
    IF ( inputStatus > 0 ) STOP "*** Input File 1 Error ***"

    ! Count the number of occurances of each PLAIN-ENGLISH letter.
    DO I = 1, MSG_SIZE
      IF ( IACHAR(originalMessage(I)) >= 65 .AND. IACHAR(originalMessage(I)) <= 90 ) THEN
        LetterFrequencies( IACHAR(originalMessage(I)) ) = LetterFrequencies( IACHAR(originalMessage(I)) ) + 1
      ENDIF
    END DO 

    ! Print a new heading if needed and increment the message count.
    IF ( MOD(msgCount,16) == 0 ) THEN
      CALL printHeading()
      WRITE ( outfileNumber1, 110) "Key","Message" 
      WRITE ( outfileNumber1, 110) "---","------------------------------------------------------------"
      WRITE ( outfileNumber1, '(/)') 
    ENDIF

    msgCount = msgCount + 1 

    ! Encode the message and print it 
    CALL encode( originalMessage, encodedMessage, K )
    CALL printEntry( originalMessage, encodedMessage, K )
 
  END DO
 

  ! Process the second file.
  msgCount = 0

  DO
    ! Read file and check for EOF or input errors.
    READ ( UNIT = infileNumber2, FMT = 100, IOSTAT = inputStatus), K, encodedMessage

    IF ( inputStatus < 0 ) EXIT
    IF ( inputStatus > 0 ) STOP "*** Input File 2 Error ***"

    IF ( MOD(msgCount,16) == 0 ) THEN
      CALL printHeading()
      WRITE ( outfileNumber1, 110) "Key","Message" 
      WRITE ( outfileNumber1, 110) "---","------------------------------------------------------------" 
      WRITE ( outfileNumber1, '(/)')
    ENDIF

    msgCount = msgCount + 1 

    ! Decode the message and print it!  (This function also works to decode a message.)
    CALL encode( encodedMessage, decodedMessage, K )

    ! Count the number of occurances of each PLAIN-ENGLISH letter. 
    DO I = 1, MSG_SIZE
      IF ( IACHAR(encodedMessage(I)) >= 65 .AND. IACHAR(encodedMessage(I)) <= 90 ) THEN
        LetterFrequencies( IACHAR(decodedMessage(I)) ) = LetterFrequencies( IACHAR(decodedMessage(I)) ) + 1
      ENDIF
    END DO 

    CALL printEntry( encodedMessage, decodedMessage, K )
 
  END DO

  ! The extracredit part.
  CALL printHeading()
  CALL printLetterFreq(LetterFrequencies)


  ! Close the files
  CLOSE( infileNumber1 ) 
  CLOSE( infileNumber2 ) 
  CLOSE( outfileNumber1 ) 


!!!  END  OF  MAIN  EXECUTABLE  CODE  !!! 


CONTAINS


  !-----------------------------------------------------------------------------
  ! SUBROUTINE encode(originalMessage, encodedMessage, K)
  !  
  !  Description:  Encodes a message as a Caesar cipher with a key K.  This 
  !               subroutine only works on capital letters.  ALSO, this routine
  !               works for decoding the encoded messages. Use the syntax:
  !
  !                    encode(encodedMessage, decodedMessage)
  ! 
  !               'decodedMessage' will contain the deciphered message when done.
  !
  !  Input: originalMessage  - The un-encrypted/original message.
  !         encodedMessage    - The encrypted version of the message.
  !         K - - - - - - - - - The key.
  !
  !  output: None
  !-----------------------------------------------------------------------------
  SUBROUTINE encode(originalMessage, encodedMessage, K)
    IMPLICIT NONE
    CHARACTER, INTENT(INOUT) :: originalMessage(*)
    CHARACTER, INTENT(OUT) :: encodedMessage(*)
    INTEGER, INTENT(IN) :: K 

    INTEGER :: I, I_ENC, SIZE = 60
    
    DO I = 1, SIZE, 1
      ! Check to see if there is a valid UPEERCASE ASCII character that needs to be ciphered.
      IF ( IACHAR(originalMessage(I)) >= 65 .AND. IACHAR(originalMessage(I)) <= 90 ) THEN
        I_ENC = IACHAR(originalMessage(I)) + K   ! Find the encoded letter's ASCII value.
     
        IF ( I_ENC > 90 ) I_ENC = I_ENC-26   ! If the value needs to be 'wrapped around'.
 
        ! Then put the encoded character in the encoded string.
        encodedMessage(I) = ACHAR(I_ENC)
      ELSE
        encodedMessage(I) = originalMessage(I)   ! Copy the character straight from the original.    
      ENDIF 
    END DO

  END SUBROUTINE encode


  !-----------------------------------------------------------------------------
  ! SUBROUTINE printEntry(originalMessage, encodedMessage, K)
  !  
  !  Description:   Prints out an entry of an encoded/decoded message.
  !
  !  Input: originalMessage  - The un-encrypted/original message.
  !         codedMessage    - The encrypted/decrypted version of the message.
  !         K - - - - - - - - - The key.  
  !
  !  output:   The nicely formatted original and encoded messages along with the key.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE printEntry(originalMessage, encodedMessage, K)
    IMPLICIT NONE
    CHARACTER, INTENT(IN) :: originalMessage(:), encodedMessage(:)
    INTEGER, INTENT(IN) :: K 

    210 FORMAT(01X, A16, 1X, I3, 5X, 60A)
    220 FORMAT(01X, A16, 9X, 60A, /)

    WRITE ( outfileNumber1, 210),"(original)", K, originalMessage
    WRITE ( outfileNumber1, 220),"(ciphered)", encodedMessage 

  END SUBROUTINE printEntry

  !-----------------------------------------------------------------------------
  ! SUBROUTINE printHeading()
  !  
  !  Description:   Prints out the heading with the date and page number.  Also keeps
  !                track of the page number and date.
  !
  !  Input:  None
  !
  !  output:    The heading information, date, and page number.
  !-----------------------------------------------------------------------------
  SUBROUTINE printHeading()
    IMPLICIT NONE
    CHARACTER(10) :: DATE

    ! Subroutine Variables
    INTEGER, SAVE :: pageNumber = 1 

    !Get the date.
    CALL DATE_AND_TIME(DATE)     ! DATE = YYYYMMDD
    DATE = DATE(5:6) // "/" // DATE(7:8) // "/" // DATE(1:4)    ! DateDisplay = "MM/DD/YYYY"

    ! Program Information Header.
    WRITE ( outfileNumber1, '(4/, 2X, A10, T34, A, T80, A, I3, 2/)' ), &
            DATE,"Caesar Cipher - By: Nicholas Nagrodski", "Page: ", pageNumber

    pageNumber = pageNumber + 1 

  END SUBROUTINE printHeading


  !-----------------------------------------------------------------------------
  ! SUBROUTINE printLetterFreq(LetterFrequencies)
  !  
  !  Description:   Prints out the number of times each letter in the alphabet is
  !                encountered.  Also a graph representing the relative frequencies
  !                of each letter is printed.
  !
  !  Input:   An integer array of the values of each time a letter occured. 
  !
  !  output:  Prints the count of the nubmer of english letters along with a 
  !          bar graph representing the relative frequencies of each leter.
  !
  !-----------------------------------------------------------------------------
  SUBROUTINE printLetterFreq(LetterFrequencies)
    IMPLICIT NONE
    INTEGER, INTENT(IN) :: LetterFrequencies(65:90)

    INTEGER :: I, J, maxValue, LetterFrequenciesGraph(65:90) ! This array is used to hold the bar graph inforamtion.
    INTEGER, PARAMETER :: MAX_BAR_SIZE = 80                  ! Each entry has the nubmer of *'s to print for each letter.

    410 FORMAT(06X, A, 5X, A, 5X, A)  ! Header format.
    420 FORMAT(09X, A1, 7X, I5, 5X, 100A )

    maxValue = MAXVAL(LetterFrequencies) ! Get the max value for graphing purposes.
   
    ! Print the header information.
    WRITE ( outfileNumber1, 410), "Letter", "Count", "Relative Frequency"
    WRITE ( outfileNumber1, 410), "------", "-----", &
      "--------------------------------------------------------------------------------"

    DO I = 65, 90, 1 

      ! Scale the entrys of the number count for the graph.
      ! ( To obtain a better accuracy when scaling the graph, we use real-value calculations and type-cast the result. 
      LetterFrequenciesGraph(I) = NINT(LetterFrequencies(I) * REAL(MAX_BAR_SIZE) / maxValue)   

      ! Print out all the information. 
      WRITE ( outfileNUmber1, 420), ACHAR(I), LetterFrequencies(I), ('*', J = 1, LetterFrequenciesGraph(I) )
   
    END DO

  END SUBROUTINE printLetterFreq

END PROGRAM PROG9
