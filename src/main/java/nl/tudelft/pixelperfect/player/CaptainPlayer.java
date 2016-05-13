package nl.tudelft.pixelperfect.player;

/**
 * The player fulfilling the role of captain, controlled by a player directly interacting with the
 * system.
 * 
 * @author Jesse Tilro
 *
 */
public class CaptainPlayer extends Player {

  /**
   * Construct a new CaptainPlayer instance.
   * 
   * @param name
   *          The player's name.
   */
  public CaptainPlayer(String name) {
    super(name);
  }

  /**
   * Compares two CaptainPlayers to see if they're the same.
   * 
   * @param that
   *          The object to compare.
   * 
   * @return whether it's the same or not.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof CaptainPlayer) {
      CaptainPlayer other = (CaptainPlayer) that;
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
