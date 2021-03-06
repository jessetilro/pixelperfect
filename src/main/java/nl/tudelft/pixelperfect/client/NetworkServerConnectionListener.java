package nl.tudelft.pixelperfect.client;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.player.CrewPlayer;

/**
 * The class listens to any ongoing connectivity between the server and client.
 * 
 * @author David Alderliesten
 * @author Dmitry Maralev
 *
 */
public class NetworkServerConnectionListener implements ConnectionListener {

  private Game app;

  /**
   * Sets the game whose server to listen for.
   * 
   * @param game
   *          The game.
   */
  public void setGame(Game game) {
    app = game;
  }

  /**
   * Returns the game for reference purposes.
   * 
   * @return The game.
   */
  public Game getGame() {
    return app;
  }

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
    app.getSpaceship().getCrew().addPlayer(new CrewPlayer(client));
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
    app.getSpaceship().getCrew().removePlayerByConnection(client);
  }
}
