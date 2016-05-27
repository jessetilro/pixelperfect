package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to test StartState.
 *
 * @author Wouter Zirkzee
 */
@SuppressWarnings("PMD")
public class StartStateTest extends GameStateTest {
  private Game mockGame;
  private StartState testState;

  /**
   * Setup class for testing.
   */
  @Before
  public void setUp() {
    mockGame = mock(Game.class);
    testState = new StartState(mockGame);
  }

  /**
   * Test update function.
   */
  @Test
  public void testUpdate() {
    //update method is empty for now
  }

  /**
   * Test for correct states being returned.
   */
  @Test
  public void testHandleState() {
    testHandleStateStartKey();
    testHandleStateThis();
  }

  /**
   * Test that PlayState is returned when startkey is pressed.
   */
  private void testHandleStateStartKey() {
    when(mockGame.isStartKey()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), PlayState.class);
  }

  /**
   * Test that StartState is returned when nothing is done.
   */
  private void testHandleStateThis() {
    when(mockGame.isStartKey()).thenReturn(false);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), StartState.class);
  }

}
