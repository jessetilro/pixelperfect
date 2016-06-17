package nl.tudelft.pixelperfect.client.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Kill the current session by ordering all connected clients to disconnect. This allows the game
 * session to be restarted without the need to reboot the applications.
 *
 * @author Jesse Tilro
 */
@Serializable
public class DisconnectMessage extends AbstractMessage {

  /**
   * The empty constructor for the DisconnectMessage.
   */
  public DisconnectMessage() {
  }
}
