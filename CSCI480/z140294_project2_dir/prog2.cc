//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//
// Nicholas Nagrodski
// ZID: Z140294 !
// CSCI 480 Fall 2010
// Assignment : 2
// Due Date: 10/21/2010
//
// Description:  This program's purpose is write a command interpereter, 
//              a shell, which can also accept piped commands
//              e.g., "ls || sort".  
//
//
// *NOTE*:  The "parseCommand()" function is overkill for what the program 'requires'.
//         I was attempting to create a 'modular' parsing function that would accept
//         an infinite number piped commands.  I however ran out of time.
//
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

#include <iostream>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
using namespace std;

// Constants.
const int C_SIZE = 10; // The maximum "piped-command" and "length of command arguments" size.
const int BUF_SIZE = 1024;
const char* CMD_LINE_PROMPT = "++->";

// Function Prototypes.
void parseCommand(char[BUF_SIZE], char* [C_SIZE][C_SIZE]);


int main(void)
{
  char buf[BUF_SIZE];
  char* CmdArray[C_SIZE][C_SIZE] = { { NULL } };
  pid_t pid, pid2;
  int status; 

  // Main Shell Loop. 
  while( true )
  {
    // Display Command Prompt.
    printf(CMD_LINE_PROMPT);

    // Read What the user types in, into buf.
    if ( fgets(buf,1024,stdin) == NULL )
      break; 

    buf[strlen(buf) -1] = 0;    // get rid of \n 

    // Check to see if the user entered nothing!
    if ( *buf == 0 )
      continue;

    // Parse the incoming line.
    // This function splits the command and arguments into array entries compatible with "execvp()". 
    parseCommand( buf, CmdArray);    

    // Check to see if we want to exit the shell;
    if ( strcmp(CmdArray[0][0], "q")==0  || strcmp(CmdArray[0][0], "quit")==0 )
      break; 


    // NO PIPED COMMAND.
    if ( CmdArray[1][0] == NULL ) 
    {

      // Fork and check for an error.
      if ( (pid = fork()) < 0 ) { fprintf(stderr, "Fork Failed"); exit(1); }

      else if ( pid == 0 )  // Child for a single shell command.
      {                    
        // Used for calling one command ONLY.
        // Execute the command and check for and error.
        // We return to the begining of the main shell loop after successful execution.
        if ( execvp( CmdArray[0][0],  CmdArray[0] ) < 0) 
        { fprintf(stderr,"Error Executing Command: %s ...\n", *CmdArray[0]); exit(127); } 
      }
    }
    else // PIPED COMMAND
    {
     
      // Fork and check for an error.
      if( (pid = fork()) < 0 ) { fprintf(stderr, "Fork Failed"); exit(1); }

      else if( pid == 0) // Child1: Reader.  Waits for Child2 to finish writing to pipe so it can read.
      {
        int fd[2];
        
        // Create the pipe and check for errors.
        if ( pipe(fd) < 0 ) {  fprintf(stderr,"Error Creating Pipe"); exit(1);  }
     
        // Fork to create the second child and check for an error.
        if( (pid2 = fork()) < 0 ) { fprintf(stderr,"Fork Failed!"); exit(1); }

        else if ( pid2 == 0 )        // Child2: Writer. 
        {
          // Close stdout.
          if ( close(1) != 0 )  fprintf(stderr, "Closing 1 (stdout) is not successful\n");

          // Create the write end of the pipe.    
          if ( dup(fd[1]) < 0 ) fprintf(stderr, "dup1 error\n");
      
          close(fd[0]);  // Close unused end of pipe (The 'read end' / 'stdin end').
         
          // Execute the command and check for and error.
          if ( execvp( CmdArray[0][0],  CmdArray[0] ) < 0)
          { fprintf(stderr,"Error Executing Commannd: %s ...", *CmdArray[0]); exit(127); }
        }
        
        //  Child 1: Reader.   Wait for child1 to write to the pipe.
        // Then have child 1 read the output side of the pipe and ouput to stdout.

        // Close standard input and check for an error.
        if ( close(0) != 0 ) fprintf(stderr, "Closing 0 (stdin) is not succesful\n");
      
        // Create the read end of the pipe and check for an error.
        if( dup(fd[0]) < 0 ) fprintf(stderr, "dup0 error \n");

        // Close unused write side of pipe.  
        close(fd[1]);     // Close unused write side of pipe.  

        // Execute the command and check for and error.
        if ( execvp( CmdArray[1][0],  CmdArray[1] ) < 0) 
        { fprintf(stderr, "Error Executing Command %s ...", *CmdArray[1]); exit(127); }
      }
    } // End of Piped command control structure.
    
    // Parent Process.
    if ( (pid=waitpid(pid,&status,0)) < 0 )   // Wait for the child(ren) to run the proces and check for an error.
      fprintf(stderr,"Wait_pid Error.\n");
  } 
  return 0;
}


//------------------------------------------------------------------------------
// Function : parseCommand()
// Input    : inputBuffer -  A buffer containing the entire input line.
//          : cmdArray    -  A two dimensional array used to pass back the parsed
//                          commands.  The first index is used to seperate 
//                          piped commands, and the second index is used to create
//                          an array of the command and its options.
// 
// Output   : The CmdArray is updated with the new line of commands and arguments.
// 
// *NOTE*:  This function is overkill for what the program 'requires'.  I was attempting 
//         to create a 'modular' parsing function that would accept an infinite number
//         piped commands.  I however ran out of time to impliment it.
//
//------------------------------------------------------------------------------
void parseCommand(char inputBuffer[BUF_SIZE], char* CmdArray[C_SIZE][C_SIZE])
{
    // Clear the array by setting every entry to NULL.
    // This is needed to create a fresh array, clearing any previous commands.
    for(int i=0; i < C_SIZE; i++)
      for(int j=0; j < C_SIZE; j++)
        CmdArray[j][i] = NULL;


    char *tokened_Cmd;
    int CmdNumber = 0, ArgNumber = 0;

    tokened_Cmd = strtok(inputBuffer," ");
    while ( tokened_Cmd != NULL )
    {
      // Check to see if the command is piped.
      if ( strcmp(tokened_Cmd,"||") == 0 )
      {
        CmdArray[CmdNumber][ArgNumber] = NULL; // Null terminate the argument array.
        CmdNumber++;
        ArgNumber = 0; // Reset the argument index.
        tokened_Cmd = strtok(NULL," ");  // Read in a new command to replace the "||"
      }

      // Add the argument to the list.
      CmdArray[CmdNumber][ArgNumber] = tokened_Cmd;
      ArgNumber++; 

      tokened_Cmd = strtok(NULL," ");  // Read in the next command!
    }
    
    // Add the NULL terminator to the last command argument.
    CmdArray[CmdNumber][ArgNumber] = NULL;
}
