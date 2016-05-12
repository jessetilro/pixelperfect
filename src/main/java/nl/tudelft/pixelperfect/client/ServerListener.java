package nl.tudelft.pixelperfect.client;

import java.util.ArrayList;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

import nl.tudelft.pixelperfect.Game;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventLog;

/**
 * Listener for the Game's server, which handle incoming messages.
 * 
 * @author David Alderliesten
 * @author Dmitry Malarev
 * @author Jesse Tilro
 *
 */
public class ServerListener implements MessageListener<HostedConnection> {
  
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
   * Functionality for server behavior upon recieving a message.
   * 
   * @param source
   *          The client the sends the message.
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
    } else if (message instanceof EventsMessage) {
      EventsMessage eve = (EventsMessage) message;
      ArrayList<Event> log = eve.getLog();
      EventLog curr = (EventLog) app.getSpaceship().getLog();
      curr.replace(log);
      
    }
  }
}
