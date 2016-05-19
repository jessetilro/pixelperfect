package nl.tudelft.pixelperfect.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;


/**
 * The EventCompletedMessage sends a message to the Server to tell that an event was completed.
 *
 * @author Floris Doolaard
 */
@Serializable
public class EventCompletedMessage extends AbstractMessage {
  private int completed;

  /**
   * The EventCompletedMessage constructor.
   */
  public EventCompletedMessage() {

  }

  /**
   * The constructor with a specific Event completed.
   * 
   * @param completed , a completed Event.
   */
  public EventCompletedMessage(int completed) {
    this.completed = completed;
  }
  
  /**
   * Gets the Event that was completed.
   * 
   * @return a completed Event.
   */
  public int getCompletedEvent() {
    return completed;
  }
}
