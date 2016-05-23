package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.Game;

/**
 * Created by woute on 5/23/2016.
 */
public abstract class GameState {

  protected Game game;

  public GameState(Game game) {
    this.game = game;
  }

  public abstract void update(float tpf);

  public abstract GameState handleState();
}
