package nl.tudelft.pixelperfect.route;

import java.util.ArrayList;

/**
 * A route through space that canbe traversed by the spaceship.
 * 
 * @author Jesse Tilro
 *
 */
public class Route {

  private long timestamp;
  private long duration;
  private ArrayList<RouteNode> nodes;
  private int idx;

  /**
   * Construct a new Route instance with a specified duration in milliseconds.
   * 
   * @param duration
   *          The total duration of the route.
   * @param route
   *          The sequence of RouteNodes this route consist of.
   */
  public Route(long duration, ArrayList<RouteNode> route) {
    this.timestamp = System.currentTimeMillis();
    this.duration = duration;
    this.nodes = route;
    this.idx = 0;
  }

  /**
   * Pop the next node from the queue of RouteNode's this route aggregates.
   * 
   * @return The next RouteNode.
   */
  public RouteNode popRouteNode() {
    if (idx < nodes.size()) {
      RouteNode node = nodes.get(idx);
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
  public boolean isCompleted(long now) {
    return (now >= timestamp + duration);
  }

  /**
   * Randomly generate a Route (which is an aggregation of RouteNodes) using the random
   * RouteGenerator.
   * 
   * @return A randomly generated route.
   */
  public static Route generateRoute() {
    return new Route(42, new ArrayList<RouteNode>());
  }
}
