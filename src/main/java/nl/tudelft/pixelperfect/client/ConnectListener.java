package nl.tudelft.pixelperfect.client;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

/**
 * The class listens to any ongoing connectivity between the server and client.
 * 
 * @author David Alderliesten
 * @author Dmitry Maralev
 *
 */
public class ConnectListener implements ConnectionListener {

  /**
   * Functionality that occurs when a client is added to the server.
   * 
   * @param server
   *          The server it refers to.
   * @param client
   *          The client that is trying to connect.
   */
  public void connectionAdded(Server server, HostedConnection client) {
    System.out.println("Client " + client + " has connected to the game");
  }

  /**
   * Functionality that occurs when a client disconnects from the server.
   * 
   * @param server
   *          The server it refers to.
   * @param client
   *          The client that is trying to disconnect.
   */
  public void connectionRemoved(Server server, HostedConnection client) {
    System.out.println("Client " + client + " has disconnected from the game");

  }
}
