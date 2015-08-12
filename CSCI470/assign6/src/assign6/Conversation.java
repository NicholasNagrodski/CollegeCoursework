package assign6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//**************************************************************
//This class is the Thread that handles all communication with
//the client
class Conversation extends Thread
{
  protected Socket             client;
  protected ObjectInputStream  in;
  protected ObjectOutputStream out;
  
  protected Connection dbConn;

  // Constructor -----------------------------------------------
  // Initialize the streams and start the thread

  public Conversation(Socket client_socket) 
  {
    client = client_socket;
    try
    {
      out = new ObjectOutputStream(client.getOutputStream());
      out.flush();
    }
    catch(IOException e)
    {
      try
      {
      	client.close();
      }
      catch (IOException e2) {}
        System.out.println("Exception getting socket streams " + e);
      return;
    }
    
    // Get the Database connection.
    try
    {  	
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      dbConn = DriverManager.getConnection("jdbc:mysql://courses:3306/JavaCust27");
    }
    catch (Exception e) 
    {
      System.out.println(e.getMessage());
    } 
 
    // start the run loop
    this.start();
  }

  // run -------------------------------------------------------
  // Provide the service. Read a message from the user.  Perform the desired command.
  public void run()
  {
	try
	{
	  // Read in the Message object from the client.
	  // Execute the command on the server and return data through 'out' if needed.
	  in = new ObjectInputStream(client.getInputStream());
      
	  Message msg = (Message) in.readObject();
	  
	  processMessage(msg); 
	}
	catch (IOException e1) {}
	catch (ClassNotFoundException e2) {}
	finally
	{
	  try
	  {
		dbConn.close();
	    client.close();
	  }
	  catch (IOException e1) {}
	  catch (SQLException e2) {}
    }  
  } // end run
  
  /**
   * Call the appropriate code to process an action.
   */
  private void processMessage(Message msg)
  {
	// Invoke the appropriate action on each message.
	// Each function returns a string as to the return status of the action.
    switch(msg.getActionMsg())
    {
      case Message.ADD:      msg.setMessage(addCustomer(msg.getCustomer()));    break;
      case Message.UPDATE:   msg.setMessage(updateCustomer(msg.getCustomer())); break;
      case Message.SHOW_ALL: msg.setMessage(showAllCustomers());                return; // This need to write >1 objects. Fcn takes care of it.
      case Message.DELETE:   msg.setMessage(deleteCustomer(msg.getCustomer())); break;
      case Message.SEARCH:   msg = searchCustomer(msg);                         break;
    }  
  
    // Write the processed customer back to the client.
    try 
    {
	  out.writeObject(msg);
	} 
    catch (IOException e) 
	{
	  System.out.println(e.getMessage());
	}
  }
  
  /**
   * Adds a customer to the database and returns a message about the success.
   * @param cust
   * @return
   */
  private String addCustomer(Customer cust) 
  {
	PreparedStatement st = null;
	
	try
	{
	  st = dbConn.prepareStatement("INSERT INTO cust VALUES(?, ?, ?, ?)");
	  st.setString(1, cust.getName());
	  st.setString(2, cust.getSSN());
	  st.setString(3, cust.getAddress());
	  st.setInt(4, cust.getCode() );
	  
	  if (st.executeUpdate() > 0)
	  {
		 return "Customer \"" + cust.getName() + "\" has been added successfully.";
	  }
	  else
		return "Data was not successfully added";
	}
	catch (SQLException e)
	{
	  return e.getMessage();
	}
	finally 
	{
	  try
	  {
	    st.close();
	  }
	  catch (SQLException e) 
	  {
		 return "Error adding Customer: " + cust.getName();  
	  }
	}
  }
  
  private String updateCustomer(Customer cust) 
  { 
	PreparedStatement pstmnt = null;
	
	try
	{
	  pstmnt = dbConn.prepareStatement("UPDATE cust SET address = ? WHERE ssn = ?");
	  pstmnt.setString(1, cust.getAddress());	  
	  pstmnt.setString(2, cust.getSSN());

      // If there is a valid entry was updated.		  
	  if (pstmnt.executeUpdate() > 0)
		 return "Customer has been updated successfully!";
	  else
		return "Error: Customer \"" + cust.getSSN() + "\" was not found.";
	}
	catch (SQLException e)
	{
	  return "Error updating Customer: " + e.getMessage();
	}
	finally 
	{
	  try
	  {
		pstmnt.close();
	  }
	  catch (SQLException e) 
	  {
		 return "Error updating Customer: " + e.getMessage();  
	  }
	}	  
  }
  
  private Message searchCustomer(Message msg)
  { 
	PreparedStatement pstmnt = null;
		
	try
	{
	  pstmnt = dbConn.prepareStatement("SELECT * FROM cust WHERE ssn = ?");
	  pstmnt.setString(1, msg.getCustomer().getSSN());
	  ResultSet rs = pstmnt.executeQuery();

      // If there is a valid row returned.		  
	  if (rs.first())
	  {
		msg.setMessage("Customer \"" + rs.getString("name") + "\" found!");
		
		Customer cust = new Customer(rs.getString("name"), rs.getString("ssn"), rs.getString("address"), rs.getInt("code"));
		
		msg.setCustomer(cust);
		return msg;
	  }
	  else
	  {
		msg.setMessage("Error: Customer \"" + msg.getCustomer().getSSN() + "\" was not found!");
		return msg;
	  }
	}
	catch (SQLException e)
	{
	  msg.setMessage("Error in Search Customer");
	  return msg;
	}
	finally 
	{
	  try
	  {
		pstmnt.close();
	  }
	  catch (SQLException e) 
	  {
		  msg.setMessage("Error in Search Customer");
		  return msg;
	  }
	}
  }
  
  /**
   * Deletes a customer based on their SSN.
   * @param cust
   * @return
   */
  private String deleteCustomer(Customer cust) 
  { 
	PreparedStatement pstmnt = null;
	
	try
	{
	  pstmnt = dbConn.prepareStatement("DELETE FROM cust WHERE ssn = ?");
	  pstmnt.setString(1, cust.getSSN());

	  // If there the Customer related to the SSN has been deleted.		  
	  if (pstmnt.executeUpdate() > 0)
	  {
		return "Customer \"" + cust.getSSN() + "\" delted!";
	  }
	  else
	  {
		return "Error: unable to delete " + cust.getName() + ".";
	  }
	}
	catch (SQLException e)
	{
	  return "Error: unable to delete " + cust.getName() + ".";
	}
	finally 
	{
	  try
	  {
		pstmnt.close();
	  }
	  catch (SQLException e) 
	  {
		return "Error: unable to delete " + cust.getName() + ".";
	  }
	}
  }  
  /**
   * Returns all of the customers to the client GUI.
   * @return
   */
  private String showAllCustomers() 
  { 
	PreparedStatement pstmnt = null;
		
	try
	{
	  pstmnt = dbConn.prepareStatement("SELECT * FROM cust");
	  ResultSet rs = pstmnt.executeQuery();

	  // Write all of the data to the client.		  
	  while (rs.next())
 	  {
		Customer cust = new Customer(rs.getString("name"), rs.getString("ssn"), rs.getString("address"), rs.getInt("code"));  	  
		out.writeObject(cust);
	  }
	}
	catch (SQLException e)
	{
	  return "Error showing all customers.";
	} 
	catch (IOException e) 
	{
	  return "Error showing all customers.";
	}
	finally 
	{
	  try
	  {
		pstmnt.close();
	  }
	  catch (SQLException e) 
	  {
		return "Error showing all customers.";
	  }
	}
	
	return "All customers successfully displayed";
  }
} // end Conversation