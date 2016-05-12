package nl.tudelft.pixelperfect.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the CaptainPlayer class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class CaptainPlayerTest extends PlayerTest {
  private CaptainPlayer toTest;

  /**
   * Setting up the Event class for the test.
   */
  @Before
  public void initalise() {
    toTest = new CaptainPlayer("Lorem Ipsum");
  }

  /**
   * Verify the behavior of the getName method, which is inherited from the player class..
   */
  @Test
  public void testGetName() {
    assertEquals(toTest.getName(), "Lorem Ipsum");
  }

  @Test
  public void testEqualsTrue() {
    CaptainPlayer otherTest = new CaptainPlayer("Lorem Ipsum");
    assertTrue(toTest.equals(otherTest));
  }

  @Test
  public void testEqualsFalse() {
    CaptainPlayer otherTest = new CaptainPlayer("Foo Bar");
    assertFalse(toTest.equals(otherTest));
  }

}
