package nl.tudelft.pixelperfect.route;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

/**
 * Test Suite for the Tuple class.
 * 
 * @author Jesse Tilro
 *
 */
public class TupleTest {

  /**
   * The toString method should generate a correct string representation.
   */
  @Test
  public void testToString() {
    RouteNode node1 = mock(RouteNode.class);
    RouteNode node2 = mock(RouteNode.class);
    when(node1.toString()).thenReturn("links");
    when(node2.toString()).thenReturn("rechts");
    Tuple object = new Tuple(node1, node2);

    assertEquals("(links, rechts)", object.toString());
  }

}
