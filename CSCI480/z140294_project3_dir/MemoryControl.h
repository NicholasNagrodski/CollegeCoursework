//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// Programmer: Nicholas Nagrodski
// ZID: Z140294 
// CSCI 480 Fall 2010
// Assignment : 3
// Due Date: 12/02/2010
//
// Description:  This file contains the header information for the MemoryControl
//              group of functions.  These functions are common to both the 
//              reader and writer files.
//
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

#ifndef MEMORYCONTROL_H
#define MEMORYCONTROL_H

#include <cstdlib>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/ipc.h>

char const * const SEMKEYPATH = "/dev/null";     /* Path used on ftok for semget key.            */
const int SEMKEYID = 140294;                     /* Id used on ftok for semget key.              */
char const * const SHMKEYPATH = "/dev/null";     /* Path used on ftok for shmget key.            */
const int SHMKEYID = 140294;                     /* Id used on ftok for shmget key.              */

const int MAX_MEM_SIZE = 1024;               /* Maximum size in bytes for the shared memory space. */
const int NUMBER_OF_SEMS = 2;                /* The number of semaphores we need to create.        */
const int NUM_OF_MESSAGES = 10;              /* The number of messages that will be communicated.  */

union semun
{
  int val;
  struct semid_ds *buf;
  ushort *array;
};

void getSemaphoreKey(int &, char const * const, const int);
void getSharedMemoryKey(int &, char const * const , const int);

void getSemaphoreID(int &, const int, const int);
void getSharedMemoryID(int &, const int, const int);

void getShmAddress(int *&, int);

void releaseSharedResources(int, int);

#endif
