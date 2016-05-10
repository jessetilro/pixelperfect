package nl.tudelft.pixelperfect.route;

import java.util.ArrayList;

/**
 * Random generator for routes through space.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public final class RouteGenerator {

  private static volatile RouteGenerator instance;

  /**
   * Whenever the RouteGenerator is created a new Route will be created in this factory.
   */
  private RouteGenerator() {
  }

  /**
   * Creates a new RouteGenerator instance if it has not yet been instantiated.
   * 
   * @return The single RouteGenerator instance.
   */
  public static RouteGenerator getInstance() {
    if (instance == null) {
      synchronized (RouteGenerator.class) {
        if (instance == null) {
          instance = new RouteGenerator();
        }
      }
    }
    return instance;
  }

  /**
   * The algorithm for creating a route out of RouteNodes.
   * 
   * @return A new Route
   */
  public Route generateRoute() {
    return new Route(0, new ArrayList<RouteNode>());
  }
}
