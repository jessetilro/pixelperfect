package nl.tudelft.pixelperfect.game;

/**
 * A route through space that can be traversed by the spaceship.
 * 
 * @author Jesse Tilro
 *
 */
public class Route {

  private long timestamp;
  private long duration;

  /**
   * Construct a new Route instance with a specified duration in milliseconds.
   * 
   * @param duration
   *          The total duration of the route.
   */
  public Route(long duration) {
    this.timestamp = System.currentTimeMillis();
    this.duration = duration;
  }

  /**
   * Set the Route's timestamp to a specified one.
   * 
   * @param timestamp
   *          The Route's new timestamp.
   */
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
  
  /**
   * Determine whether the route is completed.
   * 
   * @param now
   *          Current timestamp in milliseconds.
   * @return Boolean.
   */
  public boolean isCompleted(long now) {
    return (now >= timestamp + duration);
  }
}
