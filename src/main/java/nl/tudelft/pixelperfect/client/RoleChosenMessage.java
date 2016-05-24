package nl.tudelft.pixelperfect.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Whenever a role is chosen, the other crew players cannot choose the role. Therefor this message
 * is needed to broadcast to everyone.
 *
 * @author Floris Doolaard
 */
@Serializable
public class RoleChosenMessage extends AbstractMessage {
  private int role;
  private String label;

  /**
   * The empty Constructor.
   */
  public RoleChosenMessage() {

  }

  /**
   * The constructor for creating RoleChosen messages.
   *
   * @param label
   *          , the label of the chosen role as a String.
   * @param role
   *          , the role in terms of an integer.
   */
  public RoleChosenMessage(String label, int role) {
    this.role = role;
    this.label = label;
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
   * Returns the role chosen as an integer.
   *
   * @return role as an Integer.
   */
  public int getRole() {
    return role;
  }

}
