//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//
// Nicholas Nagrodski
// ZID: Z140294 
// CSCI 480 Fall 2010
// Assignment : 3
// Due Date: 12/02/2010
//
// Description:  This program's purpose is to start three "writers" to write
//              to shared memory and to impliment a solution to the CS problem.
//
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

#include "MemoryControl.h"

// Reader Function Prototypes.
void printWriterInitialInfo(int, int, int, int);
void print_CS_Info(int, union semun, int, pid_t);

int main()
{
  int sharedMemory_key, semaphore_key, sharedMemory_ID, semaphore_ID;
  int *shmAddress;              // Address we will store our shared memory value at.
  int Value;                    // The value of the shared memory.
  
  union semun arg;
  struct sembuf mysembuf;

  // Get the 'keys' for the shared memory and shared semaphore.
  getSemaphoreKey( semaphore_key, SEMKEYPATH, SEMKEYID );
  getSharedMemoryKey( sharedMemory_key, SHMKEYPATH, SHMKEYID );

  // Get shared memory and check for an error.
  getSemaphoreID( semaphore_ID, semaphore_key, NUMBER_OF_SEMS );
  getSharedMemoryID( sharedMemory_ID, sharedMemory_key, MAX_MEM_SIZE );

  // Print out the starting inforamtion.
  printWriterInitialInfo( sharedMemory_key, semaphore_key, sharedMemory_ID, semaphore_ID);

  // Create the other two processes before the shmat() so they can share the memeory addr.
  pid_t pid = fork();
  if ( pid < 0 )
  {
    printf("Writer_main(): fork(): Error creating writers 2.\n");
    exit(0);
  }
  else if ( pid != 0 ) // Parent again.
  {
    pid = fork(); 
    if ( pid < 0 )
    {
      printf("Writer_main(): fork(): Error creating writer 3.\n");
      exit(0);      
    }
  } 

  // Attach the shared memory to this process.
  getShmAddress(shmAddress, sharedMemory_ID);

  // Main work loop.
  while (1)
  {
    // Wait for reader to finish reading.
    mysembuf.sem_num = 0;   // Semaphore to operate on.
    mysembuf.sem_op = -1;   // Decrement the value by one.
    mysembuf.sem_flg = 0;   // Wait for the reader.

    // Call semop() and check for errors.
    if ( semop(semaphore_ID, &mysembuf, 1) < 0 )
    {
      if ( (Value = *shmAddress) >= NUM_OF_MESSAGES ) break;
      printf("Writer_main(): semop(): Error: Unable to wait for reader to finish reading.\n");
      exit(0);
    }

//--- START CS

    // Increment the integer shared memory variable.   
    // Check to see if we are done.
    if ( (Value = *shmAddress) >= NUM_OF_MESSAGES )
      break;
    else
      Value = ++*shmAddress;

    // Print out the Writers current state.
    print_CS_Info(semaphore_ID, arg, Value, getpid());

    // Signal to reader that we are finished writing.
    mysembuf.sem_num = 1;            // Semaphore to operate on.
    mysembuf.sem_op = 1;             // Decrement the value by one.
    mysembuf.sem_flg = IPC_NOWAIT;   // Signal Now.

    // Call semop() and check for errors.
    if ( semop(semaphore_ID, &mysembuf, 1) < 0 )
    {
      printf("Writer_main(): semop(): Error: Unable to signal to reader that we are finished writing.\n");
      exit(0);
    }

//--- END CS

    // Don't sleep if this is the last iteration.
    if ( Value < 10 )
      sleep(1);

  }

  printf("Writer_main(): Done\n");

  // Clean up section.
  printf("\n");	

  // Detach the shared memory from this process.
  if ( shmdt(shmAddress) < 0 )
  {
    printf("Writer_main(): shmdt(): Error.");
    exit(0);
  }

  return 0;
}

// Description : Prints the Initial configuration information.
void printWriterInitialInfo( int sharedMemory_key, int semaphore_key, int sharedMemory_ID, int semaphore_ID )
{
  // Print out the starting inforamtion.
  printf("Writer Stariting Information:\n");
  printf("Writer Shared Memory Key : %d\n", sharedMemory_key);
  printf("Writer Semaphore Key     : %d\n", semaphore_key);
  printf("Writer Shared Memory ID  : %d\n", sharedMemory_ID);
  printf("Writer Semaphore ID      : %d\n\n", semaphore_ID);

  return;
}

// Description : Prints the wiriter critical section information.
void print_CS_Info(int semaphore_ID, union semun arg,int Value, pid_t writer_pid)
{
  int sem0, sem1;

  sem0 = semctl(semaphore_ID, 0, GETVAL, arg);
  sem1 = semctl(semaphore_ID, 1, GETVAL, arg);

  printf("Writer PID: %d\n", writer_pid);
  printf("The new Value written: %d\n",Value);
  printf("Semaphore values: %d, %d\n\n", sem0, sem1);

  return;
}

