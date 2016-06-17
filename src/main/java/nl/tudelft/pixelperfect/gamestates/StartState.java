package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.gui.MainMenuDisplay;

/**
 * First state that the game loads.
 * 
 * @author David Alderliesten
 * @author Wouter Zirkzee
 */
public class StartState extends GameState {

  private MainMenuDisplay menuInstance;

  /**
   * Constructor for StartState.
   *
   * @param game
   *          Game for which it controls the state.
   */
  public StartState(Game game) {
    super(game);

    // Creating the main menu buttons and text.
    menuInstance = new MainMenuDisplay(game.getAssetManager(), game.getGuiNode(),
        game.getViewPortX(), game.getViewPortY());
  }

  /**
   * Method that handles updating in this state.
   *
   * @param tpf
   *          Time since last frame.
   */
  public void update(float tpf) {
    // Not required at this time.
  }

  /**
   * Method to update the state.
   *
   * @return new state.
   */
  public GameState handleState() {
    if (game.isStartKey()) {
      Game.startGame();
      menuInstance.clearMenu();
      return new PlayState(game);
    }
    return this;
  }

  @Override
  public boolean isRunning() {
    return false;
  }

}
