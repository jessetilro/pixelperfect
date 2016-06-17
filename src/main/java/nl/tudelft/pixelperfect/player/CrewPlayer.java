package nl.tudelft.pixelperfect.player;

import com.jme3.network.HostedConnection;

/**
 * A player who is fulfilling of a crew member, interacting with the system remotely over a network
 * interface.
 * 
 * @author Jesse Tilro
 *
 */
public class CrewPlayer extends Player {

  /**
   * Construct a new CrewPlayer instance.
   * 
   * @param name
   *          The player's name.
   */
  public CrewPlayer(HostedConnection connection) {
    super(connection);
  }

  /**
   * Compares two CrewPlayers to see if they're the same.
   * 
   * @param that
   *          The object to compare.
   * 
   * @return whether it's the same or not.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof CrewPlayer) {
      CrewPlayer other = (CrewPlayer) that;
      return getConnection().equals(other.getConnection());
    }
    return false;
  }

  /**
   * Class not likely to be used in a hashMap/Table.
   * 
   * @return An unused number.
   */
  @Override
  public int hashCode() {
    return 42;
  }
}
