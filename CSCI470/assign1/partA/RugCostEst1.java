/**
 * Assignemt: 1a
 * RugCostEst1.java
 *
 * Description: This program prompts the user for data pertaining to a carpet
 * cost estimator and calulates several numbers.
 *
 * Name: Nicholas Nagrodski
 * ZID: Z140294
 * Course: CSCI470 - Java
 * Prof: Jie Jhou
 * Due Date: 2011/02/04
 * Version: 1.0.0.0
 */
import java.io.*;
import java.util.Scanner;

public class RugCostEst1
{
  // Symbolic Constant Definitions.
  static final double TAX_RATE = 0.06;
  static final int JOB_SIZE_CUTOFF = 3000;
  static final double BIG_JOB_SURCHARGE = 0.05;
  static final double REG_JOB_SURCHARGE = 0.07;
  static final double REG_PADDING_COST = 1.00;
  static final double DLX_PADDING_COST = 1.50;
  static final double LABOR_PER_ROOM_CARPET = 10.00;
  static final double LABOR_PER_ROOM_FURNITURE = 20.00;

  public static void main(String[] args)
  {
    // Program variables.
    double squareFeetNeeded;
    double costPerSquareYard;
    int numberOfRoomsToCarpet;
    int numberOfRoomsToMoveFurniture;
    String paddingType;

    // Create a new scanner to read the input.
    Scanner sc = new Scanner(System.in);


    // Prompts and inputs.
    System.out.println("     *** Carpet Cost Estimator ***\n");
    System.out.println("Enter the following data:");
    System.out.print("Square Feet of carpet needed: ");
    squareFeetNeeded = sc.nextDouble();

    System.out.print("Carpet price per square yard: ");
    costPerSquareYard = sc.nextDouble();

    System.out.print("Number of rooms to be carpeted: ");
    numberOfRoomsToCarpet = sc.nextInt();

    System.out.print("Number of rooms that need furniture moved: ");
    numberOfRoomsToMoveFurniture = sc.nextInt();

    // Read in padding type until a valid input occurs.
    do
    {
      System.out.print("Padding Type, \"r\" for regular, \"d\" for delux, \"n\" for none: ");
      paddingType = sc.next();

      paddingType.toLowerCase();  // Make lowercase for comparison.
    } while( !(paddingType.equals("r") || paddingType.equals("d") || paddingType.equals("n")) );


    // Calculations Section.
    double carpetCost;
    double paddingCost;
    double laborCost;
    double subtotal;
    double taxTotal;
    double totalCost;

    // 1. Calaulate cost of carpet (in $).
    if (squareFeetNeeded > JOB_SIZE_CUTOFF)
    { // Big Job.
      carpetCost = squareFeetNeeded * costPerSquareYard / 9 * (1 + BIG_JOB_SURCHARGE);
    }
    else
    { // Regular Job.
      carpetCost = squareFeetNeeded * costPerSquareYard / 9 * (1 + REG_JOB_SURCHARGE);    
    }

    // 2. Calculate padding cost.
    if (paddingType.equals("r"))
    {
      // Regular Padding.
      paddingCost = squareFeetNeeded * REG_PADDING_COST / 9;
    }
    else if (paddingType.equals("d"))
    { 
      // Delux Padding.
      paddingCost = squareFeetNeeded * DLX_PADDING_COST / 9;
    }
    else
    { 
      // None.
      paddingCost = 0.0;
    }

    // 3. Labor Costs.
    laborCost = numberOfRoomsToCarpet * LABOR_PER_ROOM_CARPET + numberOfRoomsToMoveFurniture * LABOR_PER_ROOM_FURNITURE;
    // 4. Subtotal.
    subtotal = carpetCost + paddingCost + laborCost;
    // 5. Tax Amount.
    taxTotal = subtotal * TAX_RATE;
    // 6. Entire Total.
    totalCost = subtotal + taxTotal;


    // Ouput Section.
    System.out.println("\nCarpet Cost  : $" + carpetCost);
    System.out.println("Padding Cost : $" + paddingCost);
    System.out.println("Labor Cost   : $" + laborCost);
    System.out.println("Subtotal     : $" + subtotal);
    System.out.println("Tax Amount   : $" + taxTotal);
    System.out.println("Total        : $" + totalCost + "\nDone.\n");

  } // End Main
} // End RugCostEst1
