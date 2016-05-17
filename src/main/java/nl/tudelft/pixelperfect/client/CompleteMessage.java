package nl.tudelft.pixelperfect.client;

import com.jme3.network.serializing.Serializable;

/**
 * The message that the server recieves when an event is complete.
 * 
 * @author Dmitry
 *
 */
@Serializable
public class CompleteMessage {

  private String completed;

  /**
   * Constructs a new CompleteMessage instance.
   * 
   */
  public CompleteMessage() {
  }

  /**
   * Changes the content of the message.
   * 
   * @param passedEvent
   *          The completed event.
   */
  public CompleteMessage(String passedEvent) {
    completed = passedEvent;
  }

  /**
   * Returns the string in the message.
   * 
   * @return The string.
   */
  public String getEvent() {
    return completed;
  }
}
