package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.game.Game;

/**
 * Abstract class for the other GameStates.
 *
 * @author Wouter Zirkzee
 */
public abstract class GameState {

  @SuppressWarnings({"visibilitymodifier"})
  protected Game game;

  /**
   * Constructor for GameState.
   *
   * @param game
   *            Game for which it controlls the state.
   */
  public GameState(Game game) {
    this.game = game;
  }

  /**
   * Abstract class for what should be updated in the GameState.
   *
   * @param tpf
   *          time that has passed.
   */
  public abstract void update(float tpf);

  /**
   * Abstract class for updating the GameState.
   *
   * @return the new gamestate.
   */
  public abstract GameState handleState();
}