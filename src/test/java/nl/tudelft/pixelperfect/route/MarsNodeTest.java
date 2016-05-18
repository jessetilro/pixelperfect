package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test Suite for the MarsNode class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class MarsNodeTest extends RouteNodeTest {

  /**
   * Create a test object.
   * 
   * @return A RouteNode.
   */
  @Override
  protected RouteNode create() {
    return new MarsNode("summary", "description");
  }

  /**
   * Testing toString.
   */
  @Test
  public void testToString() {
    assertEquals("MarsNode", getObject().toString());
  }
}
