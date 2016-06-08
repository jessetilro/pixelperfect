package nl.tudelft.pixelperfect.event.type;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;

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

  @Override
  public EventTypes getType() {
    return EventTypes.FIRE_OUTBREAK;
  }

  @Override
  public void notification(Game game, Scene scene) {
    Material buttonMat = new Material(game.getAssetManager(), "jmevr/shaders/Unshaded.j3md");
//    Geometry button = scene.getAstroidEventObjects().get(
//        getParameters().get("approach").getNumberValue());

    if ((((int) game.getSpaceship().getTimer() % 2) == 0)
        && !isExpired(System.currentTimeMillis() + 2000)) {
      buttonMat.setColor("Color", ColorRGBA.Yellow);
    } else {
      buttonMat.setColor("Color", ColorRGBA.Red);

    }
//    button.setMaterial(buttonMat);
    game.getAudioPlayer().playSound("FireEvent", false);
  }
}
