package nl.tudelft.pixelperfect.client;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

/**
 * Listener for the Game's server, which handle incoming messages.
 * 
 * @author Jesse Tilro
 * @author Dmitry Malarev
 *
 */
public class ServerListener implements MessageListener<HostedConnection> {

  /**
   * What it should do if it received a message.
   */
  public void messageReceived(HostedConnection source, Message message) {
    if (message instanceof HelloMessage) {
      // do something with the message
      HelloMessage helloMessage = (HelloMessage) message;
      System.out.println(
          "Server received '" + helloMessage.getSomething() + "' from client #" + source.getId());
    } // else....
  }

}
