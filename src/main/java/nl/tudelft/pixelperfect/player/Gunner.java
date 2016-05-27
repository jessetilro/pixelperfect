package nl.tudelft.pixelperfect.player;

/**
 * The Gunner is role to choose as a crewplayer. The Gunner takes care of taking down enemy ships.
 * 
 * @author Floris Doolaard
 */
public class Gunner extends CrewPlayer {

  /**
   * The Constructor for the Gunner.
   * 
   * @param name
   *          , a String as a name.
   */
  public Gunner(String name) {
    super(name);
  }

  /**
   * Compares two Gunner players to see if they're the same.
   * 
   * @param that
   *          The object to compare.
   * 
   * @return whether it's the same or not.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof Gunner) {
      return super.equals(that);
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
