package nl.tudelft.pixelperfect.audio;

import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioSource;
import nl.tudelft.pixelperfect.Game;

import java.util.HashMap;

/**
 * Class to store and play audio.
 *
 * @author Wouter Zirkzee
 *
 */
public class AudioPlayer {

  private Game mainApp;

  private HashMap<String, AudioNode> sounds;

  public AudioPlayer(Game app){
    mainApp = app;
    sounds = new HashMap<String, AudioNode>();
  }

  /**
   * Add sounds to the HashMap based on the name and location.
   *
   * @param name
   *           Name to be used as key.
   * @param location
   *           Location if the audiofile.
   */
  public void loadSounds(String[] name, String[] location) {
    if (name.length == location.length) {
      for (int i = 0; 0 < name.length; i++) {
        AudioNode audioNode = new AudioNode(mainApp.getAssetManager(), location[i], AudioData.DataType.Buffer);
        sounds.put(name[i], audioNode);
      }
    }
  }

  /**
   * Method to start playing a sound.
   *
   * @param name
   *            Name of the sound you want to play.
   * @param loop
   *            If you want to loop the sound of play it once.
   */
  public void playSound(String name, boolean loop) {
    if (sounds.containsKey(name)) {
      AudioNode sound = sounds.get(name);
      sound.setLooping(loop);
      sounds.get(name).play();
    }
  }

  /**
   * Stop playing a sound, if it is playing.
   *
   * @param name
   *            Name of sound to stop playing.
   */
  public void stopSound(String name) {
    if (sounds.containsKey(name)) {
      if (sounds.get(name).getStatus().equals(AudioSource.Status.Playing)) {
        sounds.get(name).stop();
      }
    }
  }

  /**
   * Get game.
   *
   * @return game
   */
  public Game getGame() {
    return mainApp;
  }

  /**
   * Set a Hashmap with sounds (used for testing).
   *
   * @param sounds
   *             HashMap with String key and AudioNode value.
   */
  public void setSounds(HashMap<String, AudioNode> sounds) {
    this.sounds = sounds;
  }
}
