package nl.tudelft.pixelperfect.player;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the CrewPlayer class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class CrewPlayerTest extends PlayerTest {
  private CrewPlayer toTest;

  /**
   * Setting up the Event class for the test.
   */
  @Before
  public void initalise() {
    toTest = new CrewPlayer("Lorem Ipsum");
  }

  /**
   * Verify the behavior of the getName method, which is inherited from the player class..
   */
  @Test
  public void testGetName() {
    assertEquals(toTest.getName(), "Lorem Ipsum");
  }
}
