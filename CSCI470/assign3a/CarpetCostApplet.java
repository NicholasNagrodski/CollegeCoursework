/**
 * Assignemt: 3a
 * CarpetCostApplet.java
 *
 * Description: This program implements a GUI Applet for the carpet cost estimator program.
 *
 * Name: Nicholas Nagrodski
 * ZID: Z140294
 * Course: CSCI470 - Java
 * Prof: Jie Jhou
 * Due Date: 2011/03/07
 * Version: 1.0.0.0
 *
 * Web URL: http://students.cs.niu.edu/~z140294/CarpetCostApplet.html
 *
 */

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.*;



public class CarpetCostApplet extends JApplet implements ActionListener
{
  private static final long serialVersionUID = 123987577L;

  // Container for our FlowLayout.
  Container cp;

  // Labels/Fields for user input.
  JLabel l_SquareFeetNeeded;
  JLabel l_PricePerSquareYard;
  JLabel l_RoomCount;
  JLabel l_MoveFurniture;
  JLabel l_PaddingType;

  JTextField tf_SquareFeetNeeded;
  JTextField tf_PricePerSquareYard;
  JComboBox cb_RoomCount;
  JComboBox cb_MoveFurniture;

  JRadioButton rb_PaddingType_None;
  JRadioButton rb_PaddingType_Regular;
  JRadioButton rb_PaddingType_Delux;
  ButtonGroup bg_PaddingType;

  JButton b_Calculate;
  JButton b_Clear;

  // Labels/Fields for output.
  JLabel l_CarpetCost;
  JLabel l_PaddingCost;
  JLabel l_LaborCost;
  JLabel l_Subtotal;
  JLabel l_TaxCost;
  JLabel l_GrandTotal;

  JTextField tf_CarpetCost;
  JTextField tf_PaddingCost;
  JTextField tf_LaborCost;
  JTextField tf_Subtotal;
  JTextField tf_TaxCost;
  JTextField tf_GrandTotal;

  public void init()
  {
    initComponents();
  } // End init.

  /**
   * Controls 2 buttons to allow the user to 'clear' or 'calculate' an invoice.
   *
   * This function is an ActionEvent handler that controls the GUI form.  The user
   * can click 'clear' to reset all the entry fields to thier default values, or the
   * user can click 'Calculate' to parse in all of the input information and invoke
   * an invoice calculation.
   *
   * @param ae The ActionEvent passed by the event.
   * @author Nicholas Nagrodski
   *
   */
  public void actionPerformed(ActionEvent ae)
  {
    if (ae.getSource().equals(b_Clear))
    {
      clearEntryFields();
    }
    else if (ae.getSource().equals(b_Calculate))
    {
      try
      {
        calculateCarpetCost();
      }
      catch(Exception e)
      {
        this.showStatus("Applet Error: " + e.getMessage());
      }
    }
  }

  /**
   * Resets all the GUI user entry fields to their default values.
   *
   * @author Nicholas Nagrodski
   */
  private void clearEntryFields()
  {
    // Clear user entered values.
    tf_SquareFeetNeeded.setText("");
    tf_PricePerSquareYard.setText("");
    cb_RoomCount.setSelectedIndex(0);
    cb_MoveFurniture.setSelectedIndex(0);
    rb_PaddingType_Regular.setSelected(true);

    // Clear calculated values.
    tf_CarpetCost.setText("");
    tf_PaddingCost.setText("");
    tf_LaborCost.setText("");
    tf_Subtotal.setText("");
    tf_TaxCost.setText("");
    tf_GrandTotal.setText("");

    // Blank out Applet status bar.
    this.showStatus("");
  }


  /**
   * Calculates the carpet cost invoice using the user entered values.
   *
   * This function first parses in the two JTextFields to check if they
   * actually contain numbers.  If so they are passed into the CarpetCostEst
   * to provide us with the data we require.  Otherwise the exception will
   * propagate upward.  The calculated values are then displayed in the GUI.
   *
   * @author Nicholas Nagrodski
   */
  private void calculateCarpetCost()
  {
    // Blank out Applet status bar and then try to parse the two doubles.
    // If there isn't an issue the status bar should be blank.
    this.showStatus("");

    double sfn = Double.parseDouble(tf_SquareFeetNeeded.getText());
    double ppsy = Double.parseDouble(tf_PricePerSquareYard.getText());

    // Parse in the padding type.
    char paddingType = ' ';
    if (rb_PaddingType_None.isSelected())
      paddingType = 'n';
    else if (rb_PaddingType_Regular.isSelected())
      paddingType = 'r';
    else
      paddingType = 'd';

    // Create a new instance of our CarpetCostEst to calculate an invoice.
    CarpetCostEst cce = new CarpetCostEst(ppsy, sfn, cb_RoomCount.getSelectedIndex(),
                                          cb_MoveFurniture.getSelectedIndex(),
                                          paddingType);
    // Update all the output fields with the caclulations from cce.
	  DecimalFormat df = new DecimalFormat("0.##");
    tf_CarpetCost.setText(df.format(cce.getCarpetCost()));
    tf_PaddingCost.setText(df.format(cce.getPaddingCost()));
    tf_LaborCost.setText(df.format(cce.getLaborCost()));
    tf_Subtotal.setText(df.format(cce.getSubtotal()));
    tf_TaxCost.setText(df.format(cce.getTaxTotal()));
    tf_GrandTotal.setText(df.format(cce.getTotalCost()));
  }


  /**
   * This function initializes all of the text/buttons/fields in our Applet.
   *
   * initComponents initalizes all of JComponents that will be displayed on our
   * JApplet.  The componets are first initalized with all the relevant parameters.
   * The components are added to a GridLayout in a specific way to allow a nicely
   * formatted GUI.
   *
   * @author Nicholas Nagrodski
   * @return Returns void.
   **/
  private void initComponents()
  {
    String [] jListData = {"0","1","2","3","4","5","6","7","8","9","10 or More"};

    /** Input Field Init **/
    // Initialize all of our input Labels/Fields.
    l_SquareFeetNeeded = new JLabel("Square Feet Needed:");
    l_PricePerSquareYard = new JLabel("Price /  Square Yard:");
    l_RoomCount = new JLabel("Rooms to Carpet");
    l_MoveFurniture = new JLabel("Rooms to Move Furniture");
    l_PaddingType = new JLabel("Padding Type:");

    // Input JTextField.
    tf_SquareFeetNeeded = new JTextField();
    tf_SquareFeetNeeded.setHorizontalAlignment(JTextField.RIGHT);
    tf_PricePerSquareYard = new JTextField();
    tf_PricePerSquareYard.setHorizontalAlignment(JTextField.RIGHT);

    // Input ComboBox.
    cb_RoomCount = new JComboBox(jListData);
    cb_RoomCount.setEditable(false);
    cb_MoveFurniture = new JComboBox(jListData);
    cb_MoveFurniture.setEditable(false);

    // Create three radio buttons for the padding type selection and add them together.
    rb_PaddingType_None = new JRadioButton("None");
    rb_PaddingType_Regular = new JRadioButton("Regular", true);  // Set default as selected.
    rb_PaddingType_Delux = new JRadioButton("Delux");
    bg_PaddingType = new ButtonGroup();  // Create mutually exclusive radio buttons.
    bg_PaddingType.add(rb_PaddingType_None);
    bg_PaddingType.add(rb_PaddingType_Regular);
    bg_PaddingType.add(rb_PaddingType_Delux);

    // Create two buttons to clear and calculate the total.
    b_Calculate = new JButton("Caclulate");
    b_Calculate.addActionListener(this);
    b_Clear = new JButton("Clear Data");
    b_Clear.addActionListener(this);

    /** Output field initialization. **/
    // Initialize all of our output Labels/Fields.
    l_CarpetCost = new JLabel("Carpet Cost: ", SwingConstants.RIGHT);
    l_PaddingCost =  new JLabel("Padding Cost: ", SwingConstants.RIGHT);
    l_LaborCost = new JLabel("Labor Cost: ", SwingConstants.RIGHT);
    l_Subtotal = new JLabel("Subtotal: ", SwingConstants.RIGHT);
    l_TaxCost = new JLabel("Tax Cost: ", SwingConstants.RIGHT);
    l_GrandTotal = new JLabel("Grand Total: ", SwingConstants.RIGHT);

    // TextFields for user output.
    tf_CarpetCost = new JTextField();
    tf_CarpetCost.setEditable(false);
    tf_PaddingCost = new JTextField();
    tf_PaddingCost.setEditable(false);
    tf_LaborCost = new JTextField();
    tf_LaborCost.setEditable(false);
    tf_Subtotal = new JTextField();
    tf_Subtotal.setEditable(false);
    tf_TaxCost = new JTextField();
    tf_TaxCost.setEditable(false);
    tf_GrandTotal = new JTextField();
    tf_GrandTotal.setEditable(false);


    // Get a container to put our JFields in.
    // Then add all of the items to the Layout.
    cp = this.getContentPane();
    cp.setLayout(new GridLayout(7,4,5,5));

    // Now add each of the buttons, going across the 7row X 4col grid.
    // Row 1.
    cp.add(l_SquareFeetNeeded);
    cp.add(tf_SquareFeetNeeded);
    cp.add(l_CarpetCost);
    cp.add(tf_CarpetCost);

    // Row 2.
    cp.add(l_PricePerSquareYard);
    cp.add(tf_PricePerSquareYard);
    cp.add(l_PaddingCost);
    cp.add(tf_PaddingCost);

    // Row 3.
    cp.add(l_RoomCount);
    cp.add(cb_RoomCount);
    cp.add(l_LaborCost);
    cp.add(tf_LaborCost);

    // Row 4.
    cp.add(l_MoveFurniture);
    cp.add(cb_MoveFurniture);
    cp.add(l_Subtotal);
    cp.add(tf_Subtotal);

    // Row 5.
    cp.add(l_PaddingType);
    cp.add(rb_PaddingType_None);
    cp.add(l_TaxCost);
    cp.add(tf_TaxCost);

    // Row 6.
    cp.add(rb_PaddingType_Regular);
    cp.add(rb_PaddingType_Delux);
    cp.add(l_GrandTotal);
    cp.add(tf_GrandTotal);

    // Row 7.
    cp.add(b_Calculate);
    cp.add(b_Clear);
  } // End private void initComponents()

} // End CarpetCostApplet
