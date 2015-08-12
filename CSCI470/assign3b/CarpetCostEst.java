/**
 * Assignemt: 3b
 * CarpetCostEst.java
 *
 * Description: This program implements a class to give an invoice on a
 *  carpet apprasial.
 *
 * Name: Nicholas Nagrodski
 * ZID: Z140294
 * Course: CSCI470 - Java
 * Prof: Jie Jhou
 * Due Date: 2011/03/07
 * Version: 1.0.0.0
 *
 * Web URL: http://students.cs.niu.edu/~z140294/CarpetCostApplet2.html
 *
 */
public class CarpetCostEst
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

  // Input variables.
  double squareFeetNeeded;
  double costPerSquareYard;
  int numberOfRoomsToCarpet;
  int numberOfRoomsToMoveFurniture;
  char paddingType;

  // Output data.
  double carpetCost;
  double paddingCost;
  double laborCost;
  double subtotal;
  double taxTotal;
  double totalCost;

  public CarpetCostEst() {}
  public CarpetCostEst(double squareFeetNeeded,
                       double costPerSquareYard,
                       int numberOfRoomsToCarpet,
                       int numberOfRoomsToMoveFurniture,
                       char paddingType)
  {
    this.squareFeetNeeded = squareFeetNeeded;
    this.costPerSquareYard = costPerSquareYard;
    this.numberOfRoomsToCarpet = numberOfRoomsToCarpet;
    this.numberOfRoomsToMoveFurniture = numberOfRoomsToMoveFurniture;
    this.paddingType= paddingType;

    calculateCost();
  }

  /** This function calculates the six fields shown above to give a full invoice. */
  public void calculateCost()
  {
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
    if (paddingType == 'r')
    {
      // Regular Padding.
      paddingCost = squareFeetNeeded * REG_PADDING_COST / 9;
    }
    else if (paddingType == 'd')
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
    // 4. Subtotal.
    // 5. Tax Amount.
    // 6. Entire Total.
    laborCost = numberOfRoomsToCarpet * LABOR_PER_ROOM_CARPET + numberOfRoomsToMoveFurniture * LABOR_PER_ROOM_FURNITURE;
    subtotal = carpetCost + paddingCost + laborCost;
    taxTotal = subtotal * TAX_RATE;
    totalCost = subtotal + taxTotal;

  } // End public void CaclualteCost()

  /** This function trys to create a double from our input string
     and also tests whether it is in our specified range. */
  static double testRange(javax.swing.JTextField jtf, double maxInputValue) throws Exception, NumberFormatException
  {
    double doubleValue;

    // Try to create a double from our input string.
    //  If we fail will display a message and rethrow the error.
    try
    {
      doubleValue = new Double(jtf.getText()).doubleValue();
    }
    catch(NumberFormatException nfe)
    {
      jtf.setText(jtf.getText() + "<- Error");

      throw nfe;
    }

    // Now that it has been parsed, check to see if it is in range.
    if (doubleValue > maxInputValue)
      throw new Exception("Out of Range Input Value \"" + doubleValue + "\" is not in range [0," + maxInputValue + "]");
    else
      return doubleValue;
  }

  public double getCarpetCost()
  {
    return carpetCost;
  }
  public double getPaddingCost()
  {
    return paddingCost;
  }
  public double getLaborCost()
  {
    return laborCost;
  }
  public double getSubtotal()
  {
    return subtotal;
  }
  public double getTaxTotal()
  {
    return taxTotal;
  }
  public double getTotalCost()
  {
    return totalCost;
  }

  public void setSquareFeet(int sqrFeet)
  {
    this.squareFeetNeeded = sqrFeet;
  }
  public void setCostPerSquareYard(double costPerSquareYard)
  {
    this.costPerSquareYard = costPerSquareYard;
  }
  public void setRoomsToCarpet(int numRooms)
  {
    this.numberOfRoomsToCarpet = numRooms;
  }
  public void setRoomsToMoveFurniture(int numRooms)
  {
    this.numberOfRoomsToMoveFurniture = numRooms;
  }
  public void setPaddingType(char c)
  {
    this.paddingType = c;
  }

} // End class CarpetCostEst
