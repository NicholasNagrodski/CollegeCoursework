package assign6;

import java.io.Serializable;

public class Customer implements Serializable
{
  private static final long serialVersionUID = -5588328138901202071L;
  
  protected  String name;
  protected  String ssn;
  protected  String address;
  protected  int code;
  
  public Customer(String name, String ssn, String address, int code)
  {
	this.name = name;
	this.ssn = ssn;
	this.address = address;
	this.code = code;
  }
  
  public String getName()
  { return name; }
  public String getSSN()
  { return ssn; }
  public String getAddress()
  { return address; }
  public int getCode()
  { return code; }
  
  public void setAddress(String address)
  { this.address = address; }
}
