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
  private String label;

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
  public EventCompletedMessage(String label, int completed) {
    this.label = label;
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
  
  /**
   * Retrieves the label of the completed event.
   * 
   * @return label , a String.
   */
  public String getLabel() {
    return label;
  }
}
