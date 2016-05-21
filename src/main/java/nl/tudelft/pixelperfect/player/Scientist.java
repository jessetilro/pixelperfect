package nl.tudelft.pixelperfect.player;

/**
 * The Scientist is a role that the CrewMember can choose. The Scientist tries to upgrade the ship
 * and make solutions for mysterious quests.
 * 
 * @author Floris Doolaard
 */
public class Scientist extends CrewPlayer {

  public Scientist(String name) {
    super(name);
  }

  /**
   * Compares two Scientist players to see if they're the same.
   * 
   * @param that
   *          The object to compare.
   * 
   * @return whether it's the same or not.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof Scientist) {
      Scientist other = (Scientist) that;
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
