package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test Suite for the AsteroidNodeTest class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class AsteroidNodeTest extends RouteNodeTest {

  /**
   * Create a test object.
   * 
   * @return A RouteNode.
   */
  @Override
  protected RouteNode create() {
    return new AsteroidNode("summary", "description");
  }

  /**
   * Testing toString.
   */
  @Test
  public void testToString() {
    assertEquals("AsteroidNode", getObject().toString());
  }
}
