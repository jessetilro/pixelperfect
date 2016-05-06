package pixelperfect;

import com.jme3.app.SimpleApplication;

import pixelperfect.event.EventScheduler;

/**
 * Main class representing an active Game process and creating the JMonkey
 * Environment.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class Game extends SimpleApplication {

  private Spaceship spaceship;
  private EventScheduler scheduler;

  /**
   * Main method bootstrapping the process by constructing this class and
   * initializing a jMonkeyEngine Game.
   * 
   * @param args
   *          The parameters passed to the process.
   */
  public static void main(String[] args) {
    Game app = new Game();
    app.start();
  }

  /**
   * Method initializing the game (e.g. setting up the scenegraph and/or the
   * main menu).
   */
  @Override
  public void simpleInitApp() {
    spaceship = Spaceship.getInstance();
    scheduler = new EventScheduler(42);

    scheduler.subscribe(spaceship.getLog());

    // Set up scenegraph.
  }

  /**
   * Main update loop for the game.
   */
  @Override
  public void simpleUpdate(float tpf) {
    scheduler.update();
    spaceship.update(tpf);
  }
}
