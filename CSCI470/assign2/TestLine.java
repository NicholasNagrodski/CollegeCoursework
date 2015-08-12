/**
 * Assignemt: 2
 * TestLine.java
 *
 * Name: Nicholas Nagrodski
 * ZID: Z140294
 * Course: CSCI470 - Java
 * Prof: Jie Jhou
 * Due Date: 2011/02/15
 * Version: 1.0.0.0
 *
 * This program demonstrates a simple "Line" class. 
 * Here, a Line class is defined with its properties and
 * interface (i.e., its methods).  
 * A main class then creates instances of this Line class 
 * and calls on the methods to demonstrate its behavior.
 *
 * CODE UPDATES: 2011/02/10 - Nicholas Nagrodski
 *  - Added getLength()
 *  - 
 */

import java.io.*;
import java.lang.Math;
import java.text.DecimalFormat;

//****************************************************************
class Line
{
  private int x1, y1, x2, y2;     // Coordinates of the line
  private int lineWidth = 1;          // Line width.
  private String lineColor;       // Line color.
  
  DecimalFormat df = new DecimalFormat("0.##");  // Declare a new Decimal Format for pretty Printing.

  //Constructor 1
  //Receives 4 integers which are the Line's start and end points.
  public Line(int left, int top, int right, int bottom) throws Exception
  {
    // each of these validates its argument - see below.
    setLeft(left);
    setTop(top);
    setRight(right);
    setBottom(bottom);
  } //End constructor 1    

  //Constructor 2
  public Line(TwoDPoint p1, TwoDPoint p2) throws Exception
  {
    this(p1.x, p1.y, p2.x, p2.y);
  }


  //*************************************
  //method draw() calls another method called drawLine(),
  //which is assumed to be a graphics primitive on the
  //system.  However, since this program will be
  //run in console mode, a text description of the Line
  //will be displayed. 
  //
  public void draw()
  {
    drawLine(x1, y1, x2, y2);
  }

  //*************************************
  //method drawLine() simulates drawing of a line for console mode.
  //It should describe all the important attributes of the line.
  //In a graphics mode program, we would delete this and use the
  //system's Graphics library drawLine().
  //
  private void drawLine(int x1, int y1, int x2, int y2)
  {
    System.out.println("Draw a line from x of " + x1 + " and y of " + y1);
    System.out.println("to x of " + x2 + " and y of " + y2);
    System.out.println("The line color is " + lineColor);
    System.out.println("The width of the line is " + lineWidth);
    System.out.println("Then length of the line is " + df.format(getLength()) + "\n" );
  }

  //*************************************
  //Method setLine() allows user to change the points of the 
  //already existing Line.
  //
  public void setLine(int left, int top, int right, int bottom) throws Exception
  {
    setLeft(left);
    setTop(top);
    setRight(right);
    setBottom(bottom);    
  }  

  // -- the individual setXXXX methods that prevent
  //    any line's coordinate from being offscreen.
  //    In the event of an invalid (offscreen) value,
  //    that method call throws an exception and the
  //    calling code handles it.

  //**************************
  public void setLineWidth(int width)
  {
    if (width > 0)
      lineWidth = width;
    else 
      lineWidth = 1;
  } 

  //**************************
  public void setLineColor(String color)
  {
    if ( !color.equals("") )
      lineColor = color;
    else
      lineColor = "Grey";
  }

  //**************************
  public void setLeft(int left) throws Exception
  {
    if (left < 0 || left > 639)
      throw new Exception("-> Error: Left Value (" + left + ") is our of range." );
    else
      x1 = left;
  }

  //**************************
  public void setRight(int right) throws Exception
  {
    if (right > 639 || right < 0)
      throw new Exception("-> Error: Right Value (" + right + ") is our of range." );
    else
      x2 = right;
  }

  //**************************
  public void setTop(int top) throws Exception
  {
    if (top < 0 || top > 479)
      throw new Exception("-> Error: Top Value (" + top + ") is our of range." );
    else
      y1 = top;
  }

  //**************************
  public void setBottom(int bottom) throws Exception
  {
    if (bottom > 479 || bottom < 0) 
      throw new Exception("-> Error: Bottom Value (" + bottom + ") is our of range." );
    else
      y2 = bottom;
  }


  //Now for some "get" Access methods to get individual values

  //**************************
  public String getLineColor()
  {
    return lineColor;
  }

  //**************************
  // This function calculates the distance between the two points.
  public double getLength()
  {
    return Math.sqrt( Math.pow((x2-x1),2.0) + Math.pow((y2-y1),2.0) );
  }

  //**************************
  public int getLeft()
  {
    return x1;
  }

  //**************************
  public int getTop()
  {
    return y1;
  }

  //**************************
  public int getRight()
  {
    return x2;
  }

  //**************************
  public int getBottom()
  {
    return y2;
  }  

} // end class Line

 

/***************************************************************
 Now we will define a class with main() where execution will begin. It is this
 class, and this code, that will create instances of the Line and call its
 methods.

 As a test module, this code would be improved with additional 
 System.out.println() statements that explain what is being attempted and
 what the results should be, for example:

 "About to change l1 to an invalid value and then redraw it.  Line position
  should not change: "
*/

//****************************************************************
public class TestLine
{
  public static void main(String args[])
  {
    Line l1 = null, l2 = null;                  // Declare 2 instances of Line class

    // Try create 1 Line object.
    System.out.println("Attempting: to create l1, a blue line, with a width of 4, from (10,10) to (100,100):");
    try 
    {
      l1 = new Line(10, 10, 100, 100);
      l1.setLineColor("Blue");
      l1.setLineWidth(4);

      //draw it
      l1.draw();    
    } 
    catch(Exception e)
    {
      System.out.println("-> Error in Constructor, Exit Prog: " + e.getMessage());
      System.exit(0);
    }

    // Try to change start point with valid values.
    System.out.println("Attempting: to chage l1, to a GREEN line, with a width of 2, from (5,5) to (100,100):");
    try
    {
      l1.setLine(5, 5, l1.getRight(), l1.getBottom());    
      l1.setLineColor("Green");
      l1.setLineWidth(2);

      //draw it again with new start point
      l1.draw();    
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }

    // Try to change left (x1) to an illegal value
    System.out.println("Attempting: to set l1's left point (x1) to an illegal value:");
    try
    {
      l1.setLeft(3000);    

      //draw the line...x1 should now be zero
      l1.draw();    
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }


    //create a second Line instance, l2.
    System.out.println("Attempting: to create l2, an orange line from (100,100) to (400,400):");
    try
    {
      l2 = new Line(new TwoDPoint(100,100), new TwoDPoint(400,400));    
      l2.setLineColor("Orange");

      //draw it
      l2.draw();
    }
    catch(Exception e)
    {
      System.out.println("-> Error in Constructor, Exit Prog: " + e.getMessage());
      System.exit(0);
    }

    //set a new valid bottom for line 2
    System.out.println("Attempting: to set l2's bottom point (y2) to an illegal value:");
    try
    {
       l2.setBottom(479);    

       //draw 2nd line again
       l2.draw();
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }

  } // end of main
} // end class TestLine
