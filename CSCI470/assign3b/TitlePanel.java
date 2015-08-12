/**
 * Assignemt: 3b
 * TitlePanel.java
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
public class TitlePanel extends javax.swing.JPanel
{
    public TitlePanel()
    {
      super();
    }

    public void paintComponent(java.awt.Graphics g)
    {
      super.paintComponent(g);

      String title = "Acme Carpet Job Estimator";
      g.setFont(new java.awt.Font("sansserif", java.awt.Font.ITALIC+java.awt.Font.BOLD, 48));
      g.setColor(new java.awt.Color(105, 139, 34));

      java.awt.FontMetrics fm = g.getFontMetrics();
      int y = fm.getHeight() + 5;
      int x = (this.getSize().width - fm.stringWidth(title))/2;

      g.drawString(title, x, y);
    }


    public java.awt.Dimension getPreferredSize()
    {
      return new java.awt.Dimension(300, 80);  // you may need to experiment with argument values
    }
}