package nl.tudelft.pixelperfect.client.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * This message will notify all the clients that the game should start with the current connections.
 * The message requires no implementation, because the message itself is already enough information.
 *
 * @author Floris Doolaard
 */
@Serializable
public class NewGameMessage extends AbstractMessage {

  /**
   * The empty constructor for the NewGameMessage.
   */
  public NewGameMessage() {
  }
}