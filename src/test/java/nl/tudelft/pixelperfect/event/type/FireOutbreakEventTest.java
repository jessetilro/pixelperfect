package nl.tudelft.pixelperfect.event.type;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jme3.font.BitmapText;

import nl.tudelft.pixelperfect.audio.AudioPlayer;
import nl.tudelft.pixelperfect.event.EventTest;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;
import org.junit.Test;


/**
 * Tesing the FireEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class FireOutbreakEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public FireOutbreakEvent createEvent(String summary, String description) {
    return new FireOutbreakEvent(1, summary, description, 42, 42, 99.42);
  }

  /**
   * Tests the type getter.
   * 
   */
  @Test
  public void testGetType() {
    FireOutbreakEvent test = createEvent("summary", "description");
    assertEquals(EventTypes.FIRE_OUTBREAK, test.getType());
  }
  
  /**
   * Tests the notification method.
   * 
   */
  @Test
  public void testNotification() {
    Scene mockedScene = mock(Scene.class);
    EventParameter param = new EventParameter("location", 1);
    EventParameter water = new EventParameter("water", 1);
    BitmapText mockedBitMap = mock(BitmapText.class);
    AudioPlayer mockedAudio = mock(AudioPlayer.class);
    Game mockedGame = mock(Game.class);
    when(mockedGame.getScene()).thenReturn(mockedScene);
    when(mockedGame.getAudioPlayer()).thenReturn(mockedAudio);
    when(mockedScene.getPlasmaEventLabel()).thenReturn(mockedBitMap);
    when(mockedScene.getFireEventLabel()).thenReturn(mockedBitMap);
    FireOutbreakEvent test = createEvent("summary", "description");
    test.getParameters().add(param);
    test.getParameters().add(water);
    test.notification(mockedGame, mockedScene);
    verify(mockedBitMap).setText(anyString()); 
  }
  
  /**
   * Tests the onComplete method.
   * 
   */
  @Test
  public void testOnComplete() {
    //left Empty
  }
}