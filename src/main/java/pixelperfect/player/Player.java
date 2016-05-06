package pixelperfect.player;

/**
 * A player fulfilling a role aboard of the spaceship while playing the game.
 * 
 * @author Jesse Tilro
 *
 */
public abstract class Player {

  private String name;

  /**
   * Construct a new Player instance.
   * 
   * @param name
   *          The player's name.
   */
  public Player(String name) {
    this.name = name;
  }

  /**
   * Get the Player's name.
   * 
   * @return The player's name.
   */
  public String getName() {
    return this.name;
  }

}
