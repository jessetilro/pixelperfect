package nl.tudelft.pixelperfect.client;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;;

public class ConnectListener implements ConnectionListener {

  public void connectionAdded(Server server, HostedConnection client) {
    System.out.println("Client " + client + " has connected to the game");
    
  }

  public void connectionRemoved(Server server, HostedConnection client) {
    System.out.println("Client " + client + " has disconnected from the game");
    
  }

}
