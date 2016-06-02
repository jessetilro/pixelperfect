package nl.tudelft.pixelperfect.client.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
  private Map<String, Integer> parameters;

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
    this.parameters = new HashMap<String, Integer>();
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
   * Get the submitted parameters in the form of a collection of EventParameters.
   * 
   * @return The submitted parameters.
   */
  public Collection<EventParameter> getParameters() {
    Set<Map.Entry<String, Integer>> entries = parameters.entrySet();
    Collection<EventParameter> params = new ArrayList<EventParameter>();
    for (Map.Entry<String, Integer> entry : entries) {
      params.add(new EventParameter(entry.getKey(), entry.getValue()));
    }
    return params;
  }

}
