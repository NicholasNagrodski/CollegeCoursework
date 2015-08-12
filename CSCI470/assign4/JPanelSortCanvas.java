import java.lang.Thread;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class JPanelSortCanvas extends JPanel implements Runnable, WindowStateListener
{
  /**
   * 
   */
  private static final long serialVersionUID = 2769308484516444586L;
  private static int currentNumberOfBars;
  
  //Time in milliseconds that we pause between color changes.
  private static int sortSwapTime = 500; 
  private static boolean isKilled = false;
  private static int oldPanelHeight;
  private Color blueBarColor;
  
  List<SortBar> barList;

  public JPanelSortCanvas()
  {
	// Give ourselves a nice border.
    this.setBorder(BorderFactory.createLoweredBevelBorder());
	barList = new ArrayList<SortBar>();
    oldPanelHeight = this.getHeight();
	blueBarColor = new Color(100,149,237); 
  }
  public JPanelSortCanvas(int numberOfBars)
  {
	// Give ourselves a nice border.
	this.setBorder(BorderFactory.createLoweredBevelBorder());
    currentNumberOfBars = numberOfBars;
    barList = new ArrayList<SortBar>(currentNumberOfBars);
    oldPanelHeight = this.getHeight();
    blueBarColor = new Color(100,149,237);
  }
  
  public void setSortSpeed(int milliSeconds)
  {
	sortSwapTime = milliSeconds;
  }
  public void setNumberOfBars(int numberOfBars)
  {
	currentNumberOfBars = numberOfBars;
  }
  public void kill()
  {
	isKilled = true;
  }
  public int calcBarWidth()
  {
	return (int) (this.getWidth() / (double)currentNumberOfBars);
  }

  private int calcBarXPosition(SortBar sortBar, int index)
  {
	return index*SortBar.getBarWidth()+3;
  }
  private int calcBarYPosition(SortBar sortBar)
  {
	return this.getHeight() - sortBar.getHeight()-3; 
  }
  
  private void initializeBars()
  {
    // Get the Graphics and clear the display panel.
    Graphics g = this.getGraphics();
    barList.clear();
    g.clearRect(0, 0, this.getWidth(), this.getHeight());

    // Calculate the width of each bar.
    SortBar.setBarWidth(calcBarWidth());
    SortBar.setPanelHeight(this.getHeight());

    // Create an array of randomly sized bars to sort.
    Random rnd = new Random();
    for (int i = 0; i < currentNumberOfBars; i++)
    {
      barList.add(new SortBar(rnd.nextInt(getHeight()), 
    		      SortBar.getBarWidth(), blueBarColor));

      int xPosition = calcBarXPosition(barList.get(i), i);
      int yPosition = calcBarYPosition(barList.get(i));
      barList.get(i).draw(g, xPosition, yPosition);
    }
  }
  
  public void windowStateChanged(WindowEvent we) 
  {
    SortBar.setPanelHeight(this.getHeight());  	
  }
  public void paintComponent(Graphics g)
  {
	super.paintComponents(g);
    
	if (!barList.isEmpty())
	{ 
	  // Calculate new bar widths and heights.
	  SortBar.setBarWidth(calcBarWidth());
	  
	  int panelHeight = this.getHeight();
      for (int i = 0; i < currentNumberOfBars; i++)
	  {
    	// Resize the height of the bars.
    	SortBar bar = new SortBar(barList.get(i));
    	bar.setHeight(barList.get(i).getHeight() *
                panelHeight / 
                SortBar.getOldPanelHeight() );
    	barList.set(i, bar);
        
        int xPosition = calcBarXPosition(barList.get(i), i);
        int yPosition = calcBarYPosition(barList.get(i));
        barList.get(i).draw(g, xPosition, yPosition);
      }
	}
	this.paintBorder(g);
  }
  
  public void run()
  {
	isKilled = false;  
    initializeBars();
    Graphics g = this.getGraphics();

    /**
     * Perform the selection sort while animating.
     * We do this while there are still bars to swap, or until
     * the user presses the "Reset" button and sets isKilled.
     */
    for (int i = 0; (i < currentNumberOfBars-1 && !isKilled); i++)
    {
      int minimumIndex = i;

      // Find the smallest bar to swap.
      for (int j = i+1; j < currentNumberOfBars; j++)
      {
        if (barList.get(j).getHeight() < barList.get(minimumIndex).getHeight())
          minimumIndex = j;
      }

      // Set the bars to be swapped GREEN.
      barList.get(i).setColor(Color.GREEN);
      barList.get(i).draw(g, calcBarXPosition(barList.get(i), i), calcBarYPosition(barList.get(i)));
      barList.get(minimumIndex).setColor(Color.GREEN);
      barList.get(minimumIndex).draw(g, calcBarXPosition(barList.get(minimumIndex),minimumIndex),
    		                            calcBarYPosition(barList.get(minimumIndex)));
      
      try {
        Thread.sleep(sortSwapTime);
      } catch (InterruptedException ie) {
      }

      barList.get(i).setColor(Color.RED);
      barList.get(i).draw(g, calcBarXPosition(barList.get(i), i), calcBarYPosition(barList.get(i)));
      barList.get(minimumIndex).setColor(Color.RED);
      barList.get(minimumIndex).draw(g, calcBarXPosition(barList.get(minimumIndex),minimumIndex),
    		                            calcBarYPosition(barList.get(minimumIndex)));

      // erase the two bars
      g.setColor(this.getBackground());
      barList.get(i).erase(g, calcBarXPosition(barList.get(i), i), calcBarYPosition(barList.get(i)));
      barList.get(minimumIndex).erase(g, calcBarXPosition(barList.get(minimumIndex),minimumIndex),
    		                             calcBarYPosition(barList.get(minimumIndex)));

      // swap the two bars in the array
      SortBar temp = barList.get(i);
      barList.set(i, barList.get(minimumIndex));
      barList.set(minimumIndex, temp);

      // Draw them as red for 0.5 seconds.
      barList.get(i).setColor(Color.RED);
      barList.get(i).draw(g, calcBarXPosition(barList.get(i), i), calcBarYPosition(barList.get(i)));
      barList.get(minimumIndex).setColor(Color.RED);
      barList.get(minimumIndex).draw(g, calcBarXPosition(barList.get(minimumIndex),minimumIndex),
    		                            calcBarYPosition(barList.get(minimumIndex)));
      
      try {
        Thread.sleep(sortSwapTime);
      } catch (InterruptedException ie) {
      }

      //display the array[i] one in orange
      barList.get(i).setColor(Color.ORANGE);
      barList.get(i).draw(g, calcBarXPosition(barList.get(i), i), calcBarYPosition(barList.get(i)));

      //display the array[minimumIndex] one in blue
      barList.get(minimumIndex).setColor(blueBarColor);
      barList.get(minimumIndex).draw(g, calcBarXPosition(barList.get(minimumIndex),minimumIndex),
    		                            calcBarYPosition(barList.get(minimumIndex)));
    }
    if (isKilled)
    {
      initializeBars();
    }
  }

}