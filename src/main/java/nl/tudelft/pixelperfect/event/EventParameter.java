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
  private int number;

  /**
   * Construct an EventParameter with a discrete value (part of the EventParameterValue
   * enumeration).
   * 
   * @param key
   *          The key of the parameter.
   * @param value
   *          The (discrete) value of the parameter.
   */
  public EventParameter(String key, EventParameterValues value) {
    this.key = key;
    this.value = value;
    this.number = 0;
  }

  /**
   * Construct an EventParameter with a continuous value (a number).
   * 
   * @param key
   *          The key of the parameter.
   * @param value
   *          The (continuous) value of the parameter.
   */
  public EventParameter(String key, int value) {
    this.key = key;
    this.value = EventParameterValues.GENERIC;
    this.number = value;
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
  public boolean isContinuous() {
    return (value == EventParameterValues.GENERIC);
  }

  /**
   * Check whether this parameter has a discrete value.
   * 
   * @return A boolean.
   */
  public boolean isDiscrete() {
    return !isContinuous();
  }

}
