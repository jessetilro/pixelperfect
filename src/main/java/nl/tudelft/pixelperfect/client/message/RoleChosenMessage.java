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
  private boolean allocated;

  /**
   * The empty Constructor.
   */
  public RoleChosenMessage() {
  }

  /**
   * The constructor for creating RoleChosen messages.
   *
   * @param role
   *          The Role.
   * @param allocated
   *          Allocated.
   */
  public RoleChosenMessage(Roles role, boolean allocated) {
    this.role = role;
    this.allocated = false;
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
   * Check whether the role was allocated or not.
   * 
   * @return Allocated.
   */
  public boolean isAllocated() {
    return allocated;
  }

}
