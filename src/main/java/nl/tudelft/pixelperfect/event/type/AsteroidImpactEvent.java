package nl.tudelft.pixelperfect.event.type;

import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;

/**
 * A type of Event, imposing a dangerous Asteroid Field.
 *
 * @author David Alderliesten
 * @author Wouter Zirkzee
 *
 */

public class AsteroidImpactEvent extends Event {

  /**
   * Constructor for AstroidField event.
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
  public AsteroidImpactEvent(int id, String summary, String description, long timestamp,
      long duration, double damage) {
    super(id, summary, description, timestamp, duration, damage);
  }

  @Override
  public EventTypes getType() {
    return EventTypes.ASTEROID_IMPACT;
  }

  /**
   * Allow events to render notifications to the players.
   *
   * @param game
   *          The current game.
   * @param scene
   *          The scene in which the notification must appear.
   */
  @Override
  public void notification(Game game, Scene scene) {
    if (!getNotifiedFlag()) {
      scene.getAsteroidEventLabel().setText(
          "ASTEROID DAMAGE: " + getParameters().get("locationDamageImpact").getValueDescription());

      setNotifiedFlag(true);
      game.getAudioPlayer().playSound("AsteroidEvent", false);
    }
  }
  
  @Override
  public void onComplete(Game game) {
  }

}
