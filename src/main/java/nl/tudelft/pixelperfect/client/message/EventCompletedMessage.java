package nl.tudelft.pixelperfect.client.message;

import java.util.HashMap;
import java.util.Map;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * The EventCompletedMessage sends a message to the Server to tell that an event was completed.
 *
 * @author Floris Doolaard
 * @author Jesse Tilro
 */
@Serializable
public class EventCompletedMessage extends AbstractMessage {
  private int type;
  private Map<String, Integer> parameters;

  /**
   * The EventCompletedMessage constructor.
   */
  public EventCompletedMessage() {
    type = 0;
    parameters = new HashMap<String, Integer>();
  }

  /**
   * The constructor with a specific Event completed.
   * 
   * @param type
   *          The type of Event completed.
   */
  public EventCompletedMessage(int type) {
    this.type = type;
    parameters = new HashMap<String, Integer>();
  }

  /**
   * Get the type of Event completed.
   * 
   * @return The type of the completed Event.
   */
  public int getType() {
    return type;
  }

  /**
   * Set the submitted parameters.
   * 
   * @param parameters
   *          The submitted parameters.
   */
  public void setParameters(Map<String, Integer> parameters) {
    this.parameters = parameters;
  }

  /**
   * Get the submitted parameters in the form they were originally stored (A Map).
   *
   * @return A Map of the submitted parameters.
   */
  public Map<String, Integer> getParameters() {
    return parameters;
  }

}
