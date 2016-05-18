package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test Suite for the EarthNode class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class EarthNodeTest extends RouteNodeTest {

  /**
   * Create a test object.
   * 
   * @return A RouteNode.
   */
  @Override
  protected RouteNode create() {
    return new EarthNode("summary", "description");
  }

  /**
   * Testing toString.
   */
  @Test
  public void testToString() {
    assertEquals("EarthNode", getObject().toString());
  }
}
