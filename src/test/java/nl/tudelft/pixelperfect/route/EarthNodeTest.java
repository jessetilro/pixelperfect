package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EarthNode class.
 * 
 * @author David Alderliesten
 *
 */
public class EarthNodeTest extends RouteNodeTest {
  private EarthNode node;

  /**
   * Initializing objects.
   */
  @Before
  public void before() {
    node = new EarthNode("summary", "description");
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
