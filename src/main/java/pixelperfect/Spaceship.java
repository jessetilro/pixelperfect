package pixelperfect;

import pixelperfect.event.EventListener;
import pixelperfect.event.EventLog;
import pixelperfect.route.Route;
import pixelperfect.route.RouteGenerator;

/**
 * The spaceship the players are controlling and guiding along a given route
 * through space.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class Spaceship {

  private RouteGenerator routeGenerator;
  private double health;
  private Route route;
  private EventListener log;

  /**
   * Construct a new Spaceship instance.
   */
  public Spaceship() {
    this.health = 100;
    this.routeGenerator = new RouteGenerator();
    this.route = routeGenerator.getRoute();
    this.log = new EventLog();
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
   * Update the Spaceship and related model classes for the step in the game
   * loop.
   * 
   * @param tpf
   *          Time per frame.
   */
  public void update(float tpf) {
    // update
  }

}
