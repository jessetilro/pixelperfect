package nl.tudelft.pixelperfect.event;


import nl.tudelft.pixelperfect.Scene;

/**
 * A type of Event, imposing the problem of a fire outbreak.
 * 
 * @author Wouter Zirkzee
 * 
 */
public class FireEvent extends Event {

  /**
   * Constructor for FireEvent event.
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
  public FireEvent(int id, String summary, String description, long timestamp, long duration,
      double damage) {
    super(id, summary, description, timestamp, duration, damage);
  }

  
  /**
   * Show a specific notification for this event.
   * 
   * @param scene
   *          Scene to be mutated.
   */
  public void notification(Scene scene) {
    // TODO
  }
}
