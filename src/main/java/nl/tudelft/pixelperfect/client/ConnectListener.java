package nl.tudelft.pixelperfect.client;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

import nl.tudelft.pixelperfect.Game;

/**
 * The class listens to any ongoing connectivity between the server and client.
 * @author Dmitry
 *
 */
public class ConnectListener implements ConnectionListener {
  
  private Game app;
  
  /**
   * Sets the game it references to.
   * 
   * @param game the game.
   */
  public void setGame(Game game) {
    app = game;
  }
  
  /**
   * Returns the game for reference purposes.
   * 
   * @return the game.
   */
  public Game getGame() {
    return app;
  }

  /**
   * What the server should do if a client connects.
   * 
   * @param server The server it refers to.
   * @param client The client that's trying to connect.
   */
  public void connectionAdded(Server server, HostedConnection client) {
    System.out.println("Client " + client + " has connected to the game");
    app.getSpaceship().updateCrew(server.getConnections());
  }
  
  /**
   * What the server should do if a client disconnects.
   * 
   * @param server The server it refers to.
   * @param client The client that's trying to disconnect.
   */
  public void connectionRemoved(Server server, HostedConnection client) {
    System.out.println("Client " + client + " has disconnected from the game");
    app.getSpaceship().updateCrew(server.getConnections());
  }

}
