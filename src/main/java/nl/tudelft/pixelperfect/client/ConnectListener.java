package nl.tudelft.pixelperfect.client;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;;
/**
 * The class listens to any ongoing connectivity between the server and client.
 * @author Dmitry
 *
 */
public class ConnectListener implements ConnectionListener {

  /**
   * What the server should do if a client connects.
   * 
   * @param server The server it refers to.
   * @param client The client that's trying to connect.
   */
  public void connectionAdded(Server server, HostedConnection client) {
    System.out.println("Client " + client + " has connected to the game");
    
  }
  
  /**
   * What the server should do if a client disconnects.
   * 
   * @param server The server it refers to.
   * @param client The client that's trying to disconnect.
   */
  public void connectionRemoved(Server server, HostedConnection client) {
    System.out.println("Client " + client + " has disconnected from the game");
    
  }

}
