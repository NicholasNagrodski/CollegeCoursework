/* Multithreaded Server program. It uses a ServerSocket class to accept new
connections from clients. When a client connects to the port that the
ServerSocket is listening on, the ServerSocket allocates a new Socket object
for the client to communicate through.  The server can then go back to
listening on the ServerSocket for additional connections.
This server is multithreaded. The Server object itself is a thread. Its run()
method loops forever, listening for connections from clients. Each time a
client connects, the ServerSocket creates a new Socket and the server 
creates a new Thread (Conversation object here). The Conversation object 
handles all communication with the client. The constructor for the Conversation
object initializes streams for communication through the Socket object and
starts the Thread running. The run() method of the Conversation object does
all communication with the client and performs the "service" that this server 
provides.  In this case, it simply reads a line of text from the client,
reverses it, and sends it back.
From Java in a Nutshell, 1st Ed., O'Reilly
*/

package assign6;

import java.io.*;
import java.net.*;

public class Server extends Thread
{
  public final static int DEFAULT_PORT = 9728;
  protected int port;
  protected ServerSocket listen_socket;
  
  // Constructor -----------------------------------------------
  // Create a ServerSocket to listen for connections.
  // Start the thread.
  public Server (int aport)
  {
    if (aport == 0)
      aport = DEFAULT_PORT;
    
    this.port = aport;
    
    try
    {
      listen_socket = new ServerSocket(port);
    }
    catch (IOException e)
    {
      fail(e, "Exception creating server socket");
    }
    
    System.out.println("Server listening on port " + port);
    this.start();
  }
      
  // fail ------------------------------------------------------
  // Exit with an error message when an exception occurs
  
  public static void fail(Exception e, String msg)
  {
    System.err.println(msg + ": " + e);
    System.exit(1);
  }
  
  // run -------------------------------------------------------
  // The body of the server thread. Loop forever, listening for and
  // accepting connections from clients. For each connection, create a
  // new Conversation object to handle the communication through the
  // new Socket.
  
  public void run()
  {
    try
    {
      while (true)
      {
        Socket client_socket = listen_socket.accept(); 
        
        // create a Conversation object to handle this client and pass
        // it the Socket to use.  If needed, we could save the Conversation
        // object reference in a Vector. In this way we could later iterate
        // through this vector looking for "dead" connections and reclaim
        // any resources.
        new Conversation(client_socket);
      }
    }
    catch (IOException e)
    {
      fail(e, "Exception listening for connections");
    }
  }
  
  // main-------------------------------------------------------
  // Start up the Server program telling it to use command line
  // port number, or set port number to zero if not specified.
  public static void main (String args[])
  {
    int port = 0;
    if (args.length == 1)   // user passed different port to use
    {
      try
      {
      	port = Integer.parseInt(args[0]);
      }
      catch (NumberFormatException e)
      {
      	port = 0;          
      }
    }
    
    new Server(port);
  }
} // end Server