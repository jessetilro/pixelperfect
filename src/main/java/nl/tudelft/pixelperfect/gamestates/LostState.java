package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.game.Game;

/**
 * State for when you have lost the game.
 *
 * @author Wouter Zirkzee
 */
public class LostState extends GameState {

  /**
   * Constructor for LostState.
   *
   * @param game
   *          Game for which it controlls the state.
   */
  public LostState(Game game) {
    super(game);
  }

  /**
   * Method that to update in this state.
   *
   * @param tpf
   *          Time since last frame.
   */
  public void update(float tpf) {
    // render lost screen
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
    if (game.isStartKey()) {
      return new StartState(game);
    }
    return this;
  }
}
