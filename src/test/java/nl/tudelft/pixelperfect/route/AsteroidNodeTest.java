package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the AsteroidNodeTest class.
 * 
 * @author David Alderliesten
 *
 */
public class AsteroidNodeTest extends RouteNodeTest {
  private AsteroidNode node;

  /**
   * Initializing objects.
   */
  @Before
  public void before() {
    node = new AsteroidNode("summary", "description");
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
