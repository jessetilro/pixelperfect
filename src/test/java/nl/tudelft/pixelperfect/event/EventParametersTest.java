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
@SuppressWarnings("PMD.TooManyStaticImports")
public class EventParametersTest {

  private Collection<EventParameter> collection;
  private EventParameter param;

  private EventParameters parameters;
  private EventParameters parametersEmpty;

  private String key;

  /**
   * Set up the test objects an dependencies. (Dependencies are not mocked in order to test
   * integration.)
   */
  @Before
  public void setUp() {
    key = "test";
    collection = new ArrayList<EventParameter>();
    param = new EventParameter(key, 42);
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
    assertThat(parametersEmpty.get(key), is(nullValue()));
  }

  /**
   * When trying to get a parameter from an aggregation with a given key, it should return the right
   * parameter.
   */
  @Test
  public void testGet() {
    assertThat(parameters.get(key), is(param));
  }

  /**
   * When adding a parameter to an aggregation, it should be added correctly and subsequently it
   * should be possible to get the parameter from the aggregation by its key.
   */
  @Test
  public void testAdd() {
    parametersEmpty.add(param);
    assertThat(parametersEmpty.get(key), is(param));
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
   * When an EventParameter instance does not exactly aggregate the parameters in a given collection
   * (different size), the validation should yield false.
   */
  @Test
  public void testValidateFalseDifferentSize() {
    Collection<EventParameter> none = new ArrayList<EventParameter>();
    assertFalse(parameters.validate(none));
  }

  /**
   * When an EventParameter instance does not exactly aggregate the parameters in a given collection
   * (different parameters), the validation should yield false.
   */
  @Test
  public void testValidateFalseDifferentParameters() {
    Collection<EventParameter> other = new ArrayList<EventParameter>();
    other.add(new EventParameter(key, 41));
    assertFalse(parameters.validate(other));
  }

  /**
   * An instance should be considered equal to itself.
   */
  @Test
  public void testEqualsSameInstance() {
    assertEquals(parameters, parameters);
  }

  /**
   * Two instance with a different number of parameters should not be considered equal.
   */
  @Test
  public void testEqualsDifferentSizes() {
    assertThat(parameters, not(equalTo(parametersEmpty)));
  }

  /**
   * An EventParameters instance should no be considered equal to something of another type.
   */
  @Test
  public void testEqualsNotAnEventParameters() {
    assertThat(parameters, not(equalTo(null)));
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
