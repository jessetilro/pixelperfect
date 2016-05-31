package nl.tudelft.pixelperfect.event.type;

/**
 * A type of event, imposing the problem of a lack of caffeine.
 * 
 * @author Jesse Tilro
 * 
 */

public class CoffeeBoostEvent extends Event {

  /**
   * Constructor for PlasmaLeakEvent event.
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
  public CoffeeBoostEvent(int id, String summary, String description, long timestamp, long duration,
      double damage) {
    super(id, summary, description, timestamp, duration, damage);
  }
}
