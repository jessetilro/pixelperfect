package nl.tudelft.pixelperfect.client.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import nl.tudelft.pixelperfect.player.PlayerRoles;

/**
 * Whenever a role is chosen, the other crew players cannot choose the role. Therefor this message
 * is needed to broadcast to everyone.
 *
 * @author Floris Doolaard
 */
@Serializable
public class RoleChosenMessage extends AbstractMessage {
  private PlayerRoles role;
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
  public RoleChosenMessage(PlayerRoles role, boolean allocated) {
    this.role = role;
    this.allocated = allocated;
  }

  /**
   * Returns the role chosen as an Enum.
   *
   * @return role as an Enum.
   */
  public PlayerRoles getRole() {
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
