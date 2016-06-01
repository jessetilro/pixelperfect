package nl.tudelft.pixelperfect.client;

import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Server;

import nl.tudelft.pixelperfect.client.message.EventCompletedMessage;
import nl.tudelft.pixelperfect.client.message.RoleChosenMessage;
import nl.tudelft.pixelperfect.game.Game;

/**
 * Listener for the Game's server, which handles incoming messages.
 * 
 * @author David Alderliesten
 * @author Dmitry Malarev
 * @author Jesse Tilro
 *
 */
public class ServerListener implements MessageListener<HostedConnection> {

  private Game app;
  private Server server;

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
   * Uses the server of the game to broadcast messages that has come in here.
   * 
   * @param server
   *          , the server of the game.
   */
  public synchronized void setServer(Server server) {
    this.server = server;
  }

  /**
   * Functionality for server behavior upon receiving a message.
   * 
   * @param source
   *          The client that sent the message.
   * @param message
   *          The message sent.
   * 
   */
  public synchronized void messageReceived(HostedConnection source, Message message) {
    if (message instanceof EventCompletedMessage) {
      EventCompletedMessage eve = (EventCompletedMessage) message;
      System.out.println("Received a completed event: " + eve.getLabel());
      app.getSpaceship().getLog().complete(eve.getCompletedEvent());
    } else if (message instanceof RoleChosenMessage) {
      server.broadcast(Filters.notEqualTo(source), message);
    }
  }
}
