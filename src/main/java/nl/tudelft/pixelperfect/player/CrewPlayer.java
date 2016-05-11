package nl.tudelft.pixelperfect.player;

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
  public CrewPlayer(String name) {
    super(name);
  }

  /**
   * Compares two CrewPlayers to see if they're the same.
   * 
   * @param that The object to compare.
   * 
   *@return whether it's the same or not.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof CrewPlayer) {
      CrewPlayer other = (CrewPlayer) that;
      return (other.getName() == this.getName());
    }
    return false;
  }
}
