package nl.tudelft.pixelperfect.event;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EventParameters class.
 * 
 * @author Jesse Tilro
 *
 */
public class EventParametersTest {

  private Collection<EventParameter> collection;
  private EventParameter param;

  private EventParameters parameters;
  private EventParameters parametersEmpty;

  @Before
  public void setup() {
    collection = new ArrayList<EventParameter>();
    param = new EventParameter("test", 42);
    collection.add(param);

    parameters = new EventParameters(collection);
    parametersEmpty = new EventParameters();
  }

  /**
   * When trying to get a parameter from an aggregation that does not contain a parameter with the
   * specified key, it should yield null.
   */
  @Test
  public void testGetFromEmpty() {
    assertThat(parametersEmpty.get("test"), is(nullValue()));
  }

  /**
   * When trying to get a parameter from an aggregation with a given key, it should return the right
   * parameter.
   */
  @Test
  public void testGet() {
    assertThat(parameters.get("test"), is(param));
  }

  /**
   * When adding a parameter to an aggregation, it should be added correctly and subsequently it
   * should be possible to get the parameter from the aggregation by its key.
   */
  @Test
  public void testAdd() {
    parametersEmpty.add(param);
    assertThat(parametersEmpty.get("test"), is(param));
  }

  /**
   * When an EventParameter instance aggregates exactly the parameters in a given collection, the
   * validation should yield true.
   */
  @Test
  public void testValidateTrue() {
    assertTrue(parameters.validate(collection));
  }

  /**
   * When an EventParameter instance does not exactly aggregate the parameters in a given
   * collection, the validation should yield false.
   */
  @Test
  public void testValidateFalse() {
    Collection<EventParameter> none = new ArrayList<EventParameter>();
    assertFalse(parameters.validate(none));
  }

  /**
   * An instance should be considered equal to itself.
   */
  @Test
  public void testEqualsSameInstance() {
    assertTrue(parameters.equals(parameters));
  }

  /**
   * Two instance with a different number of parameters should not be considered equal.
   */
  @Test
  public void testEqualsDifferentSizes() {
    assertFalse(parameters.equals(parametersEmpty));
  }

  /**
   * Two Hash Codes generated for the same instance should always be the same.
   */
  @Test
  public void testHashCodeSameInstance() {
    assertEquals(parameters.hashCode(), parameters.hashCode());
  }

  /**
   * Two Hash Codes generated instances that are not considered equal, should (very likely, at least
   * in this case) not be equal.
   */
  @Test
  public void testHashCodeDifferentInstances() {
    assertThat(parameters.hashCode(), not(equalTo(parametersEmpty.hashCode())));
  }

}
