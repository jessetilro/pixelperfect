package nl.tudelft.pixelperfect.event.type;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jme3.font.BitmapText;
import nl.tudelft.pixelperfect.audio.AudioPlayer;
import nl.tudelft.pixelperfect.event.EventTest;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.parameter.EventParameterCollection;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;
import org.junit.Test;






/**
 * Tesing the AsteroidFieldEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class AsteroidImpactEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public AsteroidImpactEvent createEvent(String summary, String description) {
    return new AsteroidImpactEvent(1, summary, description, 42, 42, 99.42);
  }
  
  /**
   * Tests the type getter.
   * 
   */
  @Test
  public void testGetType() {
    AsteroidImpactEvent test = createEvent("summary", "description");
    assertEquals(EventTypes.ASTEROID_IMPACT, test.getType());
  }
  
  /**
   * Tests the notification method.
   * 
   */
  @Test
  public void testNotification() {
    Scene mockedScene = mock(Scene.class);
    Game mockedGame = mock(Game.class);
    EventParameter param = new EventParameter("locationDamageImpact", 1);
    BitmapText mockedBitMap = mock(BitmapText.class);
    AudioPlayer mockedAudio = mock(AudioPlayer.class);
    when(mockedScene.getAsteroidEventLabel()).thenReturn(mockedBitMap);
    when(mockedGame.getAudioPlayer()).thenReturn(mockedAudio);
    AsteroidImpactEvent test = createEvent("summary", "description");
    test.getParameters().add(param);
    test.notification(mockedGame, mockedScene);
    verify(mockedBitMap).setText(anyString());
    verify(mockedAudio).playSound(anyString(), anyBoolean());
    
  }
  
  /**
   * Tests the onComplete method.
   * 
   */
  @Test
  public void testOnComplete() {
    //left empty
  }

}
