package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EventParameter class.
 * 
 * @author Jesse Tilro
 *
 */
public class EventParameterTest {

  private EventParameter objectDiscrete;
  private EventParameter objectContinuous;

  /**
   * Set up the test objects.
   */
  @Before
  public void setup() {
    objectDiscrete = new EventParameter("test",
        EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD);
    objectContinuous = new EventParameter("test", 42);
  }

  /**
   * The isDiscrete method should tell the truth, i.e. whether the parameter has a discrete value
   * assigned.
   */
  @Test
  public void testIsDiscrete() {
    assertTrue(objectDiscrete.isDiscrete());
    assertFalse(objectContinuous.isDiscrete());
  }

  /**
   * The isContinuous method should tell the truth, i.e. whether the parameter has a continuous
   * value assigned.
   */
  @Test
  public void testIsContinuous() {
    assertFalse(objectDiscrete.isContinuous());
    assertTrue(objectContinuous.isContinuous());
  }

  /**
   * The getKey method should return the right key.
   */
  @Test
  public void testGetKey() {
    assertEquals("test", objectDiscrete.getKey());
    assertEquals("test", objectContinuous.getKey());
  }

}
