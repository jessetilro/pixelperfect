package nl.tudelft.pixelperfect.client.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import nl.tudelft.pixelperfect.game.Roles;

/**
 * Whenever a role is chosen, the other crew players cannot choose the role. Therefor this message
 * is needed to broadcast to everyone.
 *
 * @author Floris Doolaard
 */
@Serializable
public class RoleChosenMessage extends AbstractMessage {
  private Roles role;
  private String label;
  private Boolean isEmpty;

  /**
   * The empty Constructor.
   */
  public RoleChosenMessage() {
    this.isEmpty = true;
  }

  /**
   * The constructor for creating RoleChosen messages.
   *
   * @param label
   *          , the label of the chosen role as a String.
   * @param role
   *          , the role in terms of an Enum.
   */
  public RoleChosenMessage(String label, Roles role) {
    this.role = role;
    this.label = label;
    this.isEmpty = false;
  }

  /**
   * Returns the label of the chosen role.
   *
   * @return a String.
   */
  public String getLabel() {
    return label;
  }

  /**
   * Returns the role chosen as an Enum.
   *
   * @return role as an Enum.
   */
  public Roles getRole() {
    return role;
  }

  /**
   * Returns whether the message is empty or not. If the message is empty it is likely to be used
   * for sending back all roles chosen.
   * 
   * @return a boolean.
   */
  public Boolean isEmpty() {
    return isEmpty;
  }

}
