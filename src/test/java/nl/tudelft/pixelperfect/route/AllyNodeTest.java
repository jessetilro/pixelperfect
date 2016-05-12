package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the AllyNode class.
 * 
 * @author David Alderliesten
 *
 */
public class AllyNodeTest extends RouteNodeTest {
  private AllyNode node;

  /**
   * Initializing objects.
   */
  @Before
  public void before() {
    node = new AllyNode("summary", "description");
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
  
  /**
   * Testing toString.
   */
  @Test
  public void testToString() {
    assertEquals("AllyNode", node.toString());
  }
}
