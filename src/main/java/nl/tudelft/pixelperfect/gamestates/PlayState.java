package nl.tudelft.pixelperfect.gamestates;

import com.jme3.scene.Spatial;
import jmevr.app.VRApplication;
import nl.tudelft.pixelperfect.Game;
import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventScheduler;
import nl.tudelft.pixelperfect.gui.GameHeadsUpDisplay;

/**
 * Created by woute on 5/23/2016.
 */
public class PlayState extends GameState {

  private Spatial observer;
  private Spaceship spaceship;
  private GameHeadsUpDisplay headsUpDisplay;
  private EventScheduler scheduler;

  public PlayState(Game game){
    super(game);
    observer = game.getGameObserver();
    spaceship = game.getSpaceship();
    headsUpDisplay = game.getGameHud();
    scheduler = game.getScheduler();
  }

  public void update(float tpf) {
    updateMovement(tpf);

    scheduler.update(tpf);
    spaceship.update(tpf);

    // Update the in-game heads up display.
    headsUpDisplay.updateHud();

//    for (Event event : spaceship.getLog().getEvents()) {
//      event.notification(game.getScene());
//    }

  }

  public void updateMovement(float tpf) {
    if (game.isMoveForward()) {
      observer.move(VRApplication.getFinalObserverRotation().getRotationColumn(2).mult(tpf * 8f));
    }
    if (game.isMoveBackwards()) {
      observer.move(VRApplication.getFinalObserverRotation().getRotationColumn(2).mult(-tpf * 8f));
    }
    if (game.isRotateLeft()) {
      observer.rotate(0, 0.75f * tpf, 0);
    }
    if (game.isRotateRight()) {
      observer.rotate(0, -0.75f * tpf, 0);
    }
  }


  public GameState handleState() {
    if (spaceship.isVictorious()) {
      return new WonState(game);
    } else if (spaceship.isDead()) {
      return new LostState(game);
//    } else if (game.isPauseKey()) {
//      return new PauseState(game);
    }
      return this;
  }
}
