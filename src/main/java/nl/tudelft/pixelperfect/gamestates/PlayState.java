package nl.tudelft.pixelperfect.gamestates;

import com.jme3.scene.Spatial;

import jmevr.app.VRApplication;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventScheduler;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Settings;
import nl.tudelft.pixelperfect.game.Spaceship;
import nl.tudelft.pixelperfect.gui.DebugHeadsUpDisplay;
import nl.tudelft.pixelperfect.gui.GameHeadsUpDisplay;

/**
 * State for when you are playing the game.
 *
 * @author David Alderliesten
 * @author Wouter Zirkzee
 */
public class PlayState extends GameState {

  private Spatial observer;
  private Spaceship spaceship;
  private DebugHeadsUpDisplay debugDisplay;
  private GameHeadsUpDisplay gameDisplay;
  private EventScheduler scheduler;
  private Game game;

  /**
   * Constructor for PlayState.
   *
   * @param game
   *          Game for which it controlls the state.
   */
  public PlayState(Game game) {
    super(game);
    this.game = game;
    observer = game.getGameObserver();
    spaceship = game.getSpaceship();
    debugDisplay = game.getDebugHud();
    gameDisplay = game.getGameHud();
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

    // Refresh the game HUD.
    gameDisplay.updateHud();

    // If debug mode is enabled, activate the debug update.
    if (Settings.isDebug()) {
      debugDisplay.updateHud();
    } else {
      debugDisplay.clearHud();
    }

    for (EventTypes eventType : EventTypes.values()) {
      Event event = spaceship.getLog().getFirst(eventType);
      if (event != null) {
        event.notification(game, game.getScene());
      } else {
        eventType.resetNotification(game.getScene());
      }
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
    if (game.isDebugOnTrigger()) {
      Settings.setIsDebug(true);
    } else if (game.isDebugOffTrigger()) {
      Settings.setIsDebug(false);
    }
  }

  /**
   * Method to update the state.
   *
   * @return new state.
   */
  public GameState handleState() {
    if (game.isReset()) {
      Game.resetGame();
      return new StartState(game);
    }
    if (spaceship.isVictorious()) {
      return new WonState(game);
    } else if (spaceship.isDead()) {
      return new LostState(game);
    }
    return this;
  }

  @Override
  public boolean isRunning() {
    return true;
  }
}
