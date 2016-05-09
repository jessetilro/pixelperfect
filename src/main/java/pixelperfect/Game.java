package pixelperfect;

import java.io.IOException;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;

import pixelperfect.event.EventScheduler;
import pixelperfect.net.ServerListener;

/**
 * Main class representing an active Game process and creating the JMonkey Environment.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class Game extends SimpleApplication {

  private Spaceship spaceship;
  private EventScheduler scheduler;
  private Server server;

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
    try {
      server = Network.createServer(6143);
      server.start();
      server.addMessageListener(new ServerListener());
    } catch (IOException except) {
      except.printStackTrace();
    }
    spaceship = new Spaceship();
    scheduler = new EventScheduler(0.5);

    scheduler.subscribe(spaceship.getLog());

    // Set up scenegraph.
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
