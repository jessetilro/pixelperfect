package nl.tudelft.pixelperfect.client;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for the EventCompletedMessage class.
 * 
 * @author Dmitry
 *
 */
public class EventCompletedMessageTest {

  private EventCompletedMessage object;

  /**
   * Set up a test object.
   */
  @Before
  public void init() {
    object = new EventCompletedMessage(0);
  }

  /**
   * We can also construct a HelloMessage without a message.
   */
  @Test
  public void testEmptyConstructor() {
    EventCompletedMessage obj = new EventCompletedMessage();
    assertThat(obj.getCompletedEvent(), is(0));
  }

  /**
   * When calling the temporary getCompletedEvent method we expect to retrieve the original message.
   */
  @Test
  public void testGetCompletedEvent() {
    assertThat(object.getCompletedEvent(), is(0));
  }
}
