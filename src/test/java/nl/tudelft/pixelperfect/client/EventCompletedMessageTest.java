package nl.tudelft.pixelperfect.client;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class EventCompletedMessageTest {

  private EventCompletedMessage object;

  /**
   * Set up a test object.
   */
  @Before
  public void init() {
    object = new EventCompletedMessage("Hello World!");
  }

  /**
   * We can also construct a HelloMessage without a message.
   */
  @Test
  public void testEmptyConstructor() {
    EventCompletedMessage obj = new EventCompletedMessage();
    assertThat(obj.getCompletedEvent(), is(nullValue()));
  }

  /**
   * When calling the temporary getSomething method we expect to retrieve the original message.
   */
  @Test
  public void testGetSomething() {
    assertThat(object.getCompletedEvent(), is("Hello World!"));
  }
}
