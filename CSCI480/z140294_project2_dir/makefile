#**************************
# Nick Nagrodski
# Z140294
# CSCI 480
# Due: 10/21/2010
# Assignment 2 - makefile
#**************************

CC = g++
CCFLAGS = -Wall

z140294_project2: prog2.o
	$(CC) $(CCFLAGS) -o z140294_project2  prog2.o

prog2.o: prog2.cc
	$(CC) $(CCFLAGS) -c prog2.cc

clean:
	- rm *.o
	- rm z140294_project2
