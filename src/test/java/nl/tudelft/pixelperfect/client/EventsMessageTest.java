package nl.tudelft.pixelperfect.client;

import static org.junit.Assert.assertNull;
import nl.tudelft.pixelperfect.client.message.EventsMessage;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test case of the EventsMessage.
 * 
 * @author Dmitry Malarev
 *
 */
public class EventsMessageTest {

  private EventsMessage message;
  
  /**
   * Setting test object.
   */
  @Before
  public void initialise() {
    message = new EventsMessage(0, "Lorem", 5L, 7L);
  }
  
  /**
   * Tests the empty constructor.
   * 
   */
  @Test
  public void testNullConstructor() {
    EventsMessage event = new EventsMessage();
    assertNull(event.getType());
  }
  
  /**
   * Tests the getId() method.
   * 
   */
  @Test
  public void testGetId() {
    assertEquals(0, message.getId());
  }
  
  /**
   * Tests the getType() method.
   * 
   */
  @Test
  public void testGetType() {
    assertEquals("Lorem", message.getType());
  }
  
  /**
   * Tests the getTime() method.
   * 
   */
  @Test
  public void testGetTimestamp() {
    assertEquals(5L, message.getTime(), 0);
  }
  
  /**
   * Tests the getDuration() method.
   * 
   */
  @Test
  public void testGetDuration() {
    assertEquals(7L, message.getDuration(), 0);
  }

}
