package nl.tudelft.pixelperfect.audio;

import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioSource;
import nl.tudelft.pixelperfect.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertSame;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Class to test AudioPlayer
 *
 * @author Wouter Zirkzee
 */
public class AudioPlayerTest {
  private AudioPlayer audioPlayer;

  /**
   * Setup the tests.
   */
  @Before
  public void setUp() {
    audioPlayer = mock(AudioPlayer.class, CALLS_REAL_METHODS);
  }

  /**
   * Test constructor, because spy/mock doens't cover constructor.
   */
  @Test
  public void testConstructor() {
    Game mockGame = mock(Game.class);
    audioPlayer = new AudioPlayer(mockGame);
    assertSame(mockGame, audioPlayer.getGame());
  }

  /**
   * Test nothing is added when sizes are not equal.
   */
  @Test
  public void loadSoundsNotEqualSize() {
    HashMap<String, AudioNode> testMap = mock(HashMap.class);
    audioPlayer.setSounds(testMap);
    String[] names = {"1", "2"};
    String[] locations = {"1"};
    audioPlayer.loadSounds(names, locations);

    verify(testMap, never()).put(anyString(), any(AudioNode.class));
  }

  /**
   * Verify the sound gets played.
   */
  @Test
  public void playSound() {
    HashMap<String, AudioNode> testMap = new HashMap<String, AudioNode>();
    AudioNode testAudio = mock(AudioNode.class);
    testMap.put("test",testAudio);
    audioPlayer.setSounds(testMap);

    audioPlayer.playSound("test", false);

    verify(testAudio).setLooping(false);
    verify(testAudio).play();
  }

  /**
   * Verify stop issn't called when sound is not playing.
   */
  @Test
  public void stopSoundWhenNotPlaying() {
    HashMap<String, AudioNode> testMap = new HashMap<String, AudioNode>();
    AudioNode testAudio = mock(AudioNode.class);
    when(testAudio.getStatus()).thenReturn(AudioSource.Status.Stopped);
    testMap.put("test", testAudio);
    audioPlayer.setSounds(testMap);

    audioPlayer.stopSound("test");
    verify(testAudio, never()).stop();
  }

  /**
   * Verify stop is called when sound is playing.
   */
  @Test
  public void stopSoundWhenPlaying() {
    HashMap<String, AudioNode> testMap = new HashMap<String, AudioNode>();
    AudioNode testAudio = mock(AudioNode.class);
    when(testAudio.getStatus()).thenReturn(AudioSource.Status.Playing);
    testMap.put("test", testAudio);
    audioPlayer.setSounds(testMap);

    audioPlayer.stopSound("test");

    verify(testAudio).stop();
  }

}