package pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the RouteNode class.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class RouteNodeTest {

  private RouteNode node;

  /**
   * Initializing objects.
   */
  @Before
  public void before() {
    node = new RouteNode("summary", "description");
  }

  /**
   * Testing the getSummary method.
   */
  @Test
  public void testRouteSummary() {
    assertEquals("summary", node.getSummary());
  }

  /**
   * Testing the getDescription method.
   */
  @Test
  public void testRouteDescription() {
    assertEquals("description", node.getDescription());
  }
}
