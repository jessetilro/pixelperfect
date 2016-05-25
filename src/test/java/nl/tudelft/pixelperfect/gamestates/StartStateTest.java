package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by woute on 5/24/2016.
 */
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
  public void update() {
    //update method is empty for now
  }

  /**
   * Test for correct states being returned.
   */
  @Test
  public void handleState() {
    handleStateStartKey();
    handleStateThis();
  }

  /**
   * Test that PlayState is returned when startkey is pressed.
   */
  private void handleStateStartKey() {
    when(mockGame.isStartKey()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), PlayState.class);
  }

  /**
   * Test that StartState is returned when nothing is done.
   */
  private void handleStateThis() {
    when(mockGame.isStartKey()).thenReturn(false);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), StartState.class);
  }

}