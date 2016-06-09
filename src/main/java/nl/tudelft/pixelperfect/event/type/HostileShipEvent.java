package nl.tudelft.pixelperfect.event.type;

import com.jme3.font.BitmapText;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;


/**
 * A type of event, imposing the problem of a hostile alien spaceship.
 * 
 * @author Wouter Zirkzee
 * 
 */

public class HostileShipEvent extends Event {

  /**
   * Constructor for HostileShipEvent event.
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
  public HostileShipEvent(int id, String summary, String description, long timestamp, long duration,
      double damage) {
    super(id, summary, description, timestamp, duration, damage);
  }

  @Override
  public EventTypes getType() {
    return EventTypes.HOSTILE_SHIP;
  }

  private boolean notifiedFlag = false;
  @Override
  public void notification(Game game, Scene scene) {
    int xParam = getParameters().get("positionX").getNumberValue();
    int yParam = getParameters().get("positionY").getNumberValue();
    int armorParam = getParameters().get("armor").getNumberValue();
    BitmapText textfield = scene.getHostileEventText();
    textfield.setText("x: " + xParam + "\ny: " + yParam + "\n" + armorParam);


    if (!notifiedFlag) {
      notifiedFlag = true;
      game.getAudioPlayer().playSound("HostileEvent", false);
    }
  }
}
