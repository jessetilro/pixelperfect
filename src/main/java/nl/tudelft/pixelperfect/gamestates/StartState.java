package nl.tudelft.pixelperfect.gamestates;


import nl.tudelft.pixelperfect.game.Game;

/**
 * First state that the game loads.
 *
 * @author Wouter Zirkzee
 */
public class StartState extends GameState {
  

  /**
   * Constructor for StartState.
   *
   * @param game
   *            Game for which it controlls the state.
   */
  public StartState(Game game) {
    super(game);
  }


  /**
   * Method that to update in this state.
   *
   * @param tpf
   *          Time since last frame.
   */
  public void update(float tpf) {
    //render mainmenu or something
  }

  /**
   * Method to update the state.
   *
   * @return
   *        new state.
   */
  public GameState handleState() {
    if (game.isStartKey()) {
      Game.startGame();
      return new PlayState(game);
    }
    return this;
  }
}
