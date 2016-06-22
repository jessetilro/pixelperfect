package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.game.Game;

/**
 * State for when you have won the game.
 *
 * @author David Alderliesten
 * @author Wouter Zirkzee
 */
public class WonState extends GameState {

  /**
   * Constructor for WonState.
   *
   * @param game
   *          Game for which it controlls the state.
   */
  public WonState(Game game) {
    super(game);
  }

  /**
   * Method that to update in this state.
   *
   * @param tpf
   *          Time since last frame.
   */
  public void update(float tpf) {
    // System.out.println("Well played, you have completed the game!");
  }

  /**
   * Method to update the state.
   *
   * @return new state.
   */
  public GameState handleState() {
    if (game.isReset()) {
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
