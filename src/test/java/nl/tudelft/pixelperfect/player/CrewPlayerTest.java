package nl.tudelft.pixelperfect.player;

/**
 * Test Suite for the CrewPlayer class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class CrewPlayerTest extends PlayerTest {

  /**
   * Create a CrewPlayer instance as test object.
   */
  @Override
  public CrewPlayer createPlayer(String name) {
    return new CrewPlayer(name);
  }

}
