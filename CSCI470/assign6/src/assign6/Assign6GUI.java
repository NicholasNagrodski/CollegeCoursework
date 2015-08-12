package assign6;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Assign6GUI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -2828644739261903189L;
	private static final int PAD = 3;

	protected Socket s = null;
	protected final String HOST = "turing.cs.niu.edu";
	protected final int PORT = 9728;
	
    protected ObjectOutputStream out;
    protected ObjectInputStream  in;
	
    protected JPanel mainPanel; // Holds all the sub-panels
	  // Panels to hold each of our operations.
      protected JPanel addPanel;
      protected JButton btnAddCust;
      protected JTextField tfNameAP;
      protected JTextField tfSSNAP;
      protected JTextField tfaddressAP;
      protected JTextField tfzipCodeAP;
	      
    protected JPanel updatePanel;
      protected JButton btnUpdateCust;
      protected JTextField tfSSNUP;
      protected JTextField tfaddressUP;
	  
    protected JPanel searchPanel;
      protected JButton btnSearch;
      protected JTextField tfNameSP;
      protected JTextField tfSSNSP;
      protected JTextField tfaddressSP;
      protected JTextField tfzipCodeSP;
	      
    protected JPanel deletePanel;
	  protected JButton btnDeleteCust;
	  protected JTextField tfSSNDP;
	    
	protected JPanel showAllPanel;
	  protected JButton btnShowAllCust;
	  protected JScrollPane showAllScrollPane;
	  protected JTextArea taShowAll;
	
	protected JScrollPane msgScrollPane;
	static JTextArea msgTextArea;
	
	public static void main(String[] args) 
	{
      Assign6GUI frame = new Assign6GUI();
      frame.setSize(900, 500);
      frame.setResizable(false);
      frame.setVisible(true);
	}
	
	public Assign6GUI()
	{
	  initComponents();	
	  
	  connectToServer();
	  
	  try
	  {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	  }

	  catch (Exception e)
	  {
	    System.out.println("Unable to load Windows look and feel");
	  }
	}

    /**
     * Initiates a connection with the server to make sure it exists.
     */
    private void connectToServer()
    {    	  
	  try
	  {
		s = new Socket(HOST, PORT);
		Assign6GUI.addMessage("Server is running.");

        s.close();		
	  }
	  catch (IOException e)
	  {
		Assign6GUI.addMessage(e.getMessage());  
	  }
	}
    
    /**
     * Initialize the connection nessecary for a Socket communication with the server and send the Message.
     * @param msg
     * @return
     */
    private Message sendMessage(Message msg)
    {           
	  try
	  {
		s = new Socket(HOST, PORT);

        out = new ObjectOutputStream(s.getOutputStream());
        out.flush();
        
        in = new ObjectInputStream(s.getInputStream());
        
        out.writeObject(msg);
        out.flush();
        
        msg = (Message) in.readObject();
        
        in.close();
        out.close();
 
        return msg;
	  }
	  catch (ClassNotFoundException e1) 
	  {
		msg.setMessage("Error: Unable to connect to server.");
		return msg;
	  }
	  catch (IOException e2)
	  {
		Assign6GUI.addMessage("Unable to read serial object: " + e2.getMessage());  
	  }
	  
	  msg.setMessage("Fatal Error connection to server");
	  return msg;
	}

	private void initComponents()
    {
      mainPanel = new JPanel(new GridLayout(2, 3, 5, 5)); // Holds all the sub-panels
      
	    // Panels to hold each of our operations.
	    addPanel = new JPanel(new GridLayout(5, 2, PAD, PAD));
	    addPanel.setBorder(BorderFactory.createTitledBorder("Add Customer To the Database"));
	      btnAddCust = new JButton("Add Customer");
	      btnAddCust.addActionListener(this);
	      tfNameAP = new JTextField();
	      tfSSNAP = new JTextField();
	      tfaddressAP = new JTextField();
	      tfzipCodeAP = new JTextField();
	  	    
	      addPanel.add(btnAddCust);
	      addPanel.add(btnAddCust);
	      addPanel.add(new JLabel("Customer Info:", JLabel.CENTER));
	      addPanel.add(new JLabel("Name: ", JLabel.RIGHT));
	      addPanel.add(tfNameAP);
	      addPanel.add(new JLabel("SSN: ", JLabel.RIGHT));
	      addPanel.add(tfSSNAP);
	      addPanel.add(new JLabel("Address:", JLabel.RIGHT));
	      addPanel.add(tfaddressAP);
 	      addPanel.add(new JLabel("Zip Code:", JLabel.RIGHT));
  	      addPanel.add(tfzipCodeAP);
	  	      
	    updatePanel = new JPanel(new GridLayout(3, 2, PAD, PAD));
	    updatePanel.setBorder(BorderFactory.createTitledBorder("Update a Customer's Address"));
	      btnUpdateCust = new JButton("Update Address");
	      btnUpdateCust.addActionListener(this);
	      tfSSNUP = new JTextField();
	      tfaddressUP = new JTextField();
	     
  	      updatePanel.add(btnUpdateCust);
  	      updatePanel.add(new JLabel("Customer Info:", JLabel.CENTER));
  	      updatePanel.add(new JLabel("Find User by SSN: ", JLabel.RIGHT));
  	      updatePanel.add(tfSSNUP);	  
  	      updatePanel.add(new JLabel("Updated Address: ", JLabel.RIGHT));	 
	      updatePanel.add(tfaddressUP);	 
	  	  
	    searchPanel = new JPanel(new GridLayout(5, 2, PAD, PAD));
	    searchPanel.setBorder(BorderFactory.createTitledBorder("Search For Customer By SSN"));
	   	  btnSearch = new JButton("Search by SSN: ");
	   	  btnSearch.addActionListener(this);
	  	  tfNameSP = new JTextField();
	  	  tfSSNSP = new JTextField();
	  	  tfaddressSP = new JTextField();
	  	  tfzipCodeSP = new JTextField();
	  	  tfNameSP.setEditable(false);
	  	  tfaddressSP.setEditable(false);
	  	  tfzipCodeSP.setEditable(false);
	  	    
	   	  searchPanel.add(btnSearch);
	   	  searchPanel.add(tfSSNSP);
	  	  searchPanel.add(new JLabel(""));
	  	  searchPanel.add(new JLabel("Customer Info:", JLabel.RIGHT));
	  	  searchPanel.add(new JLabel("Name: ", JLabel.RIGHT));
	  	  searchPanel.add(tfNameSP);
	      searchPanel.add(new JLabel("Address:", JLabel.RIGHT));
	  	  searchPanel.add(tfaddressSP);
	      searchPanel.add(new JLabel("Zip Code:", JLabel.RIGHT));
	      searchPanel.add(tfzipCodeSP);
	  	       
	    deletePanel = new JPanel(new GridLayout(2, 2, PAD, PAD));
	    deletePanel.setBorder(BorderFactory.createTitledBorder("Delete Customer"));
	  	  btnDeleteCust = new JButton("Delete Customer");
	  	  btnDeleteCust.addActionListener(this);
	  	  tfSSNDP = new JTextField();
	  	  
	   	  deletePanel.add(btnDeleteCust);
	  	  deletePanel.add(new JLabel("<html>Customer to Delete:</html>", JLabel.CENTER));
		  deletePanel.add(new JLabel("SSN: ", JLabel.RIGHT));
		  deletePanel.add(tfSSNDP);    
	  	    
	    showAllPanel = new JPanel(new BorderLayout(PAD, PAD));
	    showAllPanel.setBorder(BorderFactory.createTitledBorder("Show All Customers Panel"));
	      btnShowAllCust = new JButton("Show All Customers");
	      btnShowAllCust.addActionListener(this);
		  taShowAll = new JTextArea(6, 10);
		  taShowAll.setEditable(false);
	      showAllScrollPane = new JScrollPane(taShowAll);
	  	    
	      showAllPanel.add(btnShowAllCust, BorderLayout.NORTH);
	  	  showAllPanel.add(showAllScrollPane, BorderLayout.CENTER);

	  // Add the top-level items to the JFrame.	  	  
	  mainPanel.add(addPanel);
	  mainPanel.add(updatePanel);
	  mainPanel.add(searchPanel);
	  mainPanel.add(deletePanel);
	  mainPanel.add(showAllPanel);
  	
  	  msgTextArea = new JTextArea(8, 40);
  	  msgTextArea.setFont(new Font("Verdana", Font.BOLD, 12));
  	  msgTextArea.setEditable(false);
  	  msgScrollPane = new JScrollPane(msgTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
  			                                       JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  	  msgScrollPane.setBorder(BorderFactory.createTitledBorder("Server Messages"));
  	  
  	  this.getContentPane().setLayout(new BorderLayout());
  	  this.getContentPane().add(mainPanel, BorderLayout.CENTER);
  	  this.getContentPane().add(msgScrollPane, BorderLayout.SOUTH );
    }

    /**
     * Button responses for all five of the panels in the program.
     * 
     * Checks to make sure the appropriate fields are populated in the panel in which
     *  the button click occurs.  Also, gets the information back from the server and
     *  displays it in the JTextFields and/or the Bottom message box.  
     */
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
	  if (ae.getSource().equals(btnAddCust))
	  {
		// Make sure the  name, SSN, address, and code fields are populated.
		if ( tfNameAP.getText().equals("") || tfSSNAP.getText().equals("") ||
			 tfaddressAP.getText().equals("") || tfzipCodeAP.getText().equals("") )
		{
		  JOptionPane.showMessageDialog(this, "Please enter all fields for the customer"
		    + " information you wish to add.", "Data Missing", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
		   // Add the customer.  Get their information from the GUI, and add it to a Message.
		   Customer cust = new Customer(tfNameAP.getText(), tfSSNAP.getText(),
				                        tfaddressAP.getText(), Integer.valueOf(tfzipCodeAP.getText()) );
		   
		   // Create a message to send to the server.
		   Message msg = new Message(Message.ADD, cust);
		   msg = sendMessage(msg); // Send our request and get back the data.
		   
		   // Process the returned message.
		   if (msg.getMessage().contains("Duplicate") )
			 Assign6GUI.addMessage("Error: \"" + cust.getName() + "\" not added because it is already in the table: " + msg.getMessage());	
		   else
		     Assign6GUI.addMessage(msg.getMessage());		   	   
		}			  
	  }
	  else if (ae.getSource().equals(btnUpdateCust))
	  {
	    // Make sure the address and SSN fields are entered.
		if ( tfSSNUP.getText().equals("") || tfaddressUP.getText().equals("") )
		  JOptionPane.showMessageDialog(this, "Please enter both fields for the customer's"
		    + " address you wish to update.", "Data Missing", JOptionPane.ERROR_MESSAGE);
	    else
		{
		   // Update the customer's address.  NOTE: we only need the SSN and address.
		   Customer cust = new Customer("", tfSSNUP.getText(), tfaddressUP.getText(), 0 );
			   
		   // Create a message to send to the server.
		   Message msg = new Message(Message.UPDATE, cust);
		   msg = sendMessage(msg); // Send our request and get back the data.
			   
		   // Process the returned message.
		   if (msg.getMessage().contains("Error") )
			 Assign6GUI.addMessage(msg.getMessage());	
		   else
		     Assign6GUI.addMessage(msg.getMessage());	
		}			  
	  }
	  else if (ae.getSource().equals(btnSearch))
	  {
		// Make sure the SSN field is entered.
		if ( tfSSNSP.getText().equals(""))
		  JOptionPane.showMessageDialog(this, "Please enter a SSN for the customer"
		    + " you wish to search for.", "No SSN Entered", JOptionPane.ERROR_MESSAGE);
		else
		{
		  // Search for the customer's SSN.  NOTE: we only need the SSN to find a customer.
		  Customer cust = new Customer("", tfSSNSP.getText(), "", 0 );
				   
		  // Create a message to send to the server.
		  Message msg = new Message(Message.SEARCH, cust);
		  msg = sendMessage(msg); // Send our request and get back the data.
				   
		  // Process the returned message.
		  if (msg.getMessage().contains("Error") )
		    Assign6GUI.addMessage(msg.getMessage());	
		  else
		  {
			tfNameSP.setText(msg.getCustomer().getName());
			tfaddressSP.setText(msg.getCustomer().getAddress()); 
			tfzipCodeSP.setText(Integer.toString(msg.getCustomer().getCode()));
			  
		    Assign6GUI.addMessage(msg.getMessage());		
		  }
		}		  
	  }
	  else if (ae.getSource().equals(btnDeleteCust))
	  {
		if ( tfSSNDP.getText().equals(""))
		  JOptionPane.showMessageDialog(this, "Please enter a SSN for the customer"
		    + " you wish to delete.", "No SSN Entered", JOptionPane.ERROR_MESSAGE);
		else
		{
		  // Search for the customer's SSN to try and delete them.
		  Customer cust = new Customer("", tfSSNDP.getText(), "", 0 );
					   
		  // Create a message to send to the server.
		  Message msg = new Message(Message.DELETE, cust);
		  msg = sendMessage(msg); // Send our request and get back the data.	
		  
		  // Process the returned message.
		  Assign6GUI.addMessage(msg.getMessage());	
		}
	  }
	  else if (ae.getSource().equals(btnShowAllCust))
	  {
        // Show all.
	    try
		{
		  s = new Socket(HOST, PORT);

	      out = new ObjectOutputStream(s.getOutputStream());
	      out.flush();
	      in = new ObjectInputStream(s.getInputStream());

	      // Send a message to show all the customers.  We don't care about and Customer information.
	      Message msg = new Message(Message.SHOW_ALL, null); 
	      
	      out.writeObject(msg);
	      out.flush();
	        
	      try
	      {
	    	taShowAll.setText("");
	    	
	    	Customer cust = (Customer) in.readObject();
	        while ( cust != null)
	        {
              taShowAll.append(cust.getName() + ", "
            		         + cust.getSSN() + ", "
            		         + cust.getAddress() + ", "
            		         + cust.getCode() + "\n");
              
              cust = (Customer) in.readObject();
	        }
	      }
	      catch (EOFException e) { } // Data should all be displayed.
	      
	      in.close();
	      out.close();
	 
		}
		catch (ClassNotFoundException e1) 
		{
		  Assign6GUI.addMessage("Error: Unable to connect to server.");
		}
		catch (IOException e2)
		{
		  Assign6GUI.addMessage("Unable to read serial object: " + e2.getMessage());  
		}  
	  }
	}
	
	/**
	 * Adds a Message to the server message JTextArea and scrolls to the bottom.
	 * @param msg
	 */
	public static void addMessage(String msg)
	{
	  Date date = new Date();
	  msgTextArea.append(date.toString() + " --> " + msg + "\n");
	  msgTextArea.setCaretPosition(msgTextArea.getText().length());	
	} 
}//End class Assign6GUI
