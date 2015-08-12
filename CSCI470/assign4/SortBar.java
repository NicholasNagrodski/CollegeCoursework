import java.awt.Color;
import java.awt.Graphics;

public class SortBar
{
  private int barHeight;
  private static int width;
  private static int maxBarHeight;
  private Color barColor;

  public SortBar(int height, int width, Color color)
  {
    this.barHeight = height;
    SortBar.width = width;
    this.barColor = color;
  }
  public SortBar(SortBar b)
  {
	this.barHeight = b.barHeight;
	this.barColor = b.barColor;
  }

  // public methods.
  public int getHeight()
  {
    return barHeight;
  }
  public void setHeight(int height)
  {
	this.barHeight = height;
  }
  
  public void setColor(Color color)
  {
    barColor = color;
  }
  
  public static void setPanelHeight(int height)
  {
	SortBar.maxBarHeight = height;
  }
  public static int getOldPanelHeight()
  {
	return SortBar.maxBarHeight;
  }
  public static void setBarWidth(int newWidth)
  {
	SortBar.width = newWidth;
  }
  public static int getBarWidth()
  {
	return SortBar.width;
  }

  public void draw(Graphics g, int xPosition, int yPosition)
  {
    Color oldColor = g.getColor();
    g.setColor(this.barColor);
    g.fill3DRect(xPosition, yPosition, SortBar.width, this.barHeight, true);
    g.setColor(oldColor);
  }

  public void erase(Graphics g, int xPosition, int yPosition)
  {
    g.fillRect(xPosition, yPosition, SortBar.width, this.barHeight);
  }
}