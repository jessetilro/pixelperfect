package nl.tudelft.pixelperfect.player;

import com.jme3.network.HostedConnection;

import nl.tudelft.pixelperfect.event.Event;

/**
 * A player fulfilling a role aboard of the spaceship while playing the game.
 * 
 * @author Jesse Tilro
 *
 */
public abstract class Player {

  private HostedConnection connection;
  private String name;
  private PlayerRoles role;

  /**
   * Construct a new Player instance.
   * 
   * @param connection
   *          Connection to the player.
   */
  public Player(HostedConnection connection) {
    this.connection = connection;
  }

  /**
   * Get the connection through which this player is connected to the server.
   * 
   * @return The HostedConnection
   */
  public HostedConnection getConnection() {
    return connection;
  }

  /**
   * Get the Player's name.
   * 
   * @return The player's name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Set the Player's name.
   * 
   * @param name
   *          The player's name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the role assigned to this player.
   * 
   * @return The role assigned.
   */
  public PlayerRoles getRole() {
    return role;
  }

  /**
   * Assign this player a given role.
   * 
   * @param role
   *          The role to be assigned.
   */
  public void assignRole(PlayerRoles role) {
    this.role = role;
  }

  /**
   * Check whether this Player is able to solve a specific Event (based on whether the Player's role
   * is able to solve the type of Event).
   * 
   * @return Whether this Player can solve an Event.
   */
  public boolean canSolveEvent(Event event) {
    return (role != null && role.canSolveEventType(event.getType()));
  }

  /**
   * Get a String representation of the name this Player has.
   * 
   * @return The name.
   */
  private String describeName() {
    if (name != null && name != "") {
      return name;
    } else {
      return "Anonymous Player";
    }
  }

  /**
   * Get a String representation of the role this Player has.
   * 
   * @return The role.
   */
  private String describeRole() {
    if (role != null) {
      return role.toString();
    } else {
      return "choosing a role";
    }
  }

  /**
   * Get a String representation of this player, describing his/her name and role.
   * 
   * @return A String representation of this Player.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(describeName()).append(" is ").append(describeRole());
    return sb.toString();
  }

  /**
   * Compares two Players to see if they're the same.
   * 
   * @param that
   *          The object to compare.
   * 
   * @return whether it's the same or not.
   */
  @Override
  public abstract boolean equals(Object that);

  /**
   * Class not likely to be used in a hashMap/Table.
   * 
   * @return An unused number.
   */
  @Override
  public abstract int hashCode();

}