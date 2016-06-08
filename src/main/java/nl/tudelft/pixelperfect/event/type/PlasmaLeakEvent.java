package nl.tudelft.pixelperfect.event.type;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;

/**
 * A type of event, imposing the problem of a plasma leak.
 * 
 * @author Wouter Zirkzee
 * 
 */

public class PlasmaLeakEvent extends Event {

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
  public PlasmaLeakEvent(int id, String summary, String description, long timestamp, long duration,
      double damage) {
    super(id, summary, description, timestamp, duration, damage);
  }

  @Override
  public EventTypes getType() {
    return EventTypes.PLASMA_LEAK;
  }

  /**
   * Allow events to render notifications to the players.
   *
   * @param game
   *            The current game.
   * @param scene
   *          The scene in which the notification must appear.
   */
  @Override
  public void notification(Game game, Scene scene) {
    Material buttonMat = new Material(game.getAssetManager(), "jmevr/shaders/Unshaded.j3md");
    int parameter = getParameters().getMap().values().iterator().next().getNumberValue();
    Geometry button = scene.getPlasmaEventObjects().get(parameter);

    if ((((int) game.getSpaceship().getTimer() % 2) == 0)
        && !isExpired(System.currentTimeMillis() + 2000)) {
      buttonMat.setColor("Color", ColorRGBA.Black);
    } else {
      buttonMat.setColor("Color", ColorRGBA.Red);

    }
    button.setMaterial(buttonMat);
    game.getAudioPlayer().playSound("PlasmaEvent", false);
  }
}
