package nl.tudelft.pixelperfect.route;

import java.util.ArrayList;

/**
 * A route through space that can be traversed by the spaceship.
 * 
 * @author Jesse Tilro
 *
 */
public class Route {

  private long timestamp;
  private long duration;
  private ArrayList<Tuple> nodes;
  private int idx;

  /**
   * Construct a new Route instance with a specified duration in milliseconds.
   * 
   * @param duration
   *          The total duration of the route.
   * @param route
   *          The sequence of RouteNodes this route consist of.
   */
  public Route(long duration, ArrayList<Tuple> route) {
    this.timestamp = System.currentTimeMillis();
    this.duration = duration;
    this.nodes = route;
    this.idx = 0;
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
   * Pop the next tuple from the queue of RouteNode's this route aggregates.
   * 
   * @return The next RouteNode.
   */
  public Tuple popRouteNode() {
    if (idx < nodes.size()) {
      Tuple node = nodes.get(idx);
      idx++;
      return node;
    }
    return null;
  }

  /**
   * Determine whether the route is completed.
   * 
   * @param now
   *          Current timestamp in milliseconds.
   * @return Boolean.
   */
  public boolean isCompleted(float now) {
    return (now >= timestamp + duration);
  }

  /**
   * Randomly generate a Route (which is an aggregation of RouteNodes) using the random
   * RouteGenerator.
   * 
   * @return A randomly generated route.
   */
  public static Route generateRoute() {
    RouteGenerator rg = RouteGenerator.getInstance();
    return rg.generateRoute();
  }

  /**
   * The String representation of a Route.
   * 
   * @return A String.
   */
  public String toString() {
    String res = "<Route (" + duration + " ms): ";
    res = res + nodes.toString() + ">";
    return res;
  }
}
