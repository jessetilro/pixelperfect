package nl.tudelft.pixelperfect;

import java.io.IOException;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import nl.tudelft.pixelperfect.Scene;

import nl.tudelft.pixelperfect.client.ConnectListener;
import nl.tudelft.pixelperfect.client.HelloMessage;
import nl.tudelft.pixelperfect.client.ServerListener;
import nl.tudelft.pixelperfect.event.EventScheduler;

/**
 * Main class representing an active Game process and creating the JMonkey Environment.
 * 
 * @author David Alderliesten
 * @author Floris Doolaard
 * @author Dmitry Malarev
 * @author Jesse Tilro
 * @author Wouter Zirkzee
 *
 */
public class Game extends SimpleApplication {

  private Spaceship spaceship;
  private EventScheduler scheduler;
  private Server server;
  private Scene scene;

  /**
   * Main method bootstrapping the process by constructing this class and initializing a
   * jMonkeyEngine Game.
   * 
   * @param args
   *          The parameters passed to the process.
   */
  public static void main(String[] args) {
    Game app = new Game();
    app.start();
  }

  /**
   * Method initializing the game (e.g. setting up the scenegraph and/or the main menu).
   */
  @Override
  public void simpleInitApp() {
    scene = new Scene(this);
    scene.createMap();
    // increase movement speed
    flyCam.setMoveSpeed(50);

    try {
      server = Network.createServer(6143);
      Serializer.registerClass(HelloMessage.class);
      server.start();
      server.addMessageListener(new ServerListener(), HelloMessage.class);
      server.addConnectionListener(new ConnectListener());

    } catch (IOException except) {
      except.printStackTrace();
    }
    spaceship = new Spaceship();
    scheduler = new EventScheduler(0.5);

    scheduler.subscribe(spaceship.getLog());
  }

  /**
   * Main update loop for the game.
   */
  @Override
  public void simpleUpdate(float tpf) {
    scheduler.update(tpf);
    spaceship.update(tpf);

    if (spaceship.isDead()) {
      this.stop();
    }

    if (spaceship.isVictorious()) {
      System.out.println("Well played, you have completed the game!");
      this.stop();
    }
  }
}
