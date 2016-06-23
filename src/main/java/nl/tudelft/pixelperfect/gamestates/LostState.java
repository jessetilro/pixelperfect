package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.gui.LossHeadsUpDisplay;
import nl.tudelft.pixelperfect.gui.WonHeadsUpDisplay;

/**
 * State for when you have lost the game.
 *
 * @author David Alderliesten
 * @author Wouter Zirkzee
 */
public class LostState extends GameState {
  
  private LossHeadsUpDisplay menuInstance;

  /**
   * Constructor for LostState.
   *
   * @param game
   *          Game for which it controls the state.
   */
  public LostState(Game game) {
    super(game);
    
    menuInstance = new LossHeadsUpDisplay(game.getAssetManager(), game.getGuiNode(),
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
