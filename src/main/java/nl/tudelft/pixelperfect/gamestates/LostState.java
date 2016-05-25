package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.Game;

/**
 * State for when the game is lost.
 * Created by woute on 5/23/2016.
 */
public class LostState extends GameState {

  /**
   * Constructor for LostState.
   * @param game
   *            Game for which it controlls the state.
   */
  public LostState(Game game) {
    super(game);
  }

  /**
   * Method that to update in this state.
   * @param tpf
   *          Time since last frame.
   */
  public void update(float tpf) {
    //render lost screen
  }

  /**
   * Method to update the state.
   * @return
   *        new state.
   */
  public GameState handleState() {
    if (game.isStartKey()) {
      return new StartState(game);
    }
    return this;
  }
}
