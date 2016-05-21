package nl.tudelft.pixelperfect.player;

/**
 * The Engineer is a role that the CrewMember can choose. The Engineer takes care of the engine
 * and other technical parts of the ship.
 * 
 * @author Floris Doolaard
 */
public class Engineer extends CrewPlayer {

  /**
   * The constructor for the Engineer.
   * 
   * @param name
   *          , a String of a name.
   */
  public Engineer(String name) {
    super(name);
  }

  /**
   * Compares two Engineer players to see if they're the same.
   * 
   * @param that
   *          The object to compare.
   * 
   * @return whether it's the same or not.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof Engineer) {
      Engineer other = (Engineer) that;
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
    return 42;
  }

}
