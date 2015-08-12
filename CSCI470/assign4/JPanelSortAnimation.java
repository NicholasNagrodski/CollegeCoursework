import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.Hashtable;

import javax.swing.*;

public class JPanelSortAnimation extends JPanel implements ActionListener, ChangeListener
{
  /**
   * 
   */
  private static final long serialVersionUID = -8249796760214869847L;
  private static final int NUM_BARS_MIN = 20; 
  private static final int NUM_BARS_MAX = 80;
  
  private static final int SPEED_SLOW = 1000;
  private static final int SPEED_FAST = 0;
  private static final int SPEED_INIT = (SPEED_FAST+SPEED_SLOW)/2;

  private static int currentNumberOfBars = 40;
  
  JPanelSortCanvas drawingCanvas;
  JPanel menuBar;

  JButton btn_StartSort;
  JButton btn_Reset;
  JSlider s_NumberOfBars;
  JSlider s_SortSpeed;


  public JPanelSortAnimation()
  {
    initComponents();
    
    // Give ourselves a nice border.
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
  }

  /*
   * This function responds to user button clicks on the Sort Animation
   * 
   * This function 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent ae)
  {
	/**
	 * If we press the start button, create a new thread and 
	 * disable the start button.
	 */
    if (ae.getSource().equals(btn_StartSort))
    {	
      btn_StartSort.setEnabled(false); 	
      new Thread(drawingCanvas).start();

    }
    /**
     * If we press the reset button enable the Start button
     * and kill the currently running sort.
     */
    if(ae.getSource().equals(btn_Reset))
    { 
      btn_StartSort.setEnabled(true);
      drawingCanvas.setNumberOfBars(s_NumberOfBars.getValue());
      drawingCanvas.kill();
    }
  }

  private void initComponents() 
  {
	/**
	 * Initialize the components.
	 */
    btn_StartSort = new JButton("Start Sort");
    btn_StartSort.addActionListener(this);

    btn_Reset = new JButton("Reset");
    btn_Reset.addActionListener(this);
    
    s_NumberOfBars = new JSlider(JSlider.HORIZONTAL, NUM_BARS_MIN, NUM_BARS_MAX, currentNumberOfBars);
    s_NumberOfBars.setMajorTickSpacing(20);
    s_NumberOfBars.setMinorTickSpacing(5);
    s_NumberOfBars.setSnapToTicks(true);
    s_NumberOfBars.setPaintTicks(true);
    s_NumberOfBars.setPaintLabels(true);
    
    s_SortSpeed = new JSlider(JSlider.HORIZONTAL, SPEED_FAST, SPEED_SLOW, SPEED_INIT);
    s_SortSpeed.setMajorTickSpacing(200);
    s_SortSpeed.setPaintTicks(true);
    // Create the label table for the speed slider.
    Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
    labelTable.put( new Integer( 0 ), new JLabel("Stop") );
    labelTable.put( new Integer( SPEED_FAST/4 ), new JLabel("Slow") );
    labelTable.put( new Integer( SPEED_FAST ), new JLabel("Fast") );
    s_SortSpeed.setLabelTable( labelTable );
    s_SortSpeed.setPaintLabels(true);
    s_SortSpeed.addChangeListener(this);
    
    // Create a panel to be the canvas and one to hold the menu.
    drawingCanvas = new JPanelSortCanvas(currentNumberOfBars);
    menuBar = new JPanel(new FlowLayout());
    
    // Add the components to the menu.
    menuBar.add(btn_StartSort);
    menuBar.add(btn_Reset);
    menuBar.add(s_NumberOfBars);
    menuBar.add(s_SortSpeed);

    // Add the menu bar and main canvas to the layout.
    this.setLayout(new BorderLayout());

    this.add(drawingCanvas, BorderLayout.CENTER);
    this.add(menuBar, BorderLayout.SOUTH);
  }

  public void stateChanged(ChangeEvent ce) 
  {
	drawingCanvas.setSortSpeed(s_SortSpeed.getValue());
  }
}//End class JPanelSortAnimation
