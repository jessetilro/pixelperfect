package pixelperfect.spaceship;

import com.jme3.system.Timer;
import pixelperfect.event.EventListener;
import pixelperfect.event.EventLog;

/**
 * The spaceship the players are controlling and guiding along a given route through space.
 * 
 * @author Jesse Tilro
 *
 */
public class Spaceship {

  private static final int STARTING_HEALTH = 100;

  private double health;
  private Route route;
  private EventListener log;
  private float timer;

  /**
   * Construct a new Spaceship instance.
   */
  public Spaceship() {
    this.health = STARTING_HEALTH;
    this.route = Route.generateRoute();
    this.log = new EventLog();
    this.timer = 0;
  }

  /**
   * Get the spaceship's log of events.
   * 
   * @return An EventListener.
   */
  public EventListener getLog() {
    return log;
  }

  /**
   * Update the ship's health status.
   * 
   * @param delta
   *          The amount to increment or decrement the health value with.
   */
  public void updateHealth(double delta) {
    this.health += delta;
    if (this.health < 0) {
      this.health = 0;
    }
  }

  /**
   * Update the Spaceship and related model classes for the step in the game loop.
   * 
   * @param tpf
   *          Time per frame.
   */
  public void update(float tpf) {
    this.timer += tpf;
  }

}
