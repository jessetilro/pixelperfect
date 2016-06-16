package nl.tudelft.pixelperfect.event.type;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;

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

  @Override
  public EventTypes getType() {
    return EventTypes.COFFEE_BOOST;
  }

  private boolean notifiedFlag = false;
  @Override
  public void notification(Game game, Scene scene) {
    if (!notifiedFlag) {
      notifiedFlag = true;
      game.getAudioPlayer().playSound("CoffeeEvent", false);
    }
    if ((((int) game.getSpaceship().getTimer() % 2) == 0)
        && !isExpired(System.currentTimeMillis() + 2000)) {
      scene.getLight().setColor(ColorRGBA.Brown);
    } else {
      scene.getLight().setColor(ColorRGBA.White);
    }
  }

}
