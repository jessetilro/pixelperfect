package nl.tudelft.pixelperfect.gamestates;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.pixelperfect.game.Game;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test WonState.
 *
 * @author Wouter Zirkzee
 */
@SuppressWarnings("PMD")
public class WonStateTest extends GameStateTest {

  private Game mockGame;
  private WonState testState;

  /**
   * Setup classes for testing.
   */
  @Before
  public void setUp() {
    mockGame = mock(Game.class);
    testState = new WonState(mockGame);
  }

  /**
   * Test update function.
   */
  @Test
  public void testUpdate() {
    // update method is empty for now
  }

  /**
   * Tests for state changes.
   */
  @Test
  public void testHandleState() {
    handleStateThis();
  }

  /**
   * Test that you stay in WonState when the startkey is not pressed.
   */
  private void handleStateThis() {
    when(mockGame.isStartKey()).thenReturn(false);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), WonState.class);
  }

}
