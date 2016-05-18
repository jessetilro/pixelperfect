package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Test Suite for the RouteGenerator class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class RouteGeneratorTest {

  /**
   * Test the getRoute method.
   */
  @Test
  public void getRoute() {
    RouteGenerator generator = RouteGenerator.getInstance();
    Route route = generator.getRoute();
    assertFalse(route.isCompleted(0));
  }

}
