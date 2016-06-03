package nl.tudelft.pixelperfect.gamestates;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.jme3.scene.Spatial;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import nl.tudelft.pixelperfect.event.EventScheduler;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Settings;
import nl.tudelft.pixelperfect.game.Spaceship;
import nl.tudelft.pixelperfect.gui.DebugHeadsUpDisplay;
import nl.tudelft.pixelperfect.gui.GameHeadsUpDisplay;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Class to test PlayState.
 *
 * @author David Alderliesten
 * @author Wouter Zirkzee
 */
@SuppressFBWarnings("URF_UNREAD_FIELD")
@SuppressWarnings("PMD")
public class PlayStateTest extends GameStateTest {

  private Game mockGame;
  private PlayState testState;
  private Spatial mockObserver;
  private EventScheduler mockScheduler;
  private Spaceship mockSpaceship;
  private DebugHeadsUpDisplay mockDebugUp;
  private GameHeadsUpDisplay mockHeadsUp;
  private Settings testSettings;

  /**
   * Setup the classes for testing.
   */
  @Before
  public void setUp() {
    mockGame = mock(Game.class, Mockito.CALLS_REAL_METHODS);
    mockScheduler = mock(EventScheduler.class);
    mockObserver = mock(Spatial.class);
    mockSpaceship = mock(Spaceship.class);
    mockDebugUp = mock(DebugHeadsUpDisplay.class);
    mockHeadsUp = mock(GameHeadsUpDisplay.class);
    mockGame.setGameObserver(mockObserver);
    mockGame.setScheduler(mockScheduler);
    mockGame.setSpaceship(mockSpaceship);
    mockGame.setHeadsUpDisplay(mockHeadsUp);
    mockGame.setDebugDisplay(mockDebugUp);
    testSettings = Settings.getInstance();
    testState = new PlayState(mockGame);
  }

  /**
   * Test update function with debug enabled.
   */
  @Test
  public void testUpdate() {
    testSettings.setIsDebug(true);

    testState.update(1f);
    verify(mockScheduler).update(1f);
    verify(mockSpaceship).update(anyFloat());
    verify(mockHeadsUp).updateHud();
    verify(mockDebugUp).updateHud();
  }

  /**
   * Test update function without debug enabled.
   */
  @Test
  public void testUpdateWithoutDebug() {
    testSettings.setIsDebug(false);

    testState.update(1f);
    verify(mockScheduler).update(1f);
    verify(mockSpaceship).update(anyFloat());
    verify(mockHeadsUp).updateHud();

    verify(mockDebugUp, Mockito.times(0)).updateHud();
  }

  /**
   * Test for move forward.
   */
  @Test
  public void testUpdateMoveForward() {
    when(mockGame.isMoveForward()).thenReturn(false);
    testState.updateMovement(1f);
    verifyNoMoreInteractions(mockObserver);
  }

  /**
   * Test for move backwards.
   */
  @Test
  public void testUpdateMoveBackwards() {
    when(mockGame.isMoveBackwards()).thenReturn(false);
    testState.updateMovement(1f);
    verifyNoMoreInteractions(mockObserver);
  }

  /**
   * Test for rotation left.
   */
  @Test
  public void testUpdateRotateLeft() {
    when(mockGame.isRotateLeft()).thenReturn(true);
    when(mockObserver.rotate(anyFloat(), anyFloat(), anyFloat())).thenReturn(mock(Spatial.class));
    testState.updateMovement(1f);
    verify(mockObserver).rotate(anyFloat(), anyFloat(), anyFloat());
  }

  /**
   * Test for rotation right.
   */
  @Test
  public void testUpdateRotateRight() {
    when(mockGame.isRotateRight()).thenReturn(true);
    when(mockObserver.rotate(anyFloat(), anyFloat(), anyFloat())).thenReturn(mock(Spatial.class));
    testState.updateMovement(1f);
    verify(mockObserver).rotate(anyFloat(), anyFloat(), anyFloat());
  }

  /**
   * Test that after you won WonState is returned.
   */
  @Test
  public void testHandleStateWon() {
    when(mockSpaceship.isVictorious()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), WonState.class);
  }

  /**
   * Test that after you lost LoseState is returned.
   */
  @Test
  public void testHandleStateLost() {
    when(mockSpaceship.isDead()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), LostState.class);
  }

  /**
   * Test that when nothing changed PlayState is returned.
   */
  @Test
  public void testHandleState() {
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), PlayState.class);
  }
}