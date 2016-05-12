package nl.tudelft.pixelperfect.route;

import java.util.ArrayList;
import java.util.Random;

/**
 * Random generator for routes through space.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public final class RouteGenerator {

  private Route mainRoute;
  private static volatile RouteGenerator instance;

  /**
   * Whenever the RouteGenerator is created a new Route will be created in this factory.
   */
  private RouteGenerator() {
    mainRoute = generateRoute();
  }

  /**
   * Retrieve the created route from the RouteGenerator.
   * 
   * @return a random Route.
   */
  public Route getRoute() {
    return mainRoute;
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
   * The algorithm for creating a route out of RouteNodes. First the algorithm created a random
   * number based of the number of nodes wanted. This number will represent which node to be
   * selected from the available nodes.
   * 
   * <p>
   * After creating a list of nodes (which must be divisible by 2) another iteration will run on
   * making a list of Tuples. This is to make the two-choice of the captain in the game easier.
   * 
   * @return A new Route
   */
  public Route generateRoute() {
    ArrayList<RouteNode> route = new ArrayList<RouteNode>();
    int routeLength = 10;
    int it = 0;
    int nodes = 5;
    while (it < routeLength) {
      Random rd = new Random();
      switch (rd.nextInt(nodes)) {
      case 0:
        route.add(new AllyNode("sum", "descr"));
        break;
      case 1:
        route.add(new AsteroidNode("sum", "descr"));
        break;
      case 2:
        route.add(new EarthNode("sum", "descr"));
        break;
      case 3:
        route.add(new EnemyNode("sum", "descr"));
        break;
      case 4:
        route.add(new MarsNode("sum", "descr"));
        break;
      default:
        break;
      }
      it++;
    }
    return createTuples(routeLength, route);
  }

  /**
   * Creating a list of Tuples out of the list with RouteNodes.
   * 
   * @param routeLength
   *          The length of the input list.
   * @param route
   *          The input list.
   * @return A new Route.
   */
  public Route createTuples(int routeLength, ArrayList<RouteNode> route) {
    ArrayList<Tuple> res = new ArrayList<Tuple>();
    int first = 0;
    int second = routeLength / 2;
    while (first < routeLength / 2) {
      res.add(new Tuple(route.get(first), route.get(second)));
      first++;
      second++;
    }
    return new Route(600000, res);
  }
}
