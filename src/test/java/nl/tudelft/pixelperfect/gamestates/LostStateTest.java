package nl.tudelft.pixelperfect.gamestates;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.pixelperfect.game.Game;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test LostState.
 *
 * @author Wouter Zirkzee
 */
@SuppressWarnings("PMD")
public class LostStateTest extends GameStateTest {

  private Game mockGame;
  private LostState testState;

  /**
   * Setup for the test.
   */
  @Before
  public void setUp() {
    mockGame = mock(Game.class);
    testState = new LostState(mockGame);
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
