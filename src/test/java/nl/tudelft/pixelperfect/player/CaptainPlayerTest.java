package nl.tudelft.pixelperfect.player;

/**
 * Test Suite for the CaptainPlayer class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
public class CaptainPlayerTest extends PlayerTest {

  /**
   * Create a CaptainPlayer instance as test object.
   */
  @Override
  public CaptainPlayer createPlayer(String name) {
    return new CaptainPlayer(name);
  }

}
