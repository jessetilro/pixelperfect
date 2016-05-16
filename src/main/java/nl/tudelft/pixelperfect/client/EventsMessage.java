package nl.tudelft.pixelperfect.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;


/**
 * Message type that sends the event to the client.
 * 
 * @author Dmitry Malarev
 *
 */
@Serializable
public class EventsMessage extends AbstractMessage {

  private String eve;

  /**
   * Constructs a new EventsMessage instance.
   * 
   */
  public EventsMessage() {
  }

  /**
   * Changes the content of the message.
   * 
   * @param event
   *          The event that will be the new message.
   */
  public EventsMessage(String event) {
    eve = event;
  }

  /**
   * Returns the event to send to the client.
   * 
   * @return The event.
   */
  public String getLog() {
    return eve;
  }

}
