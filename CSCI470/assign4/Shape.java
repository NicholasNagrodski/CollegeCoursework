import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

public abstract class Shape 
{
  protected int xPosition;
  protected int yPosition;
  protected int xSpeed = 2;
  protected int ySpeed = 2;
  protected int size;
  
  protected boolean isFancy;
  protected static Dimension canvasDim;
  
  public Shape()
  {
  }
  
  public abstract void draw(Graphics g);
  
  public void setXSpeed(int xSpeed)
  {
	this.xSpeed = xSpeed;
  }
  public void setYSpeed(int ySpeed)
  {
	this.ySpeed = ySpeed;
  }
  
  public static void setCanvasDimension(Dimension d)
  {
	canvasDim = d;  
  }
  public void set()
  {
			isFancy = false;
				
			Random rnd = new Random();
				
			// Set an appropriate item size and starting coordinates.
			int scale = (canvasDim.width + canvasDim.height)/20;
			size = rnd.nextInt(scale/2)+scale/2;
			xPosition = canvasDim.width/2;
			yPosition = canvasDim.height/2;
  }
  public void move()
  {
	// Reverse the directions if needed.
	if (xPosition >= canvasDim.width || xPosition <= 0)
      xSpeed = -xSpeed;
	if (yPosition >= canvasDim.height || yPosition <= 0)
	  ySpeed = -ySpeed;	
	
	// Update the locations.
    xPosition += xSpeed;
	yPosition += ySpeed;
  }
}//End class Shape
