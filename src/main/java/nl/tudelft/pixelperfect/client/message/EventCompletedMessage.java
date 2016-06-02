package nl.tudelft.pixelperfect.client.message;

import java.util.Collection;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import nl.tudelft.pixelperfect.event.parameter.EventParameter;

/**
 * The EventCompletedMessage sends a message to the Server to tell that an event was completed.
 *
 * @author Floris Doolaard
 * @author Jesse Tilro
 */
@Serializable
public class EventCompletedMessage extends AbstractMessage {
  private int completed;
  private String label;
  private Collection<EventParameter> parameters;

  /**
   * The EventCompletedMessage constructor.
   */
  public EventCompletedMessage() {

  }

  /**
   * The constructor with a specific Event completed.
   * 
   * @param completed
   *          A completed Event.
   * @param label
   *          A name as a String.
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
   * @return label A String.
   */
  public String getLabel() {
    return label;
  }
}
