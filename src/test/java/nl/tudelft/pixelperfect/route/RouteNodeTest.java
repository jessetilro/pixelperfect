package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the RouteNode class.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public abstract class RouteNodeTest {

  private RouteNode object;

  /**
   * Create a test object.
   * 
   * @return A RouteNode.
   */
  protected abstract RouteNode create();

  /**
   * Initializing objects.
   */
  @Before
  public void before() {
    object = create();
  }

  /**
   * Getter for the test object, so subclasses may use it as well.
   * 
   * @return The test object.
   */
  protected RouteNode getObject() {
    return object;
  }

  /**
   * Testing the getSummary method.
   */
  @Test
  public void testRouteSummary() {
    assertEquals("summary", getObject().getSummary());
  }

  /**
   * Testing the getDescription method.
   */
  @Test
  public void testRouteDescription() {
    assertEquals("description", getObject().getDescription());
  }
}
