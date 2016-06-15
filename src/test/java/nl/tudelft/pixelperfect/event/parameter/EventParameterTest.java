package nl.tudelft.pixelperfect.event.parameter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EventParameter class. Suppressing some warnings because we needs all the
 * static imports for testing. Also a test suite can contain relatively many methods if they are all
 * small and simple test cases.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings({ "PMD.TooManyStaticImports", "PMD.TooManyMethods" })
public class EventParameterTest {

  private EventParameter object;
  private EventParameter objectSpecific;
  private String key;

  /**
   * Set up the test objects.
   */
  @Before
  public void setUp() {
    key = "test";
    object = new EventParameter(key, 42);
    objectSpecific = new EventParameter(key, "Energy Shield");
  }

  /**
   * The isGeneric method should tell the truth, i.e. whether the parameter has a discrete value
   * assigned.
   */
  @Test
  public void testIsGeneric() {
    assertTrue(object.isGeneric());
    assertFalse(objectSpecific.isGeneric());
  }

  /**
   * The getKey method should return the right key.
   */
  @Test
  public void testGetKey() {
    assertEquals(key, object.getKey());
    assertEquals(key, objectSpecific.getKey());
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
    assertEquals(object, other);
  }

  /**
   * Two parameters with different keys should not be considered equal.
   */
  @Test
  public void testEqualsDifferentKeys() {
    EventParameter other = new EventParameter("different", 42);
    assertThat(object, not(equalTo(other)));
  }

  /**
   * Two parameters with different values should not be considered equal.
   */
  @Test
  public void testEqualsDifferentValues() {
    EventParameter other = new EventParameter(key, 43);
    assertThat(object, not(equalTo(other)));
  }

  /**
   * An EventParameter instance should not be considered equal to something of another type.
   */
  @Test
  public void testEqualsNull() {
    assertFalse(object.equals(null));
  }

  /**
   * An EventParameter instance should not be considered equal to something of another type.
   */
  @Test
  public void testEqualsNotAnEventParameter() {
    assertFalse(object.equals(key));
  }

  /**
   * The Hash Codes generated for two instance that are not equal should be different.
   */
  @Test
  public void testHashCodeDifferent() {
    assertThat(object.hashCode(), not(equalTo(objectSpecific.hashCode())));
  }

  /**
   * The Hash Codes generated for two instances that are equal should be the same.
   */
  @Test
  public void testHashCodeSame() {
    EventParameter other = new EventParameter(key, 42);
    assertThat(object.hashCode(), equalTo(other.hashCode()));
  }
  
  /**
   * A generic parameter and a normal cannot have the same values.
   */
  @Test
  public void testDifferentValues() {
    assertThat(object.getValue(), not(equalTo(objectSpecific.getValue())));
  }

  /**
   * The toString method should generate a correct String representation of a generic
   * EventParameter.
   */
  @Test
  public void testToStringGeneric() {
    object
        .setSummary("The Answer to the Ultimate Question of Life, the Universe, and Everything");
    String expected = "The Answer to the Ultimate Question of Life,"
        + " the Universe, and Everything is 42";
    assertEquals(expected, object.toString());
  }

  /**
   * The toString method should generate a correct String representation of a specific
   * EventParameter.
   */
  @Test
  public void testToStringSpecific() {
    objectSpecific.setSummary("The thing that keeps you safe");
    String expected = "The thing that keeps you safe is Energy Shield";
    assertEquals(expected, objectSpecific.toString());
  }

}
