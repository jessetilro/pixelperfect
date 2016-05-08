package pixelperfect;

import pixelperfect.event.EventListener;
import pixelperfect.event.EventLog;
import pixelperfect.route.Route;
import pixelperfect.route.RouteGenerator;

/**
 * The spaceship the players are controlling and guiding along a given route through space.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class Spaceship {

  private static final int STARTING_HEALTH = 100;

  private double health;
  private Route route;
  private EventListener log;
  private long timer;
  private boolean victorious;

  /**
   * Construct a new Spaceship instance.
   */
  public Spaceship() {
    this.health = STARTING_HEALTH;
    this.victorious = false;
    RouteGenerator rg = RouteGenerator.getInstance();
    this.route = rg.generateRoute();
    this.log = new EventLog(this);
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
   * A getter for the ship's health.
   * 
   * @return the ship's health
   */
  public double getHealth() {
    return this.health;
  }

  /**
   * Checks if the ship has 0 health.
   * 
   * @return a boolean that determines whether the ship is dead or not
   */
  public boolean isDead() {
    return (this.health == 0);
  }

  /**
   * Checks if the ship has reached the end of the Route.
   * 
   * @return A Boolean pointing out Victory.
   */
  public boolean isVictorious() {
    return victorious;
  }

  /**
   * Update the Spaceship and related model classes for the step in the game loop.
   * 
   * @param tpf
   *          Time per frame.
   */
  public void update(float tpf) {
    if (route.isCompleted(timer)) {
      this.victorious = true;
    }
    this.timer += tpf;
    log.update();
  }

}
