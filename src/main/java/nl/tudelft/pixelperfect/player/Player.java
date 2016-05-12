package nl.tudelft.pixelperfect.player;

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
  
  /**
   * Compares two Players to see if they're the same.
   * 
   * @param that The object to compare.
   * 
   *@return whether it's the same or not.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof Player) {
      Player other = (Player) that;
      return (other.getName().equals(this.getName()));
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
    assert false : "hashCode not designed";
    return 42;
  }
}