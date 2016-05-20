package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test Suite for the AllyNode class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class AllyNodeTest extends RouteNodeTest {

  /**
   * Create a test object.
   * 
   * @return A RouteNode.
   */
  @Override
  protected RouteNode create() {
    return new AllyNode("summary", "description");
  }

  /**
   * Testing toString.
   */
  @Test
  public void testToString() {
    assertEquals("AllyNode", getObject().toString());
  }
}
