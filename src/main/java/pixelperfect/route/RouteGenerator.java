package pixelperfect.route;

import java.util.ArrayList;

/**
 * Random generator for routes through space.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class RouteGenerator {

  /**
   * Whenever the RouteGenerator is created a new Route will be created (in this
   * factory).
   */
  public RouteGenerator() {
  }

  /**
   * The algorithm for creating a route out of RouteNodes.
   * 
   * @return A new Route
   */
  public static Route generate() {
    return new Route(0, new ArrayList<RouteNode>());
  }
}
