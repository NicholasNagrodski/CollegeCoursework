#**************************
# Nick Nagrodski
# CSCI 480
# Due: 9/23/2010
# Assignment 1 - makefile
#**************************

CC = g++
CCFLAGS = -Wall

z140294_project1: prog1.o
	$(CC) $(CCFLAGS) -o z140294_project1  prog1.o

prog1.o: prog1.cc
	$(CC) $(CCFLAGS) -c prog1.cc

clean:
	- rm *.o
	- rm z140294_project1
