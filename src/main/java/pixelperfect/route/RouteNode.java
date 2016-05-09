package pixelperfect.route;

/**
 * Node along a route through space.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class RouteNode {

  private String summary;
  private String description;

  /**
   * A constructor for the RouteNode.
   * 
   * @param summary
   *          , a summary about the RouteNode.
   * @param description
   *          , a description about the RouteNode.
   */
  public RouteNode(String summary, String description) {
    this.summary = summary;
    this.description = description;
  }
}
