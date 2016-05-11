package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EnemyNode class.
 * 
 * @author David Alderliesten
 *
 */
public class EnemyNodeTest extends RouteNodeTest {
  private EnemyNode node;

  /**
   * Initializing objects.
   */
  @Before
  public void before() {
    node = new EnemyNode("summary", "description");
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
    assertEquals("EnemyNode", node.toString());
  }
}
