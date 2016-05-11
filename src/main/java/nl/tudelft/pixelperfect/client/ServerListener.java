package nl.tudelft.pixelperfect.client;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

/**
 * Listener for the Game's server, which handle incoming messages.
 * 
 * @author David Alderliesten
 * @author Dmitry Malarev
 * @author Jesse Tilro
 *
 */
public class ServerListener implements MessageListener<HostedConnection> {

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
    }
  }
}
