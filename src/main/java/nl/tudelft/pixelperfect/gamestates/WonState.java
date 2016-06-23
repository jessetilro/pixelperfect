package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.gui.WonHeadsUpDisplay;

/**
 * State for when you have won the game.
 *
 * @author David Alderliesten
 * @author Wouter Zirkzee
 */
public class WonState extends GameState {

  private WonHeadsUpDisplay menuInstance;

  /**
   * Constructor for WonState.
   *
   * @param game
   *          Game for which it controlls the state.
   */
  public WonState(Game game) {
    super(game);

    menuInstance = new WonHeadsUpDisplay(game.getAssetManager(), game.getGuiNode(),
        game.getViewPortX(), game.getViewPortY());
  }

  /**
   * Method that to update in this state.
   *
   * @param tpf
   *          Time since last frame.
   */
  public void update(float tpf) {
  }

  /**
   * Method to update the state.
   *
   * @return new state.
   */
  public GameState handleState() {
    if (game.isReset()) {
      game.getGuiNode().detachAllChildren();

      game.resetGame();

      return new StartState(game);
    }
    return this;
  }

  @Override
  public boolean isRunning() {
    return false;
  }
}
