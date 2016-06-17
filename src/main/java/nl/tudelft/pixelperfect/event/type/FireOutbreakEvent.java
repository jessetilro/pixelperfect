package nl.tudelft.pixelperfect.event.type;

import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;

/**
 * A type of Event, imposing the problem of a fire outbreak.
 * 
 * @author David Alderliesten
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

  @Override
  public EventTypes getType() {
    return EventTypes.FIRE_OUTBREAK;
  }

  /**
   * Plays an explosion sound when a ship is successfully destroyed.
   */
  @Override
  public void onComplete(Game game) {
    game.getAudioPlayer().playSound("CompleteFireEvent", false);
  }

  @Override
  public void notification(Game game, Scene scene) {
    if (!getNotifiedFlag()) {
      scene.getFireEventLabel()
          .setText("FIRE WARNING: " + (getParameters().get("location").getValueDescription()));

      setNotifiedFlag(true);
    }
  }

}
