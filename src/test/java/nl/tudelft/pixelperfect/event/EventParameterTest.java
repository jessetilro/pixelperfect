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
@SuppressWarnings("PMD.TooManyStaticImports")
public class EventParameterTest {

  private EventParameter object;
  private EventParameter objectGeneric;
  private String key;

  /**
   * Set up the test objects.
   */
  @Before
  public void setUp() {
    key = "test";
    object = new EventParameter(key, EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD);
    objectGeneric = new EventParameter(key, 42);
  }

  /**
   * The isGeneric method should tell the truth, i.e. whether the parameter has a discrete value
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
    assertEquals(key, object.getKey());
    assertEquals(key, objectGeneric.getKey());
  }

  /**
   * An instance should be considered equal to itself.
   */
  @Test
  public void testEqualsSameInstance() {
    assertEquals(object, object);
  }

  /**
   * Two generic parameters with the same key and number should be considered equal.
   */
  @Test
  public void testEqualsSameNumber() {
    EventParameter other = new EventParameter(key, 42);
    assertEquals(objectGeneric, other);
  }

  /**
   * Two parameters with the same key and value should be considered equal.
   */
  @Test
  public void testEqualsSameValue() {
    EventParameter other = new EventParameter(key,
        EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD);
    assertEquals(object, other);
  }

  /**
   * A generic and a not generic instance with the same key should not be considered equal.
   */
  @Test
  public void testEqualsGenericAndNotGeneric() {
    assertThat(object, not(equalTo(objectGeneric)));
  }

  /**
   * Two parameters with different keys should not be considered equal.
   */
  @Test
  public void testEqualsDifferentKeys() {
    EventParameter other = new EventParameter("different",
        EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD);
    assertThat(object, not(equalTo(other)));
  }

  /**
   * Two parameters with different values should not be considered equal.
   */
  @Test
  public void testEqualsDifferentValues() {
    EventParameter other = new EventParameter(key,
        EventParameterValues.HOSTILE_SHIP_ARMOR_TITANIUM);
    assertThat(object, not(equalTo(other)));
  }

  /**
   * Two parameters with different numbers should not be considered equal.
   */
  @Test
  public void testEqualsDifferentNumbers() {
    EventParameter other = new EventParameter(key, 41);
    assertThat(object, not(equalTo(other)));
  }

  /**
   * An EventParameter instance should no be considered equal to something of another type.
   */
  @Test
  public void testEqualsNotAnEventParameter() {
    assertThat(object, not(equalTo(null)));
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
    EventParameter other = new EventParameter(key,
        EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD);
    assertThat(object.hashCode(), equalTo(other.hashCode()));
  }

}
