package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test Suite for the EnemyNode class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class EnemyNodeTest extends RouteNodeTest {

  /**
   * Create a test object.
   * 
   * @return A RouteNode.
   */
  @Override
  protected RouteNode create() {
    return new EnemyNode("summary", "description");
  }

  /**
   * Testing toString.
   */
  @Test
  public void testToString() {
    assertEquals("EnemyNode", getObject().toString());
  }
}
