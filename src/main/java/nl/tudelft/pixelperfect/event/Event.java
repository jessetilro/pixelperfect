package nl.tudelft.pixelperfect.event;

import nl.tudelft.pixelperfect.Scene;
import nl.tudelft.pixelperfect.Spaceship;

/**
 * A class for storing and defining events, called upon by the event scheduler.
 * 
 * @author David Alderliesten
 *
 */
public abstract class Event {
  private String summary;
  private String description;
  private int id;
  private long timestamp;
  private long duration;
  private long timeLeft;
  private double damage;

  /**
   * Constructor for the event class, taking parameters for the type of event, a summary of the
   * event/name, a description of the event, a timestamp to start, a duration, and a damage if the
   * event is failed.
   * 
   * @param id
   *          The unique id of the event.
   * @param summary
   *          Summary/name of the event.
   * @param description
   *          A description of the event.
   * @param timestamp
   *          The timestamp of start of the event.
   * @param duration
   *          The time to live milliseconds until the event expires.
   * @param damage
   *          The damage done to the ship on even failure.
   */
  public Event(int id, String summary, String description, long timestamp, long duration,
      double damage) {
    this.id = id;
    this.summary = summary;
    this.description = description;
    this.timestamp = timestamp;
    this.duration = duration;
    this.timeLeft = duration;
    this.damage = damage;
  }

  /**
   * Getter for the event id.
   * 
   * @return The id of event.
   */
  public int getId() {
    return this.id;
  }

  /**
   * Getter for the event summary/name.
   * 
   * @return Summary/name of the event.
   */
  public String getSummary() {
    return this.summary;
  }

  /**
   * Get the description of the event.
   * 
   * @return Description of event.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Get the timestamp of the event.
   * 
   * @return Timestamp of event.
   */
  public Long getTimestamp() {
    return this.timestamp;
  }

  /**
   * Get the duration of the event.
   * 
   * @return Duration of event.
   */
  public Long getDuration() {
    return this.duration;
  }

  /**
   * Get the time remaining of the event.
   * 
   * @return The time remaining.
   */
  public Long getTimeLeft() {
    return this.timeLeft;
  }

  /**
   * Check whether the event is expired at a given moment in time.
   * 
   * @param time
   *          The moment in time at which to check the expiration.
   * @return Whether the event is expired.
   */
  public boolean isExpired(long time) {
    return (time > (timestamp + duration));
  }

  /**
   * Updates the time left variable for the event.
   * 
   * @param currentTime
   *          the current time of the game
   */
  public void updateTimeLeft(long currentTime) {
    this.timeLeft = this.timestamp - currentTime;
  }

  /**
   * Apply damage to a given Spaceship.
   * 
   * @param spaceship
   *          The Spaceship to apply damage to.
   */
  public void applyDamage(Spaceship spaceship) {
    spaceship.updateHealth(-1 * damage);
  }

  /**
   * Get the damage that will be done to the ship in the case of event failure.
   * 
   * @return The event's damage value.
   */
  public double getDamage() {
    return this.damage;
  }

  /**
   * Allow events to render notifications to the players.
   *
   * @param scene
   *          The scene in which the notification must appear.
   */
  public abstract void notification(Scene scene);
}
