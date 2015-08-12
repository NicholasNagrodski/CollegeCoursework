import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class JPanelBallCanvas extends JPanel implements Runnable
{
  private static final long serialVersionUID = 6654691588995566053L;
  
  private List<Shape> shapeList;
  private boolean trailsAreOn = false;
  private boolean threadIsRunning = true;
  
  public JPanelBallCanvas()
  {    
    shapeList = new ArrayList<Shape>();	
  }
  
  public void setTrails(boolean b)
  {
	trailsAreOn = b;
  }
  public void setIsRunning(boolean b)
  {
	threadIsRunning = b;
  }
  public void addShape(Shape shape)
  {
	shapeList.add(shape);
  }
  
  public void run()
  {
	Shape.setCanvasDimension(this.getSize());
	Graphics g = this.getGraphics();
	for (int i = 0; i < shapeList.size(); i++)
	{
	  shapeList.get(i).set();
	}
	// Draw the shapes and move them forever.  
    while(threadIsRunning)
	{
      // If there are no trails then erase the canvas.
      if (!trailsAreOn)
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
    	
  	  // Then draw and move all the shapes.
      for (int i = 0; i < shapeList.size() && threadIsRunning; i++)
      {	    
    	shapeList.get(i).draw(g);
    	shapeList.get(i).move();  
      }
      this.repaint();
    }
  }
}//End class JPanelBallCanvas