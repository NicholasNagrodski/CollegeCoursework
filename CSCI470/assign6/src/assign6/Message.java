package assign6;

import java.io.Serializable;

public class Message implements Serializable
{
  private static final long serialVersionUID = 8068799993417501767L;
  
  public static final int ADD = 0;
  public static final int UPDATE = 1;
  public static final int SEARCH = 2;
  public static final int DELETE = 3;
  public static final int SHOW_ALL = 4;	
	
  /**
   * The action to perform for this message (delete, update, add, ...)
   */
  protected int action;
  
  /**
   * The customer data we are using.
   */
  protected Customer cust;
  
  /**
   * Info that gets returned to the user.
   */
  protected String returnMsg;
	
  public Message(int action, Customer cust)
  {
	this.action = action;
	this.cust = cust;
  }
  
  public int getActionMsg()
  { return action; }
  public Customer getCustomer()
  { return cust; } 
  public String getMessage()
  { return returnMsg; }
  
  public void setMessage(String msg)
  { this.returnMsg = msg; }
  public void setCustomer(Customer cust)
  { this.cust = cust; }
}
