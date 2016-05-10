package nl.tudelft.pixelperfect.player;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the Player class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public abstract class PlayerTest {
  private Player toTest;

  /**
   * Setting up the Event class for the test.
   */
  @Before
  public void initalise() {
    toTest = new Player("Lorem Ipsum");
  }

  /**
   * Verify the behavior of the getName method.
   */
  @Test
  public void testGetName() {
    assertEquals(toTest.getName(), "Lorem Ipsum");
  }
}
