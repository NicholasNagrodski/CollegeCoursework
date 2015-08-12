/**
 * Assignemt: 4
 * JPanelBallAnimation.java
 *
 * Description: This program is a standalone Java application that
 *  implements two JPanels, one with a animated sort
 *  and the other with a bouncing ball graphic that the user can control.
 *
 * @see Assign4.java for the full description.
 *
 * Name: Nicholas Nagrodski
 * ZID: Z140294
 * Course: CSCI470 - Java
 * Prof: Jie Jhou
 * Due Date: 2011/04/SDFASDASDASDA
 * Version: 1.0.0.0
 *
 */
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Assign4 extends JFrame implements WindowListener
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  JPanelSortAnimation sortPanel;
  JPanelBallAnimation ballPanel;

  public static void main(String[] args)
  {
    Assign4 frame = new Assign4();
    frame.setVisible(true);
  }

  public void windowActivated(WindowEvent we) {}
  public void windowClosed(WindowEvent we) {}
  public void windowDeactivated(WindowEvent we) {}
  public void windowDeiconified(WindowEvent we) {}
  public void windowIconified(WindowEvent we) {}
  public void windowOpened(WindowEvent we) {}

  public void windowClosing(WindowEvent we)
  {
    this.dispose();
    System.exit(0);
  }
  
  public Assign4()
  {
    sortPanel = new JPanelSortAnimation();
    ballPanel = new JPanelBallAnimation(); //JPanelBallAnimation();
    //ballPanel.setBackground(java.awt.Color.GRAY);

    this.setTitle("JAVA - Assign4 - Nicholas Nagrodski");
    this.setSize(1200,500);
    this.setLayout(new GridLayout(1, 2, 10, 10));

    this.getContentPane().add(sortPanel);
    this.getContentPane().add(ballPanel);
  }

}
