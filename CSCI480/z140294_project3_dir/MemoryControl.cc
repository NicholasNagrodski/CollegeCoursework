//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//
// Programmer : Nicholas Nagrodski
// ZID: Z140294 
// CSCI 480 Fall 2010
// Assignment : 3
// Due Date: 12/02/2010
//
// Description:  This file contains routines to get, control, and release 
//              the shared memory and semaphores.  These functions are common
//              to both the writer.cc and reader.cc.
//              
// System Function Calls : ftok, shmget, semget, shmctl, shmat
//
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

#include "MemoryControl.h"

// Description : This function returns a semaphore 'keyValue' using the function call ftok()
// Input : The key file path and a constant unique key identifier. 
// Output : Returns the keyValue
// System Call : ftok()
void getSemaphoreKey( int &keyValue, char const * const SEMKEYPATH, const int SEMKEYID)
{
  keyValue = ftok( SEMKEYPATH, SEMKEYID );
  if ( keyValue == (key_t)-1 )
  {
    printf("main: ftok() for semaphore allocation failed\n");
    exit(0);
  }

  return;
}

// Description : This function returns a shared memory 'keyValue' using the function call ftok()
// Input : The key file path and a constant unique key identifier.
// Output : Returns the keyValue
// System Call : ftok()
void getSharedMemoryKey( int &keyValue, char const * const SEMKEYPATH, const int SEMKEYID)
{
  keyValue = ftok( SHMKEYPATH, SHMKEYID );
  if ( keyValue == (key_t)-1 )
  {
    printf("main: ftok() for shared memory failed\n");
    exit(0);
  }

  return;
}

// Description : This function returns a 'semaphore_ID' using the function call semget()
// Input : The 'sharedMemory_key' and the 'NUMBER_OF_SEMS'.
// Output : Returns the semaphore_ID by reference.
// System Call : semget()
void getSemaphoreID( int &semaphore_ID, const int semaphore_key, const int NUMBER_OF_SEMS)
{
  // Get the semaphore ID and check for an error.
  semaphore_ID = semget(semaphore_key, NUMBER_OF_SEMS, 0400 | 0200 | IPC_CREAT);
  if ( semaphore_ID == -1 )
  {
    printf("main: semget() for semaphore allocation failed\n");
    exit(0);
  }

  return;
}

// Description : This function returns a 'SharedMemory_ID' using the function call shmget()
// Input : The 'sharedMemory_key' and the MAX_MEM_SIZE.
// Output : Returns the sharedMemory_ID by reference.
// System Call : shmget()
void getSharedMemoryID(int &sharedMemory_ID, const int sharedMemory_key, const int MAX_MEM_SIZE)
{
  // Get the shared memory ID and check for an error.
  sharedMemory_ID = shmget(sharedMemory_key, MAX_MEM_SIZE, SHM_R | SHM_W | IPC_CREAT);
  if ( sharedMemory_ID == -1 )
  {
    printf("main: shmget() shared memory failed\n");
    exit(0);
  }

  return;
}

// Description : This function releases all the semaphores and shared memory.
// Input : The 'sharedMemory_ID' and the 'semaphore_ID'.
// Output : None.
// System Call : shmctl(), semctl()
void releaseSharedResources(const int sharedMemory_ID, const int semaphore_ID)
{
  // Release the semaphores and shared memory while handiling errors.
  if ( shmctl(sharedMemory_ID, IPC_RMID, NULL) == -1 )
  {
    printf("main: shmctl() for releasing shared memory failed\n");
    exit(0);
  }

  if ( semctl(semaphore_ID, 1, IPC_RMID, NULL) == -1 )
  {
    printf("main: shmctl() for releasing semaphore failed\n");
    exit(0);
  }

  return;
}

// Description : This function returns the 'shmAddress' using the function call shmat()
// Input : The 'sharedMemory_ID'.
// Output : Returns the 'shmAddress' by reference.
// System Call : shmat()
void getShmAddress(int *&shmAddress, int sharedMemory_ID)
{
  // Attach the shared memory to this process.
  shmAddress = (int*)shmat(sharedMemory_ID, NULL, 0);
  if ( shmAddress == NULL )
  {
    printf("main(): shmat(): Error attaching memory.\n");
    exit(0);
  }

  return;
}
