package nl.tudelft.pixelperfect.gamestates;

import nl.tudelft.pixelperfect.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by woute on 5/24/2016.
 */
public class StartStateTest extends GameStateTest {
  private Game mockGame;
  private StartState testState;

  @Before
  public void setUp() {
    mockGame = mock(Game.class);
    testState = new StartState(mockGame);
  }

  @Test
  public void update() {

  }

  @Test
  public void handleState() {
    handleStateStartKey();
    handleStateThis();
  }

  private void handleStateStartKey() {
    when(mockGame.isStartKey()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), PlayState.class);
  }

  private void handleStateThis() {
    when(mockGame.isStartKey()).thenReturn(false);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), StartState.class);
  }

}