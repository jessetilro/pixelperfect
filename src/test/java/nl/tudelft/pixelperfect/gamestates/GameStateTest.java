package nl.tudelft.pixelperfect.gamestates;

import org.junit.Test;

/**
 * Class to test GameState.
 *
 * @author Wouter Zirkzee
 */
@SuppressWarnings("PMD")
public abstract class GameStateTest {

  /**
   * Abstract method for update.
   */
  @Test
  public abstract void testUpdate();

  /**
   * Abstract method for handleState.
   */
  @Test
  public abstract void testHandleState();

}
