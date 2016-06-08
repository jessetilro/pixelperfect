package nl.tudelft.pixelperfect.client.message;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.pixelperfect.client.message.EventCompletedMessage;

/**
 * Test case for the EventCompletedMessage class.
 * 
 * @author Dmitry
 *
 */
public class EventCompletedMessageTest {

  private EventCompletedMessage object;
  private Map<String, Integer> map;

  /**
   * Set up a test object.
   */
  @Before
  public void init() {
    object = new EventCompletedMessage(2);
    map = new HashMap<String, Integer>();
    map.put("test", 42);
    object.setParameters(map);
  }

  /**
   * We can also construct a EventCompletedMessage without any attributes.
   */
  @Test
  public void testEmptyConstructor() {
    EventCompletedMessage obj = new EventCompletedMessage();
    assertThat(obj.getType(), is(0));
  }

  /**
   * When calling the getType method we expect to retrieve the original type.
   */
  @Test
  public void testGetType() {
    assertThat(object.getType(), is(2));
  }

  /**
   * When calling the getParameters method we expect to retrieve the original parameters.
   */
  @Test
  public void testGetParameters() {
    assertThat(object.getParameters().get("test"), is(42));
  }
}
