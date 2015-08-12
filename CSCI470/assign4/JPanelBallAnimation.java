import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EtchedBorder;

public class JPanelBallAnimation extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 5876855578358462038L;
  
  private static final int SPEED_SLOW = 3;
  private static final int SPEED_MED= 6;
  private static final int SPEED_FAST = 12;
 
  // Two main containers: A canvas and a menuBar.
  JPanelBallCanvas drawingCanvas;
  JMenuBar mainMenuBar;
  
  // Menu Items and their associated components.
  JMenuItem btn_Start;
  JMenuItem btn_Stop;
  JMenuItem btn_Clear;
  JMenuItem btn_Create;
  JMenu menuType;
    JCheckBoxMenuItem menuType_Circle;
    JCheckBoxMenuItem menuType_Square;
    JCheckBoxMenuItem menuType_Triangle; 
    ButtonGroup bg_menuType;
  JMenu menuXSpeed;
    JCheckBoxMenuItem menuXSpeed_NegDir;
    JRadioButtonMenuItem menuXSpeed_Fast;
    JRadioButtonMenuItem menuXSpeed_Med;
    JRadioButtonMenuItem menuXSpeed_Slow;
    ButtonGroup bg_menuXSpeed;
  JMenu menuYSpeed;
    JCheckBoxMenuItem menuYSpeed_NegDir;
    JRadioButtonMenuItem menuYSpeed_Fast;
    JRadioButtonMenuItem menuYSpeed_Med;
    JRadioButtonMenuItem menuYSpeed_Slow; 
    ButtonGroup bg_menuYSpeed;
  JMenu menuFancy;
    JCheckBoxMenuItem menuFancy_IsFancy;
  JMenu menuTrails;
    JRadioButtonMenuItem menuTrails_HasTrails;
    JRadioButtonMenuItem menuTrails_NoTrails;
    ButtonGroup bg_menuTrails;
  
  
  public JPanelBallAnimation()
  {
    super();
    initComponents();
    
    // Give ourselves a nice border.
    this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
  }

  public void actionPerformed(ActionEvent ae) 
  {
	  
	if (ae.getSource().equals(btn_Create))
	{
	  if (menuType_Circle.isSelected())
		drawingCanvas.addShape(new Circle());
//	  else if (menuType_Square.isSelected())
//		drawingCanvas.addShape(new Square());
//	  else if (menuType_Triangle.isSelected())
//		drawingCanvas.addShape(new Triangle());
	  btn_Start.setEnabled(true);
	}
	else if (ae.getSource().equals(btn_Start))
	{
	  // 1. Start a new thread to run out animation.
      // 2. Turn on the Stop button.
	  new Thread(drawingCanvas).start();
	  btn_Stop.setEnabled(true);
	}
	else if (ae.getSource().equals(btn_Stop))
	{
	  drawingCanvas.setIsRunning(false);
	}
	else if (ae.getSource().equals(btn_Clear))
	{
	  // Clear.
	}
	else if (ae.getSource().equals(menuTrails_HasTrails))
	  drawingCanvas.setTrails(true);
	else if (ae.getSource().equals(menuTrails_NoTrails))
	  drawingCanvas.setTrails(false);
	
  }

  private void initComponents()
  {
	this.setLayout(new BorderLayout());  
	
	mainMenuBar = new JMenuBar();  
    drawingCanvas = new JPanelBallCanvas();
    
    btn_Start = new JMenuItem("Start");
    btn_Start.setEnabled(false);
    btn_Start.addActionListener(this);

    btn_Stop = new JMenuItem("Stop");
    btn_Stop.setEnabled(false);
    btn_Stop.addActionListener(this);
    
    btn_Clear = new JMenuItem("Clear");
    btn_Clear.addActionListener(this);
    
    btn_Create = new JMenuItem("Create");
    btn_Create.addActionListener(this);
    
    menuType = new JMenu("Type");
    menuType_Circle = new JCheckBoxMenuItem("Circle");
    menuType_Square = new JCheckBoxMenuItem("Square",true);
    menuType_Triangle = new JCheckBoxMenuItem("Triangle");
    menuType.add(menuType_Circle);
    menuType.add(menuType_Square);
    menuType.add(menuType_Triangle);
    menuType.getPopupMenu().setLightWeightPopupEnabled(false);
//    bg_menuType = new ButtonGroup();
//    bg_menuTrails.add(menuType_Circle);
//    bg_menuTrails.add(menuType_Square);
//    bg_menuTrails.add(menuType_Triangle);  
    
    menuXSpeed = new JMenu("X-Speed");
    menuXSpeed_NegDir = new JCheckBoxMenuItem("Neg Dir",false);
    menuXSpeed_Fast = new JRadioButtonMenuItem("Fast");
    menuXSpeed_Med = new JRadioButtonMenuItem("Med",true);   
    menuXSpeed_Slow = new JRadioButtonMenuItem("Slow");
    menuXSpeed.add(menuXSpeed_NegDir);
    menuXSpeed.add(menuXSpeed_Fast);
    menuXSpeed.add(menuXSpeed_Med);
    menuXSpeed.add(menuXSpeed_Slow);
    menuXSpeed.getPopupMenu().setLightWeightPopupEnabled(false);

    bg_menuXSpeed = new ButtonGroup();
    bg_menuXSpeed.add(menuXSpeed_Fast);
    bg_menuXSpeed.add(menuXSpeed_Med);
    bg_menuXSpeed.add(menuXSpeed_Slow);
    
    menuYSpeed = new JMenu("Y-Speed");
    menuYSpeed_NegDir = new JCheckBoxMenuItem("Neg Dir",false);
    menuYSpeed_Fast = new JRadioButtonMenuItem("Fast");
    menuYSpeed_Med = new JRadioButtonMenuItem("Med",true);   
    menuYSpeed_Slow = new JRadioButtonMenuItem("Slow");
    menuYSpeed.add(menuYSpeed_NegDir);
    menuYSpeed.add(menuYSpeed_Fast);
    menuYSpeed.add(menuYSpeed_Med);
    menuYSpeed.add(menuYSpeed_Slow);
    menuYSpeed.getPopupMenu().setLightWeightPopupEnabled(false);
    bg_menuYSpeed = new ButtonGroup();
    bg_menuYSpeed.add(menuYSpeed_Fast);
    bg_menuYSpeed.add(menuYSpeed_Med);
    bg_menuYSpeed.add(menuYSpeed_Slow);
    
    menuFancy = new JMenu("Fancy?");
    menuFancy_IsFancy = new JCheckBoxMenuItem("Fancy", false);
    menuFancy.add(menuFancy_IsFancy);
    menuFancy.getPopupMenu().setLightWeightPopupEnabled(false);
    
    menuTrails = new JMenu("Trails");
    menuTrails_HasTrails = new JRadioButtonMenuItem("On", true);
    menuTrails_NoTrails = new JRadioButtonMenuItem ("Off");
    menuTrails.add(menuTrails_HasTrails);
    menuTrails.add(menuTrails_NoTrails);
    menuTrails.getPopupMenu().setLightWeightPopupEnabled(false);
    bg_menuTrails = new ButtonGroup();
    bg_menuTrails.add(menuTrails_HasTrails);
    bg_menuTrails.add(menuTrails_NoTrails);
    
    mainMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT)); 
    mainMenuBar.add(btn_Start);
    mainMenuBar.add(btn_Stop);
    mainMenuBar.add(btn_Clear);
    mainMenuBar.add(btn_Create);
    mainMenuBar.add(menuType);
    mainMenuBar.add(menuXSpeed);
    mainMenuBar.add(menuYSpeed);
    mainMenuBar.add(menuFancy);
    mainMenuBar.add(menuTrails);
    
    // Add the menu bar and the canvas.
    this.add(mainMenuBar, BorderLayout.NORTH);
    this.add(drawingCanvas, BorderLayout.CENTER);
  }
}//End class JPanelBallAnimation