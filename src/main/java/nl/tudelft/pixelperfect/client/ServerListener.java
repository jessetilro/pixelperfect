package nl.tudelft.pixelperfect.client;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

import nl.tudelft.pixelperfect.Game;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventLog;

import java.util.ArrayList;

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
   * Functionality for server behavior upon receiving a message.
   * 
   * @param source
   *          The client that sent the message.
   * @param message
   *          The message sent.
   * 
   */
  public void messageReceived(HostedConnection source, Message message) {
    // TODO add else clause and correctly parse message.
    if (message instanceof HelloMessage) {
      HelloMessage helloMessage = (HelloMessage) message;
      System.out.println(
          "Server received '" + helloMessage.getSomething() + "' from client #" + source.getId());
    } else if (message instanceof EventCompletedMessage) {
      EventCompletedMessage eve = (EventCompletedMessage) message;
      System.out.println("Received a completed event: " + eve.getCompletedEvent());
    }
  }
}
