package nl.tudelft.pixelperfect.gamestates;

import com.jme3.scene.Spatial;
import jmevr.app.VRApplication;
import nl.tudelft.pixelperfect.Constants;
import nl.tudelft.pixelperfect.Game;
import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.event.EventScheduler;
import nl.tudelft.pixelperfect.gui.DebugHeadsUpDisplay;

/**
 * State for when you are playing the game.
 *
 * @author Wouter Zirkzee
 */
public class PlayState extends GameState {

  private Spatial observer;
  private Spaceship spaceship;
  private DebugHeadsUpDisplay debugDisplay;
  private EventScheduler scheduler;

  /**
   * Constructor for PlayState.
   *
   * @param game
   *          Game for which it controlls the state.
   */
  public PlayState(Game game) {
    super(game);
    observer = game.getGameObserver();
    spaceship = game.getSpaceship();
    debugDisplay = game.getGameHud();
    scheduler = game.getScheduler();
  }

  /**
   * Method that to update in this state.
   *
   * @param tpf
   *          Time since last frame.
   */
  public void update(float tpf) {
    updateMovement(tpf);

    scheduler.update(tpf);
    spaceship.update(tpf);

    // If debug mode is enabled, activate the debug update.
    if (Constants.isDebug) {
      debugDisplay.updateHud();
    } else {
      debugDisplay.clearHud();
    }
  }

  /**
   * Method to check for movement.
   *
   * @param tpf
   *          Time since last check.
   */
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
    if (game.isDebugTrigger()) {
      Constants.isDebug = !Constants.isDebug;
    }
  }

  /**
   * Method to update the state.
   *
   * @return new state.
   */
  public GameState handleState() {
    if (spaceship.isVictorious()) {
      return new WonState(game);
    } else if (spaceship.isDead()) {
      return new LostState(game);
    }
    return this;
  }
}
