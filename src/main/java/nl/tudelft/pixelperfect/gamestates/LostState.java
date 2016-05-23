package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.Game;

/**
 * Created by woute on 5/23/2016.
 */
public class LostState extends GameState {

  public LostState(Game game) {
    super(game);
  }

  public void update(float tpf){
//    System.out.println("Lost");
  }

  public GameState handleState() {
    if (game.isStartKey()){
      return new StartState(game);
    }
    return this;
  }
}
