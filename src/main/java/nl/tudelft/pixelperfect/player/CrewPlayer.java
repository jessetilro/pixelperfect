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

}
