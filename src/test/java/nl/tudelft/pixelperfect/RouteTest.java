package nl.tudelft.pixelperfect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import nl.tudelft.pixelperfect.game.Route;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the Route class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class RouteTest {
  private Route object;

  /**
   * Setting up the route class for the test.
   */
  @Before
  public void initialise() {
    object = new Route(42);
    object.setTimestamp(0);
  }

  /**
   * When a Route's duration has not passed since it was created, it is not completed yet.
   */
  @Test
  public void testIsCompletedFalse() {
    assertFalse(object.isCompleted(41));
  }

  /**
   * When a Route's duration has passed since it was created, it is completed.
   */
  @Test
  public void testIsCompletedTrue() {
    assertTrue(object.isCompleted(42));
  }
}
