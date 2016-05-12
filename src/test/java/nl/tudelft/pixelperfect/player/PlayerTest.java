package nl.tudelft.pixelperfect.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for the automated testing of the Player class.
 * 
 * @author David Alderliesten
 *
 */
public abstract class PlayerTest {

  private Player testObject;

  /**
   * Setting up the Player class as a test object.
   */
  @Before
  public void initialize() {
    testObject = createPlayer("Lorem Ipsum");
  }

  /**
   * Factory method for testing.
   * 
   * @return The object to be tested.
   */
  public abstract Player createPlayer(String name);

  /**
   * Verify the behavior of the getName method, which is inherited from the player class..
   */
  @Test
  public void testGetName() {
    assertEquals(testObject.getName(), "Lorem Ipsum");
  }

  /**
   * Two players with the same name should be considered equal.
   */
  @Test
  public void testEqualsTrue() {
    Player otherTest = createPlayer("Lorem Ipsum");
    assertTrue(testObject.equals(otherTest));
  }

  /**
   * Two players with a different name should not be considered equal.
   */
  @Test
  public void testEqualsFalse() {
    Player otherTest = createPlayer("Foo Bar");
    assertFalse(testObject.equals(otherTest));
  }

}
