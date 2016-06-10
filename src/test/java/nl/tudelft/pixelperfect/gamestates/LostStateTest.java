package nl.tudelft.pixelperfect.gamestates;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.pixelperfect.game.Game;

import static org.mockito.Mockito.mock;

/**
 * Class to test LostState.
 *
 * @author Wouter Zirkzee
 */
@SuppressWarnings("PMD")
public class LostStateTest extends GameStateTest {

  private Game mockGame;

  /**
   * Setup for the test.
   */
  @Before
  public void setUp() {
    mockGame = mock(Game.class);
  }

  /**
   * Test update function.
   */
  @Test
  public void testUpdate() {
    // update method is empty for now.
  }

  /**
   * Test handleState.
   */
  @Test
  public void testHandleState() {
    // Emptied due to startState untestability.
  }
}
