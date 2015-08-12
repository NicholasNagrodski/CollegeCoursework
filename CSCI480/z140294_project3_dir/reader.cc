//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//
// Nicholas Nagrodski
// ZID: Z140294 
// CSCI 480 Fall 2010
// Assignment : 3
// Due Date: 12/02/2010
//
// Description:  This program's purpose is to start a reader that initializes
//              shared memory and reads from three writers that write to it.
//
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

#include "MemoryControl.h"

// Reader Function Prototypes.
void initSemaphores(union semun &, const int);
void print_CS_Info(int, union semun, int, pid_t);
void printInitialInfo(int, int, int, int, int);

int main()
{
  int sharedMemory_key, semaphore_key, sharedMemory_ID, semaphore_ID;
  int *shmAddress;              // Address we will store our shared memory value at.
  int Value = 0;                // The value of the shared memory.
  pid_t reader_PID = getpid();  // PID of the reader.

  union semun arg;
  struct sembuf mysembuf;

  // Get the 'keys' for the shared memory and shared semaphore.
  getSemaphoreKey(semaphore_key, SEMKEYPATH, SEMKEYID);
  getSharedMemoryKey(sharedMemory_key, SHMKEYPATH, SHMKEYID);

  // Get shared memory and check for an error.
  getSemaphoreID(semaphore_ID, semaphore_key, NUMBER_OF_SEMS);
  getSharedMemoryID(sharedMemory_ID, sharedMemory_key, MAX_MEM_SIZE);

  // Initialize the semaphore values. ( set sem[0]=1, and sem[1]=0 )
  initSemaphores(arg, semaphore_ID);

  // Print out the starting inforamtion.
  printInitialInfo(sharedMemory_key, semaphore_key, sharedMemory_ID, semaphore_ID, Value);

  // Attach the shared memory to this process.
  getShmAddress(shmAddress, sharedMemory_ID);
 
  // Initalize the shared memory location to the inital value of zero.
  *shmAddress = Value;

  // Main work loop.
  while (1)
  {
    // Wait for writer to finish writing.
    mysembuf.sem_num = 1;   // Semaphore to operate on.
    mysembuf.sem_op = -1;   // Decrement the value by one.
    mysembuf.sem_flg = 0;   // Wait for the writer.

    // Call semop() and check for errors.
    if ( semop(semaphore_ID, &mysembuf, 1) < 0 )
    {  
      printf("Reader_main(): semop(): Error: Unable to wait for writer to finish writing.\n");
      exit(0);
    }

//--- START CS
	
    // Get our value from the shared memory.
    Value = *shmAddress;
       
    // Print out the Readers current state.
    print_CS_Info(semaphore_ID, arg, Value, reader_PID);
   
    // Check to see if we are done.
    if ( Value >= NUM_OF_MESSAGES )
      break;

    // Signal to writer(s) that we are finished reading.
    mysembuf.sem_num = 0;            // Semaphore to operate on.
    mysembuf.sem_op = 1;             // Increment the value by one.
    mysembuf.sem_flg = IPC_NOWAIT;   // Signal the writer immediately.

    // Call semop() and check for errors.
    if ( semop(semaphore_ID, &mysembuf, 1) )
    {
      printf("Reader_main(): semop(): Error: Unable to signal writers we are done reading.\n");
      exit(0);
    }

    // Check to see if we are done.
    if ( Value >= NUM_OF_MESSAGES )
      break;

//--- END CS

  } 

  printf("Reader_main(): Done\n\n");

  // Detach the shared memory from this process.
  if ( shmdt(shmAddress) < 0 )
  {
    printf("Reader_main(): shmdt(): Error.");
    exit(0);
  }

  // Release the semaphores and shared memory.
  releaseSharedResources(sharedMemory_ID, semaphore_ID);

  return 0;
}


// Description : Initializes the semaphores to the values described below.
void initSemaphores(union semun &arg, const int semaphore_ID)
{
  /* Initialize the semaphore values. ( set sem[0]=1, and sem[1]=0 )     */
  /*  The first semaphore, s[0], represents the reader state:            */
  /*   '0' - The reader is in the process of reading.                    */
  /*   '1' - The reader is done reading.                                 */
  /*  The second semaphore, s[1], represents the writer state:           */
  /*   '0' - The writer is in the process of writing.                    */
  /*   '1' - The writer is done writing.                                 */

  arg.val = 1;
  int rc;
  if ( (rc = semctl( semaphore_ID, 0, SETVAL, arg )) < 0 )
  {  
    printf("Reader_main(): semctl(): Error initializing semaphore 0 value.\n");
    printf("Return Value: %d", rc);
    exit(0);
  }

  arg.val = 0;
  if ( semctl( semaphore_ID, 1, SETVAL, arg ) < 0 )
  { 
    printf("Reader_main(): semctl(): Error initializing semaphore 1 value.\n"); 
    exit(0);
   }

  return;
}


// Description : Prints the wiriter critical section information.
void print_CS_Info(int semaphore_ID, union semun arg, int val, pid_t reader_PID)
{
  int sem0, sem1;

  sem0 = semctl(semaphore_ID, 0, GETVAL, arg);
  sem1 = semctl(semaphore_ID, 1, GETVAL, arg);

  printf("Reader PID: %d\n", reader_PID);
  printf("Value Read From Shared Memory: %d\n", val);
  printf("Semaphore values: %d, %d\n\n", sem0, sem1);

  return;
}


// Description : Prints the readers inital information.
void printInitialInfo(int sharedMemory_key, int semaphore_key, int sharedMemory_ID, int semaphore_ID, int value)
{
  // Print out the starting inforamtion.
  printf("\nReader Stariting Information:\n");
  printf("Reader Shared Memory Key : %d\n", sharedMemory_key);
  printf("Reader Semaphore Key     : %d\n", semaphore_key);
  printf("Reader Shared Memory ID  : %d\n", sharedMemory_ID);
  printf("Reader Semaphore ID      : %d\n", semaphore_ID);
  printf("Reader Initial shared Memory Value: %d\n\n", value);

  return;
}

