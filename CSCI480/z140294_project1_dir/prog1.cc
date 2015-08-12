//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//
// Nicholas Nagrodski
// CSIC 480 Fall 2010
// Assignment : 1
// Due Date: 9/24/2010
//
// Description:  This program's purpose is to familiarize ourselves with the
//              concept of processes.  The program "forks" and creats a 
//              child process.  There is output printed to the screen 
//              showing the process ID's (PID's) of the child and parent.
//               Also, both the child and parent process make a system call
//              which required another fork in each in of the two processes
//              created.   
//
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

#include <iostream>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
using namespace std;

int main()
{
  int child_pid, parent_pid, grandParent_pid, pid;  // PID variables
  setbuf(stdout, NULL);        // Set the standard output to unbuffered.


  if ( (pid = fork()) < 0 )	// Fork and check for an error.
  {
    // Fork Failed
    cerr << "\nFork Failed" << endl;
    exit(-1); 
  }
  else if ( pid > 0 )		// Parent Process.
  {
    // Get the PID's of the parent and the parent's parent (grandparent) processes to display.
    parent_pid = getpid();
    grandParent_pid = getppid();
    
    cout << "\nParent: My PID is " << parent_pid << " My parents PID is: "
         << grandParent_pid << " and the return value of the fork is: " << pid << endl;


    // Get set up to call the 'ps -fp command.'
    char UnixCmdOptions[20];
    sprintf( UnixCmdOptions,"%d,%d", pid, parent_pid );
 
    cout << "\nParent issuing command: ps -fp " << UnixCmdOptions << endl;


    // Call "ps" from a different thread. 
    int ps_pid;
    if ( (ps_pid = fork()) < 0 )	  // Fork and check for an error.
    {
      // Fork Failed
      cerr << "\nFork Failed" << endl;
      exit(-1);
    }
    else if ( ps_pid == 0 )               // ( pid == 0 and  Child Process.
    {
      execlp( "/bin/ps", "ps", "-fp", UnixCmdOptions, NULL );
      exit(0);                            // Kill child used to run the exec().
    }
    
    // Wait for the child to run the proces.
    wait( NULL );


    // Wait for the child process to sleep for three seconds and terminate.
    wait( NULL );  
    cout << "\nParent: Child finished" << endl;
  }  
  else    // ( pid == 0 ) and is Child Process.
  {
    // Get the PID's of the child and parent processes to display.
    child_pid = getpid();
    parent_pid = getppid();

    cout << "\nChild:  My PID is " << child_pid << " My parents PID is: "
         << parent_pid << " and the return value of the fork is: " << pid << endl;
 

    // Get set up to call the 'ps -fp command.'
    char UnixCmdOptions[20];
    sprintf(UnixCmdOptions, "%d,%d", child_pid, parent_pid);

    cout << "\nChild issuing command: ps -fp " << UnixCmdOptions << endl;


    // Call "ps" from a different thread.
    int ps_pid;
    if ( (ps_pid = fork()) < 0 )                 // Fork and check for an error.
    {
      // Fork Failed
      cerr << "\nFork Failed" << endl;
      exit(-1);
    }
    else if ( ps_pid == 0 )                         // ( pid == 0 and  Child Process.
    {
      execlp( "/bin/ps", "ps", "-fp", UnixCmdOptions, NULL );
      exit(0);           // Kill child used to run the exec().
    }

    // Wait for the child to run the proces.
    wait( NULL );


    // Let the child process take a nap.
    cout << "\nThe child will take a three second nap..." << endl;
    sleep(3);
    cout << "\nChild: Child is awake." << endl;

    exit(0);   // Terminate the child.
  }   
 
  return 0;
}
