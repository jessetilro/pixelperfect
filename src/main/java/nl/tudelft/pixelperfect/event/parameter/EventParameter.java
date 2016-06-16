package nl.tudelft.pixelperfect.event.parameter;

import com.jme3.network.serializing.Serializable;

/**
 * Class representing a single parameter of an Event, having either a discrete value
 * (EventParameterValue) or a continuous one (Integer).
 * 
 * @author Jesse Tilro
 *
 */
@Serializable
public class EventParameter {

  private String key;
  private int value;
  private String summary;
  private String valueDescription;

  /**
   * Construct an EventParameter with a specific value (part of the EventParameterValue
   * enumeration).
   * 
   * @param key
   *          The key of the parameter.
   * @param value
   *          The specific value of the parameter.
   */
  public EventParameter(String key, int value) {
    this.key = key;
    this.value = value;
    this.summary = "";
    this.valueDescription = "";
  }

  /**
   * Get this parameter's key.
   * 
   * @return This parameter's key.
   */
  public String getKey() {
    return key;
  }

  /**
   * Get this parameter's specific value.
   * 
   * @return A specific value.
   */
  public int getValue() {
    return value;
  }

  /**
   * Set the summary for this parameter.
   * 
   * @param summary
   *          The summary.
   */
  public void setSummary(String summary) {
    this.summary = summary;
  }

  /**
   * Set the summary for this parameter.
   * 
   * @param description
   *          The description that should be associated with the value of this parameter.
   */
  public void setValueDescription(String description) {
    this.valueDescription = description;
  }

  /**
   * Get a string representation of this parameter's value.
   */
  public String getValueDescription() {
    if (valueDescription == null || valueDescription.equals("")) {
      return Integer.toString(value);
    } else {
      return valueDescription;
    }
  }

  /**
   * Generate a String representation of this EventParameter.
   * 
   * @return A String representation.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(summary).append(" is ");
    sb.append(getValueDescription());
    return sb.toString();
  }

  /**
   * Compares two EventParameters to see if they're the same.
   * 
   * @param that
   *          The object to compare this instance to.
   * 
   * @return Whether or not the compared objects are the same EventParameter.
   */
  @Override
  public boolean equals(Object that) {
    if (that instanceof EventParameter) {
      EventParameter other = (EventParameter) that;
      if (!key.equals(other.getKey())) {
        return false;
      }
      return value == other.getValue();
    }
    return false;
  }

  /**
   * Generate a Hash Code for this instance.
   * 
   * @return A likely unique hash code for this instance.
   */
  @Override
  public int hashCode() {
    int result = 42;
    int prime = 37;
    for (char ch : key.toCharArray()) {
      result = prime * result + (int) ch;
    }
    result = prime * result + summary.length();
    result = prime * result + value;
    return result;
  }

}
