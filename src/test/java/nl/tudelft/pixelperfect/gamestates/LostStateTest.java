package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.Game;
import org.junit.Before;
import org.junit.Test;

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
    //update method is empty for now.
  }

  /**
   * Test handleState.
   */
  @Test
  public void testHandleState() {
    testHandleStateStartKey();
    testHandleStateThis();
  }

  /**
   * Test branch for new StartState.
   */
  @Test
  public void testHandleStateStartKey() {
    when(mockGame.isStartKey()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), StartState.class);
  }

  /**
   * Test branch for LostState.
   */
  @Test
  public void testHandleStateThis() {
    when(mockGame.isStartKey()).thenReturn(false);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), LostState.class);
  }
}
