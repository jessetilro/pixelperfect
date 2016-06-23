package nl.tudelft.pixelperfect.client.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * This message is used to communicate player details.
 *
 * @author Jesse Tilro
 */
@Serializable
public class PlayerDetailsMessage extends AbstractMessage {

  private String name;

  /**
   * The empty Constructor.
   */
  public PlayerDetailsMessage() {
  }

  /**
   * The constructor for a PlayerDetailsMessage for communicating the given name.
   *
   * @param role
   *          The Role.
   * @param allocated
   *          Allocated.
   */
  public PlayerDetailsMessage(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the player who sent this message.
   *
   * @return The player's name.
   */
  public String getName() {
    return name;
  }

}
