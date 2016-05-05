package pixelperfect.event;

/**
 * A class for storing and defining events, called upon by the event scheduler.
 * 
 * @author David Alderliesten
 *
 */
public class Event {
  private String summary;
  private String description;
  private int type;
  private long timestamp;
  private long duration;
  private double damage;

  /**
   * Constructor for the event class, taking parameters for the type of event, a summary of the
   * event/name, a description of the event, a timestamp to start, a duration, and a damage if the
   * event is failed.
   * 
   * @param type
   *          the desired type
   * @param summary
   *          summary/name of the event
   * @param description
   *          a description of the event
   * @param timestamp
   *          the timestamp of start of the event
   * @param duration
   *          The time to live milliseconds until the event expires.
   * @param damage
   *          the damage done to the ship on even failure
   */
  public Event(int type, String summary, String description, long timestamp, long duration,
      double damage) {
    this.type = type;
    this.summary = summary;
    this.description = description;
    this.timestamp = timestamp;
    this.duration = duration;
    this.damage = damage;
  }

  /**
   * Getter for the event type.
   * 
   * @return type of event
   */
  public int getType() {
    return this.type;
  }

  /**
   * Getter for the event summary/name.
   * 
   * @return summary of event/name
   */
  public String getSummary() {
    return this.summary;
  }

  /**
   * Get the description of the event.
   * 
   * @return description of event
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Get the starting timestamp of the event.
   * 
   * @return starting timestamp of event
   */
  public long getTimeStamp() {
    return this.timestamp;
  }

  /**
   * Get the total duration of this event.
   * 
   * @return The total duration.
   */
  public long getDuration() {
    return this.duration;
  }

  /**
   * Get the damage done to the ship in the case of event failure.
   * 
   * @return event damage done to ship
   */
  public double getDamage() {
    return this.damage;
  }
}
