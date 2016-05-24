package nl.tudelft.pixelperfect.gamestates;

import com.jme3.scene.Spatial;
import nl.tudelft.pixelperfect.Game;
import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.event.EventScheduler;
import nl.tudelft.pixelperfect.gui.GameHeadsUpDisplay;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.*;

/**
 * Created by woute on 5/24/2016.
 */
public class PlayStateTest extends GameStateTest {

  private Game mockGame;
  private PlayState testState;
  private Spatial mockObserver;
  private EventScheduler mockScheduler;
  private Spaceship mockSpaceship;
  private GameHeadsUpDisplay mockHeadsUp;

  @Before
  public void setUp() throws Exception {
    mockGame = mock(Game.class, Mockito.CALLS_REAL_METHODS);
    mockScheduler = mock(EventScheduler.class);
    mockObserver = mock(Spatial.class);
    mockSpaceship = mock(Spaceship.class);
    mockHeadsUp = mock(GameHeadsUpDisplay.class);
    mockGame.setGameObserver(mockObserver);
    mockGame.setScheduler(mockScheduler);
    mockGame.setSpaceship(mockSpaceship);
    mockGame.setHeadsUpDisplay(mockHeadsUp);
    testState = new PlayState(mockGame);
  }

  @Test
  public void update() {
    testState.update(1f);
    verify(mockScheduler).update(1f);
    verify(mockSpaceship).update(anyFloat());
    verify(mockHeadsUp).updateHud();
  }

  @Test
  public void updateMoveForward() {
    when(mockGame.isMoveForward()).thenReturn(false);
//    when(mockObserver.move(anyFloat(), anyFloat(), anyFloat())).thenReturn(mock(Spatial.class));
    testState.updateMovement(1f);
    verify(mockGame).isMoveForward();
//    verify(mockObserver).move(anyFloat(), anyFloat(), anyFloat());
  }

  @Test
  public void updateMoveBackwards() {
    when(mockGame.isMoveBackwards()).thenReturn(false);
//    when(mockObserver.move(anyFloat(), anyFloat(), anyFloat())).thenReturn(mock(Spatial.class));
    testState.updateMovement(1f);
    verify(mockGame).isMoveBackwards();
//    verify(mockObserver).move(anyFloat(), anyFloat(), anyFloat());
  }

  @Test
  public void updateRotateLeft() {
    when(mockGame.isRotateLeft()).thenReturn(true);
    when(mockObserver.rotate(anyFloat(), anyFloat(), anyFloat())).thenReturn(mock(Spatial.class));
    testState.updateMovement(1f);
    verify(mockGame).isRotateLeft();
    verify(mockObserver).rotate(anyFloat(), anyFloat(), anyFloat());
  }

  @Test
  public void updateRotateRight() {
    when(mockGame.isRotateRight()).thenReturn(true);
    when(mockObserver.rotate(anyFloat(), anyFloat(), anyFloat())).thenReturn(mock(Spatial.class));
    testState.updateMovement(1f);
    verify(mockGame).isRotateRight();
    verify(mockObserver).rotate(anyFloat(), anyFloat(), anyFloat());
  }

  @Test
  public void handleStateWon() {
    when(mockSpaceship.isVictorious()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), WonState.class);
  }

  @Test
  public void handleStateLost() {
    when(mockSpaceship.isDead()).thenReturn(true);
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), LostState.class);
  }

  @Test
  public void handleState() {
    GameState newState = testState.handleState();
    assertSame(newState.getClass(), PlayState.class);
  }

}