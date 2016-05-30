package nl.tudelft.pixelperfect.event;

/**
 * Class representing a single parameter of an Event, having either a discrete value
 * (EventParameterValue) or a continuous one (Integer).
 * 
 * @author Jesse Tilro
 *
 */
public class EventParameter {

  private String key;
  private EventParameterValues value;
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
  public EventParameter(String key, EventParameterValues value) {
    this.key = key;
    this.value = value;
    this.numberValue = 0;
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
    this.value = EventParameterValues.GENERIC;
    this.numberValue = numberValue;
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
    return (value == EventParameterValues.GENERIC);
  }

  /**
   * Get this parameter's number value.
   * 
   * @return A number value.
   */
  protected int getNumberValue() {
    return numberValue;
  }

  /**
   * Get this parameter's specific value.
   * 
   * @return A specific value.
   */
  protected EventParameterValues getValue() {
    return value;
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
    result = prime * result + value.ordinal();
    result = prime * result + numberValue;
    return result;
  }

}
