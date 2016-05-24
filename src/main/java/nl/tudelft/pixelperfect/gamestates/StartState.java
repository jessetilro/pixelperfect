package nl.tudelft.pixelperfect.gamestates;


import nl.tudelft.pixelperfect.Game;

/**
 * Created by woute on 5/23/2016.
 */
public class StartState extends GameState {

  public StartState(Game game){
    super(game);
  }

  public void update(float tpf) {
    //render mainmenu or something
  }

  public GameState handleState() {
    if (game.isStartKey()) {
      return new PlayState(game);
    }
    return this;
  }
}
