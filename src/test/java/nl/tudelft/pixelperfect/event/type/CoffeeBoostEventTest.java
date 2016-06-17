package nl.tudelft.pixelperfect.event.type;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jme3.light.Light;
import com.jme3.math.ColorRGBA;

import nl.tudelft.pixelperfect.audio.AudioPlayer;
import nl.tudelft.pixelperfect.event.EventTest;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;
import nl.tudelft.pixelperfect.game.Spaceship;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing the CoffeeBoostEvent class.
 * 
 * @author Jesse Tilro
 */
@SuppressWarnings("PMD")
public class CoffeeBoostEventTest extends EventTest {

  private Game mockedGame;
  private Scene mockedScene;
  private Spaceship mockedShip;
  private Light mockedLight;
  private AudioPlayer mockedAudio;
  
  /**
   * Factory method for testing.
   * 
   * @return CoffeeBoostEvent instance.
   */
  @Override
  public CoffeeBoostEvent createEvent(String summary, String description) {
    return new CoffeeBoostEvent(1, summary, description, 42, 42, 99.42);
  }
  
  /**
   * What the test case should initialize.
   * 
   */
  @Before
  public void setUp() {
    mockedGame = mock(Game.class);
    mockedScene = mock(Scene.class);
    mockedShip = mock(Spaceship.class);
    mockedLight = mock(Light.class);
    when(mockedGame.getSpaceship()).thenReturn(mockedShip);
    when(mockedScene.getLight()).thenReturn(mockedLight);
    mockedAudio = mock(AudioPlayer.class);
    when(mockedGame.getScene()).thenReturn(mockedScene);
    when(mockedGame.getAudioPlayer()).thenReturn(mockedAudio);
  }
  
  /**
   * Tests the type getter.
   * 
   */
  @Test
  public void testGetType() {
    CoffeeBoostEvent coffee = new CoffeeBoostEvent(1, "summary", "description", 42, 42, 99.42);
    assertEquals(EventTypes.COFFEE_BOOST, coffee.getType());
  }
  
  /**
   * Tests to see that the audioplayer is triggered when the method is called.
   * 
   */
  @Test
  public void testOnComplete() {
  }
  
  /**
   * Tests the notification method that sets the light to brown.
   * 
   */
  @Test
  public void testNotificationBrown() {
    CoffeeBoostEvent test = new CoffeeBoostEvent(1, "summary", "description",
        System.currentTimeMillis(), System.currentTimeMillis(), 99.42);
    when(mockedShip.getTimer()).thenReturn(2.0f);
    test.notification(mockedGame, mockedScene);
    verify(mockedLight).setColor(ColorRGBA.Brown);
  }
  
  /**
   * Tests the notification method that sets the light to white.
   * Reason is because the event expired.
   * 
   */
  @Test
  public void testNotificationWhiteExpired() {
    CoffeeBoostEvent test = new CoffeeBoostEvent(1, "summary", "description",
        42, 0, 99.42);
    when(mockedShip.getTimer()).thenReturn(2.0f);
    test.notification(mockedGame, mockedScene);
    verify(mockedLight).setColor(ColorRGBA.White);
  }
  
  /**
   * Tests the notification method that sets the light to white.
   * Reason is because the timer is on an odd value.
   * 
   */
  @Test
  public void testNotificationWhiteTimer() {
    CoffeeBoostEvent test = new CoffeeBoostEvent(1, "summary", "description",
        System.currentTimeMillis(), System.currentTimeMillis(), 99.42);
    when(mockedShip.getTimer()).thenReturn(3.0f);
    test.notification(mockedGame, mockedScene);
    verify(mockedLight).setColor(ColorRGBA.White);
  }

}