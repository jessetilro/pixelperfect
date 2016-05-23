package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.Game;

/**
 * Created by woute on 5/23/2016.
 */
public class WonState extends GameState {
  public WonState(Game game) {
    super(game);
  }

  public void update(float tpf) {
//    System.out.println("Well played, you have completed the game!");
  }

  public GameState handleState() {
    if (game.isStartKey()){
      return new StartState(game);
    }
    return this;
  }
}
