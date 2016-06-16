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
  private String summary;
  private String value;
  private int numberValue;

  /**
   * Construct an EventParameter with a specific value (part of the EventParameterValue
   * enumeration).
   * 
   * @param key
   *          The key of the parameter.
   * @param value
   *          The specific value of the parameter.
   */
  public EventParameter(String key, String value) {
    this.key = key;
    this.value = value;
    this.numberValue = 0;
    this.summary = "";
  }

  /**
   * Construct an EventParameter with a generic value (a number).
   * 
   * @param key
   *          The key of the parameter.
   * @param numberValue
   *          The number value of the parameter.
   */
  public EventParameter(String key, int numberValue) {
    this.key = key;
    this.value = "Generic";
    this.numberValue = numberValue;
    this.summary = "";
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
   * Check whether this parameter has a continuous value.
   * 
   * @return A boolean.
   */
  public boolean isGeneric() {
    return (value.equals("Generic"));
  }

  /**
   * Get this parameter's number value.
   * 
   * @return A number value.
   */
  public int getNumberValue() {
    return numberValue;
  }

  /**
   * Get this parameter's specific value.
   * 
   * @return A specific value.
   */
  public String getValue() {
    return value;
  }
  
  /**
   * Sets the value of the parameter.
   * 
   * @param value the value to be set.
   */
  public void setValue(String value) {
    this.value = value;
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
   * Generate a String representation of this EventParameter.
   * 
   * @return A String representation.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(summary).append(" is ");
    if (isGeneric()) {
      sb.append(numberValue);
    } else {
      sb.append(value);
    }
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
      if (isGeneric() && other.isGeneric()) {
        return numberValue == other.getNumberValue();
      } else {
        return value.equals(other.getValue());
      }
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
    result = prime * result + numberValue;
    return result;
  }

}
