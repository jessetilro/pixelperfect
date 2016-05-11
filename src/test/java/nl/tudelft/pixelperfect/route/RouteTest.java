package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test Suite for the Route class.
 * 
 * @author David Alderliesten
 *
 */
public class RouteTest {
  private Route toTest;
  private RouteNode first = new RouteNode("First", "The first place to go.");
  private RouteNode second = new RouteNode("Second", "The second and final place to go.");

  /**
   * Setting up the route class for the test.
   */
  @Before
  public void initialise() {
    ArrayList<Tuple> toAdd = new ArrayList<Tuple>();
    toAdd.add(new Tuple(first, second));

    toTest = new Route((long) 42, toAdd);
  }

  /**
   * Test to validate the isCompleted false functionality.
   */
  @Test
  public void testIsCompletedFalse() {
    assertFalse(toTest.isCompleted(1));
  }

  // /**
  // * Test to validate the isCompleted true functionality.
  // */
  // @Test TODO VALIDATE FUNCTIONALITY
  // public void testIsCompletedTrue() {
  // assertTrue(toTest.isCompleted(0));
  // }

  /**
   * Test to validate arraylist popping behavior for all cases.
   */
  @Test
  public void testRouteNodePopping() {
    Tuple tp = toTest.popRouteNode();
    assertEquals(tp.getLeft().getDescription(), first.getDescription());
    assertEquals(tp.getRight().getDescription(), second.getDescription());
    assertEquals(toTest.popRouteNode(), null);
  }
}
