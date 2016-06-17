package nl.tudelft.pixelperfect.event.type;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
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
 * Tesing the HostileShipEvent class.
 * 
 * @author Wouter Zirkzee
 * @author Floris Doolaard
 */
@SuppressWarnings("PMD")
public class HostileShipEventTest extends EventTest {

  /**
   * Factory method for testing.
   * 
   * @return PlasmaLeakEvent class
   */
  @Override
  public HostileShipEvent createEvent(String summary, String description) {
    return new HostileShipEvent(1, summary, description, 42, 42, 99.42);
  }
  
  /**
   * Tests the type getter.
   * 
   */
  @Test
  public void testGetType() {
    HostileShipEvent test = createEvent("summary", "description");
    assertEquals(EventTypes.HOSTILE_SHIP, test.getType());
  }
  
  
  /**
   * Tests the notification method.
   * 
   */
  @Test
  public void testNotification() {
    Scene mockedScene = mock(Scene.class);
    Game mockedGame = mock(Game.class);
    AudioPlayer mockedAudio = mock(AudioPlayer.class);
    EventParameter posX = new EventParameter("positionX", 1);
    EventParameter posY = new EventParameter("positionY", 1);
    EventParameter armor = new EventParameter("armor", 1);
    BitmapText mockedBitMap = mock(BitmapText.class);
    when(mockedGame.getScene()).thenReturn(mockedScene);
    when(mockedGame.getAudioPlayer()).thenReturn(mockedAudio);
    when(mockedScene.getHostileEventText()).thenReturn(mockedBitMap);
    HostileShipEvent test = createEvent("summary", "description");
    test.getParameters().add(posX);
    test.getParameters().add(posY);
    test.getParameters().add(armor);
    test.notification(mockedGame, mockedScene);
    verify(mockedBitMap).setText(anyString()); 
    verify(mockedAudio).playSound(anyString(), anyBoolean());
  }

}
