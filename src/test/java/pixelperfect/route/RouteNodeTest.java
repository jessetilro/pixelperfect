package pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the RouteNode class.
 * 
 * @author Jesse Tilro
 *
 */
public class RouteNodeTest {

  RouteNode node;

  @Before
  public void before() {
    node = new RouteNode("summary", "description");
  }

  @Test
  public void testRouteSummary() {
    assertEquals("summary", node.getSummary());
  }

  @Test
  public void testRouteDescription() {
    assertEquals("description", node.getDescription());
  }
}
