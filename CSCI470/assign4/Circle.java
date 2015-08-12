import java.awt.Color;
import java.awt.Graphics;


public class Circle extends Shape
{
  public Circle()
  {
	super();
  }

  public void draw(Graphics g) 
  {
	g.setColor(Color.GREEN);  
	g.fillOval(xPosition, yPosition, size, size);
	
	// If the circle is fancy do additional drawing.
	if (isFancy)
	{
	  g.setColor(Color.GRAY);
	  g.fillOval(xPosition + size/4, yPosition + size/4 , size/2, size/2);
	} 
  }
}
