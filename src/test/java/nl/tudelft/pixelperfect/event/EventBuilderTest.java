package nl.tudelft.pixelperfect.event;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EventBuilder class. Most of it has already been tested through integration
 * tests, therefore this unit test suite is quite small.
 * 
 * @author Jesse Tilro
 *
 */
public class EventBuilderTest {

  private EventBuilder object;

  /**
   * Set up the test object.
   */
  @Before
  public void setUp() {
    object = new EventBuilder();
  }

  /**
   * When no Event type has been specified for the EventBuilder to build, it should yield a null
   * reference when calling the buildEvent method.
   */
  @Test
  public void testBuildEventWithoutSettingType() {
    assertThat(object.buildEvent(), is(nullValue()));
  }

}
