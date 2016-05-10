package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the MarsNode class.
 * 
 * @author David Alderliesten
 *
 */
public class MarsNodeTest extends RouteNodeTest {
  private MarsNode node;

  /**
   * Initializing objects.
   */
  @Before
  public void before() {
    node = new MarsNode("summary", "description");
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
