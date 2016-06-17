package nl.tudelft.pixelperfect.event.parameter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.jme3.network.serializing.Serializable;

/**
 * Class aggregating the parameters (key / value pairs) for an Event.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
@Serializable
public class EventParameterCollection {

  private Map<String, EventParameter> map;

  /**
   * Construct a new empty aggregation of parameters.
   */
  public EventParameterCollection() {
    map = new HashMap<String, EventParameter>();
  }

  /**
   * Construct a new aggregation of parameters containing all parameters in a given collection.
   * 
   * @param parameters
   *          The parameters to be aggregated.
   */
  public EventParameterCollection(Collection<EventParameter> parameters) {
    map = new HashMap<String, EventParameter>();
    for (EventParameter param : parameters) {
      add(param);
    }
  }

  /**
   * Get a parameter in this aggregation by its key.
   * 
   * @param key
   *          The parameter's key.
   * @return The parameter.
   */
  public EventParameter get(String key) {
    return map.get(key);
  }

  /**
   * Add a new parameter to the aggregation.
   * 
   * @param param
   *          The parameter to add.
   */
  public void add(EventParameter param) {
    map.put(param.getKey(), param);
  }

  /**
   * Generate a String representation of the collection of parameters.
   * 
   * @return A String representation of this instance.
   */
  public String toString() {
    String and = "";
    StringBuilder sb = new StringBuilder();
    for (EventParameter param : map.values()) {
      sb.append(and);
      sb.append(param.toString());
      and = " and ";
    }
    return sb.toString();
  }

  /**
   * Generate a debug string representation for the parameters.
   * 
   * @return the debug string.
   */
  public String toDebugString() {
    StringBuilder sb = new StringBuilder();
    if (map.isEmpty()) {
      sb.append("None");
    } else {
      for (EventParameter param : map.values()) {
        sb.append(", ").append(param.getValueDescription());
      }
    }
    return sb.toString();
  }

  /**
   * Validate whether a given collection of parameters matches the parameters aggregated by this
   * instance. This method is used this way for efficiency (iterating over collection, while getting
   * by key from hash map).
   * 
   * @param parameters
   *          The collection of parameters to validate against.
   * 
   * @return Whether the given collection of parameters corresponds to the parameters this instance
   *         aggregates.
   */
  public boolean validate(Collection<EventParameter> parameters) {
    if (map.size() != parameters.size()) {
      return false;
    }
    for (EventParameter parameter : parameters) {
      EventParameter param = map.get(parameter.getKey());
      if (!param.equals(parameter)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Check whether another object aggregates the same parameters as this instance does.
   * 
   * @param that
   *          Another object to compare this instance to.
   * 
   * @return Whether the compared instances are equal.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof EventParameterCollection) {
      EventParameterCollection other = (EventParameterCollection) that;
      return other.validate(map.values());
    }
    return false;
  }

  /**
   * Generates a Hash Code for this instance.
   * 
   * @return A likely unique hash code.
   */
  @Override
  public int hashCode() {
    int result = 32;
    int prime = 31;
    for (EventParameter param : map.values()) {
      result = prime * result + param.hashCode();
    }
    return result;
  }

}
