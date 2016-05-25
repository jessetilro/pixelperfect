package nl.tudelft.pixelperfect.gamestates;

import org.junit.Test;

/**
 * Created by woute on 5/24/2016.
 */
public abstract class GameStateTest {

  /**
   * Abstract method for update.
   */
  @Test
  public abstract void update();

  /**
   * Abstract method for handleState.
   */
  @Test
  public abstract void handleState();

}