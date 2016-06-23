package nl.tudelft.pixelperfect.client.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import nl.tudelft.pixelperfect.player.PlayerRoles;

/**
 * Whenever a role is chosen, the other crew players cannot choose the role. This message is used
 * for requesting and confirming / denying role allocation of a specific role to a specific player.
 *
 * @author Floris Doolaard
 * @author Jesse Tilro
 */
@Serializable
public class RoleAllocationMessage extends AbstractMessage {
  private int role;
  private boolean allocated;

  /**
   * The empty Constructor.
   */
  public RoleAllocationMessage() {
  }

  /**
   * The constructor for creating RoleChosen messages.
   *
   * @param role
   *          The Role.
   * @param allocated
   *          Allocated.
   */
  public RoleAllocationMessage(PlayerRoles role, boolean allocated) {
    this.role = role.ordinal();
    this.allocated = allocated;
  }

  /**
   * Returns the role chosen as an Enum.
   *
   * @return role as an Enum.
   */
  public PlayerRoles getRole() {
    return PlayerRoles.values()[role];
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
