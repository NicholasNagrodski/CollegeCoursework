#**************************
# Nick Nagrodski
# Z140294
# CSCI 480
# Due: 12/02/2010
# Assignment 3 - makefile
#**************************

CC = g++
CCFLAGS = -Wall

all: z140294_reader z140294_writer

z140294_reader: reader.o MemoryControl.o
	$(CC) $(CCFLAGS) -o z140294_reader reader.o MemoryControl.o

z140294_writer: writer.o MemoryControl.o
	$(CC) $(CCFLAGS) -o z140294_writer writer.o MemoryControl.o

MemoryControl.o: MemoryControl.cc MemoryControl.h
	$(CC) $(CCFLAGS) -c MemoryControl.cc

reader.o: reader.cc MemoryControl.h
	$(CC) $(CCFLAGS) -c reader.cc

writer.o: writer.cc MemoryControl.h
	$(CC) $(CCFLAGS) -c writer.cc

clean:
	- rm *.o
	- rm z140294_reader
	- rm z140294_writer
