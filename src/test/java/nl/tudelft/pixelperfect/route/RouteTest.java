package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
  private RouteNode first = new AllyNode("First", "The first place to go.");
  private RouteNode second = new AsteroidNode("Second", "The second and final place to go.");

  /**
   * Setting up the route class for the test.
   */
  @Before
  public void initialise() {
    ArrayList<Tuple> route = new ArrayList<Tuple>();
    route.add(new Tuple(first, second));

    object = new Route(42, route);
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

  /**
   * Directly after a new Route is generated, it should not be completed yet.
   */
  @Test
  public void testGenerateRoute() {
    Route route = Route.generateRoute();
    assertFalse(route.isCompleted(System.currentTimeMillis()));
  }

  /**
   * The toString method should generate a correct String representation.
   */
  @Test
  public void testToString() {
    assertEquals("<Route (42 ms): [(AllyNode, AsteroidNode)]>", object.toString());
  }

  /**
   * When it popping from the Route, the next tuple of RouteNode's should be returned and removed
   * from the Route.
   */
  @Test
  public void testRouteNodePopping() {
    Tuple tp = object.popRouteNode();
    assertEquals(tp.getLeft().getDescription(), first.getDescription());
    assertEquals(tp.getRight().getDescription(), second.getDescription());
    assertEquals(object.popRouteNode(), null);
  }
}
