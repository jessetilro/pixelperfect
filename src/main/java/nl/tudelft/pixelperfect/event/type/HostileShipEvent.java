package nl.tudelft.pixelperfect.event.type;

import com.jme3.font.BitmapText;

import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;

/**
 * A type of event, imposing the problem of a hostile alien spaceship.
 * 
 * @author David Alderliesten
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

  @Override
  @SuppressWarnings("PMD") 
  public void notification(Game game, Scene scene) {
    if (!getNotifiedFlag()) {
      String xParam = getParameters().get("positionX").getValueDescription();
      String yParam = getParameters().get("positionY").getValueDescription();
      String armorParam = getParameters().get("armor").getValueDescription();
      BitmapText textfield = game.getScene().getHostileEventText();
      
      StringBuilder sb = new StringBuilder();
      
      sb.append("x: ").append(xParam).append("\ny: ").append(yParam).append("\n")
          .append(armorParam);
      textfield.setText(sb.toString());

      if (!getNotifiedFlag()) {
        setNotifiedFlag(true);
        game.getAudioPlayer().playSound("HostileEvent", false);
      }
    }
  }

  /**
   * Plays an explosion sound when a ship is successfully destroyed.
   */
  @Override
  public void onComplete(Game game) {
  }

}
