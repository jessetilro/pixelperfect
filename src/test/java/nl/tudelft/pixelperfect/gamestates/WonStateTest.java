package nl.tudelft.pixelperfect.gamestates;

import com.jme3.scene.Spatial;
import nl.tudelft.pixelperfect.Game;
import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.event.EventScheduler;
import nl.tudelft.pixelperfect.gui.GameHeadsUpDisplay;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by woute on 5/24/2016.
 */
public class WonStateTest extends GameStateTest {

  private Game mockGame;
  private WonState testState;

  @Before
  public void setUp() {
    mockGame = mock(Game.class);
    testState = new WonState(mockGame);
  }

  /**
   * Test update function.
   */
  @Test
  public void update() {
    //update method is empty for now
  }

  /**
   * Tests for state changes.
   */
  @Test
  public void handleState() {
    handleStateStartKey();
    handleStateThis();
  }

  /**
   * Test that you go to StartState when startkey is pressed.
   */
  private void handleStateStartKey() {
    when(mockGame.isStartKey()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), StartState.class);
  }

  /**
   * Test that you stay in WonState if key is not pressed.
   */
  private void handleStateThis() {
    when(mockGame.isStartKey()).thenReturn(false);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), WonState.class);
  }

}