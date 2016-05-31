package nl.tudelft.pixelperfect.event;

/**
 * A type of Event, imposing the problem of a fire outbreak.
 * 
 * @author Wouter Zirkzee
 * 
 */
public class FireOutbreakEvent extends Event {

  /**
   * Constructor for FireOutbreakEvent event.
   *
   * @param id
   *          The desired id.
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
  public FireOutbreakEvent(int id, String summary, String description, long timestamp,
      long duration, double damage) {
    super(id, summary, description, timestamp, duration, damage);
  }
}
