package nl.tudelft.pixelperfect.event;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
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

  private EventParameter object;
  private EventParameter objectGeneric;

  /**
   * Set up the test objects.
   */
  @Before
  public void setup() {
    object = new EventParameter("test", EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD);
    objectGeneric = new EventParameter("test", 42);
  }

  /**
   * The isDiscrete method should tell the truth, i.e. whether the parameter has a discrete value
   * assigned.
   */
  @Test
  public void testIsGeneric() {
    assertTrue(objectGeneric.isGeneric());
    assertFalse(object.isGeneric());
  }

  /**
   * The getKey method should return the right key.
   */
  @Test
  public void testGetKey() {
    assertEquals("test", object.getKey());
    assertEquals("test", objectGeneric.getKey());
  }

  /**
   * An instance should be considered equal to itself.
   */
  @Test
  public void testEqualsSameInstance() {
    assertTrue(object.equals(object));
  }

  /**
   * A generic and a not generic instance with the same key should not be considered equal.
   */
  @Test
  public void testEqualsGenericAndNotGeneric() {
    assertFalse(object.equals(objectGeneric));
  }

  /**
   * Two parameters with different keys should not be considered equal.
   */
  @Test
  public void testEqualsDifferentKeys() {
    EventParameter other = new EventParameter("different",
        EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD);
    assertFalse(object.equals(other));
  }

  /**
   * Two parameters with different values should not be considered equal.
   */
  @Test
  public void testEqualsDifferentValues() {
    EventParameter other = new EventParameter("test",
        EventParameterValues.HOSTILE_SHIP_ARMOR_TITANIUM);
    assertFalse(object.equals(other));
  }

  /**
   * Two parameters with different numbers should not be considered equal.
   */
  @Test
  public void testEqualsDifferentNumbers() {
    EventParameter other = new EventParameter("test", 41);
    assertFalse(objectGeneric.equals(other));
  }

  /**
   * The Hash Codes generated for two instance that are not equal should be different.
   */
  @Test
  public void testHashCodeDifferent() {
    assertThat(object.hashCode(), not(equalTo(objectGeneric.hashCode())));
  }

  /**
   * The Hash Codes generated for two instances that are equal should be the same.
   */
  @Test
  public void testHashCodeSame() {
    EventParameter other = new EventParameter("test",
        EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD);
    assertThat(object.hashCode(), equalTo(other.hashCode()));
  }

}
